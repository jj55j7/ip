import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {

    // Parse a line from the save file (tasks.txt)
    public static Task parseTask(String line) throws ShrekException {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String desc = parts[2];

        Task t;
        switch (type) {
            case "T":
                t = new Todo(desc);
                break;
            case "D":
                t = new Deadline(desc, parts[3]); // stored date string
                break;
            case "E":
                t = new Event(desc, parts[3], parts[4]); // stored start + end
                break;
            default:
                throw new ShrekException("Stinky onion (Corrupted task) in storage: " + line);
        }
        if (isDone) {
            t.markAsDone();
        }
        return t;
    }

    // Parse the first word of user input into a Command enum
    public static Command parseCommand(String input) throws ShrekException {
        String[] parts = input.trim().split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        return Command.fromString(commandWord);
    }

    public static int parseIndex(String[] parts, int size) throws ShrekException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new ShrekException("Shrek needs a task number!");
        }

        try {
            int idx = Integer.parseInt(parts[1].trim()) - 1;
            if (idx < 0 || idx >= size) {
                throw new ShrekException("Shrek can’t find that task number!");
            }
            return idx;
        } catch (NumberFormatException e) {
            throw new ShrekException("Shrek needs a valid number!");
        }
    }

    public static Todo parseTodo(String[] parts) throws ShrekException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new ShrekException("No Onions?!? The description of a todo cannot be empty.");
        }
        return new Todo(parts[1].trim());
    }

    public static Deadline parseDeadline(String[] parts) throws ShrekException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new ShrekException("Deadlines must have a description and a /by date.");
        }

        String raw = parts[1];
        if (!raw.contains("/by")) {
            throw new ShrekException("Shrek needs a deadline with /by <date>.");
        }

        String[] deadlineParts = raw.split("/by", 2);
        String description = deadlineParts[0].trim();
        String by = deadlineParts[1].trim();

        if (description.isEmpty() || by.isEmpty()) {
            throw new ShrekException("Where's my onions? Deadline description or date cannot be empty.");
        }

        return new Deadline(description, by);
    }

    public static Event parseEvent(String[] parts) throws ShrekException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new ShrekException("Events must have a description, /from and /to times.");
        }

        String raw = parts[1];
        if (!raw.contains("/from") || !raw.contains("/to")) {
            throw new ShrekException("Shrek needs event with /from <time> and /to <time>.");
        }

        String[] eventParts = raw.split("/from", 2);
        String description = eventParts[0].trim();

        String[] timeParts = eventParts[1].split("/to", 2);
        String from = timeParts[0].trim();
        String to = timeParts[1].trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new ShrekException("Event description, start time or end time cannot be empty.");
        }

        return new Event(description, from, to);
    }

    // Handle the "ondate" command (list tasks occurring on a date)
    public static void handleOnDate(String[] parts, ArrayList<Task> tasks) throws ShrekException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new ShrekException("Shrek needs a date! Format: yyyy-MM-dd");
        }
        String dateStr = parts[1].trim();
        LocalDate queryDate;
        try {
            queryDate = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new ShrekException("Shrek needs a valid date in *yyyy-MM-dd* format, e.g. 2025-01-01");
        }
        Ui.printTasksOnDate(tasks, queryDate);
    }
}
