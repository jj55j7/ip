import java.util.Scanner;
import java.util.ArrayList;

public class Shrek {

    public static void main(String[] args) {
        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("      Hello I'm Shrek!");
        System.out.println("      Welcome to my Swamp!");
        System.out.println("      What can I do for you?");
        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        Scanner myObj = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            String input = myObj.nextLine().trim();
            if (input.isEmpty()) continue; // ignore empty input

            String[] parts = input.split(" ", 2);
            String command = parts[0].trim().toLowerCase();

            try {
                switch (command) {
                    case "bye" -> {
                        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("      Bye! I'm going to find Princess Fiona :)");
                        System.out.println("      See ya later");
                        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        myObj.close();
                        return; // exit program
                    }
                    case "todo" -> {
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new ShrekException("No Onions?!? The description of a todo cannot be empty.");
                        }
                        Task t = new Todo(parts[1].trim());
                        tasks.add(t);
                        printAddedTask(t, tasks.size());
                    }
                    case "deadline" -> {
                        if (parts.length < 2 || !parts[1].contains("/by")) {
                            throw new ShrekException("Deadlines must have a description and a /by date.");
                        }
                        String[] deadlineParts = parts[1].split("/by", 2);
                        String description = deadlineParts[0].trim();
                        String by = deadlineParts[1].trim();
                        if (description.isEmpty() || by.isEmpty()) {
                            throw new ShrekException("Where's my onions? Deadline description or date cannot be empty.");
                        }
                        Task t = new Deadline(description, by);
                        tasks.add(t);
                        printAddedTask(t, tasks.size());
                    }
                    case "event" -> {
                        if (parts.length < 2 || !parts[1].contains("/from") || !parts[1].contains("/to")) {
                            throw new ShrekException("Events must have a description, /from and /to times.");
                        }
                        String[] eventParts = parts[1].split("/from", 2);
                        String description = eventParts[0].trim();
                        String[] timeParts = eventParts[1].split("/to", 2);
                        String from = timeParts[0].trim();
                        String to = timeParts[1].trim();
                        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                            throw new ShrekException("Event description, start time or end time cannot be empty.");
                        }
                        Task t = new Event(description, from, to);
                        tasks.add(t);
                        printAddedTask(t, tasks.size());
                    }
                    case "list" -> printTaskList(tasks);
                    case "mark" -> {
                        if (parts.length < 2) {
                            throw new ShrekException("Missing Onion! Mark command needs exactly one task number.");
                        }
                        int index = parseIndex(parts[1], tasks.size());
                        Task t = tasks.get(index);
                        t.markAsDone();
                        printMarkUnmark(t, true);
                    }
                    case "unmark" -> {
                        if (parts.length < 2) {
                            throw new ShrekException("Missing Onion! Unmark command needs exactly one task number.");
                        }
                        int index = parseIndex(parts[1], tasks.size());
                        Task t = tasks.get(index);
                        t.markAsNotDone();
                        printMarkUnmark(t, false);
                    }
                    case "delete" -> {
                        if (parts.length < 2) {
                            throw new ShrekException("Missing Onion! Delete command needs exactly one task number.");
                        }
                        int index = parseIndex(parts[1], tasks.size());
                        Task t = tasks.get(index);
                        tasks.remove(index);
                        printDeleteTask(tasks, t);
                    }

                    default -> throw new ShrekException("I don't speak your language. I don't understand: " + command);
                }
            } catch (ShrekException e) {
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("      " + e.getMessage());
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        }
    }

    private static int parseIndex(String input, int size) throws ShrekException {
        try {
            int index = Integer.parseInt(input.trim()) - 1;
            if (index < 0 || index >= size) {
                throw new ShrekException("BIG onion! or is it too small? Task number out of range.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new ShrekException("Task number must be an integer.");
        }
    }

    private static void printAddedTask(Task t, int size) {
        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("      Okies, onion (task) added:");
        System.out.println("       " + t);
        System.out.println("      Now you have " + size + " tasks in the list.");
        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private static void printTaskList(ArrayList<Task> tasks) {
        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("      " + (i + 1) + ":" + tasks.get(i));
        }
        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private static void printMarkUnmark(Task t, boolean mark) {
        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        if (mark) {
            System.out.println("      Awesome! One layer of onion(task) has been removed");
            System.out.println("      (marked done)");
        } else {
            System.out.println("      One layer of onion(task) has been added back");
            System.out.println("      (marked as not done yet)");
        }
        System.out.println("       " + t);
        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    private static void printDeleteTask(ArrayList<Task> tasks, Task t) {
        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("      One onion has been YEETED! (task removed)");
        System.out.println("       " + t);
        System.out.println("      Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}