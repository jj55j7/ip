package parser;

import instruction.*;
import task.*;
import util.Command;
import util.ShrekException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Instruction parse(String userInput) throws ShrekException {
        String[] parts = userInput.split(" ", 2);
        String commandWord = parts[0].trim().toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";

        // First, validate using your existing Command enum
        Command commandEnum = Command.fromString(commandWord);

        // Then create the appropriate Instruction object
        switch (commandEnum) {
            case BYE:
                return new ExitInstruction();

            case LIST:
                return new ListInstruction();

            case TODO:
                return parseTodo(arguments);

            case DEADLINE:
                return parseDeadline(arguments);

            case EVENT:
                return parseEvent(arguments);

            case MARK:
                return parseMark(arguments, true);

            case UNMARK:
                return parseMark(arguments, false);

            case DELETE:
                return parseDelete(arguments);

            case ONDATE:
                return parseOnDate(arguments);

            default:
                throw new ShrekException("I don't speak your language. I don't understand: " + commandWord);
        }
    }

    private static Instruction parseTodo(String arguments) throws ShrekException {
        if (arguments.trim().isEmpty()) {
            throw new ShrekException("No Onions?!? The description of a todo cannot be empty.");
        }
        return new AddInstruction(new Todo(arguments.trim()));
    }

    private static Instruction parseDeadline(String arguments) throws ShrekException {
        if (!arguments.contains("/by")) {
            throw new ShrekException("Deadlines must have a description and a /by date.");
        }

        String[] deadlineParts = arguments.split("/by", 2);
        if (deadlineParts.length < 2) {
            throw new ShrekException("Deadline format should be: description /by yyyy-MM-dd");
        }

        String description = deadlineParts[0].trim();
        String by = deadlineParts[1].trim();

        if (description.isEmpty() || by.isEmpty()) {
            throw new ShrekException("Where's my onions? Deadline description or date cannot be empty.");
        }

        return new AddInstruction(new Deadline(description, by));
    }

    private static Instruction parseEvent(String arguments) throws ShrekException {
        // Check if both /from and /to are present
        if (!arguments.contains("/from") || !arguments.contains("/to")) {
            throw new ShrekException("Events must have both /from and /to date and times");
        }

        // Handle both orders: /from first or /to first
        String description;
        String from;
        String to;

        if (arguments.indexOf("/from") < arguments.indexOf("/to")) {
            // Normal order: /from comes before /to
            String[] eventParts = arguments.split("/from", 2);
            if (eventParts.length < 2) {
                throw new ShrekException("Shrek needs event with /from 2025-12-05 14:00 /to 2025-12-31 16:00");
            }

            description = eventParts[0].trim();
            String[] timeParts = eventParts[1].split("/to", 2);

            if (timeParts.length < 2) {
                throw new ShrekException("Shrek needs event with /from 2025-12-05 14:00 /to 2025-12-31 16:00");
            }

            from = timeParts[0].trim();
            to = timeParts[1].trim();
        } else {
            // Reverse order: /to comes before /from
            String[] eventParts = arguments.split("/to", 2);
            if (eventParts.length < 2) {
                throw new ShrekException("Event format should be: description /from time /to time");
            }

            description = eventParts[0].trim();
            String[] timeParts = eventParts[1].split("/from", 2);

            if (timeParts.length < 2) {
                throw new ShrekException("Event format should be: description /from time /to time");
            }

            to = timeParts[0].trim();
            from = timeParts[1].trim();
        }

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new ShrekException("Event description, start time or end time cannot be empty.");
        }

        return new AddInstruction(new Event(description, from, to));
    }

    private static Instruction parseMark(String arguments, boolean markAsDone) throws ShrekException {
        if (arguments.trim().isEmpty()) {
            throw new ShrekException("Shrek needs a task number to " + (markAsDone ? "mark" : "unmark") + "!");
        }

        try {
            int index = Integer.parseInt(arguments.trim()) - 1;
            return new MarkInstruction(index, markAsDone);
        } catch (NumberFormatException e) {
            throw new ShrekException("Shrek needs a valid task number!");
        }
    }

    private static Instruction parseDelete(String arguments) throws ShrekException {
        if (arguments.trim().isEmpty()) {
            throw new ShrekException("Shrek needs a task number to delete!");
        }

        try {
            int index = Integer.parseInt(arguments.trim()) - 1;
            return new DeleteInstruction(index);
        } catch (NumberFormatException e) {
            throw new ShrekException("Shrek needs a valid task number!");
        }
    }

    private static Instruction parseOnDate(String arguments) throws ShrekException {
        if (arguments.trim().isEmpty()) {
            throw new ShrekException("Shrek needs a date! Format: yyyy-MM-dd");
        }

        try {
            LocalDate date = LocalDate.parse(arguments.trim());
            return new OnDateInstruction(date);
        } catch (DateTimeParseException e) {
            throw new ShrekException("Shrek needs a valid date in *yyyy-MM-dd* format, e.g. 2025-01-01");
        }
    }

    // Keep the file parsing method for Storage
    public static Task parseTaskFromFile(String line) throws ShrekException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new ShrekException("Invalid task format in file: " + line);
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String desc = parts[2];

        Task task;
        switch (type) {
            case "T":
                if (parts.length != 3) {
                    throw new ShrekException("Invalid todo format in file: " + line);
                }
                task = new Todo(desc);
                break;
            case "D":
                if (parts.length != 4) {
                    throw new ShrekException("Invalid deadline format in file: " + line);
                }
                task = new Deadline(desc, parts[3]);
                break;
            case "E":
                if (parts.length != 5) {
                    throw new ShrekException("Invalid event format in file: " + line);
                }
                task = new Event(desc, parts[3], parts[4]);
                break;
            default:
                throw new ShrekException("Stinky onion (Corrupted task) in storage: " + line);
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}