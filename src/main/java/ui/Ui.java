package ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import task.Deadline;
import task.Event;
import task.Task;
import util.ShrekException;

/**
 * Handles all user interface interactions for the Shrek application.
 * Responsible for generating formatted message strings for GUI display.
 */
public class Ui {
    /**
     * Parses a string input into a task index with validation.
     *
     * @param input the string input containing the task number
     * @param size  the current size of the task list
     * @return the zero-based index of the task
     * @throws ShrekException if the input is not a valid integer or is out of range
     */
    public static int parseIndex(String input, int size) throws ShrekException {
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

    /**
     * Returns a confirmation message when a task is successfully added.
     *
     * @param t    the task that was added
     * @param size the new size of the task list after addition
     * @return formatted message string
     */
    public String printAddedTask(Task t, int size) {
        return "Okies, onion (task) added:\n"
                + "  " + t + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Returns a formatted string of all tasks in the task list.
     *
     * @param tasks the list of tasks to display
     * @return formatted task list string
     */
    public String printTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "Your task list is empty! Time to add some onions!";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are your onions (tasks):\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("  ")
                    .append(i + 1)
                    .append(": ")
                    .append(tasks.get(i))
                    .append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a confirmation message when a task is marked or unmarked.
     *
     * @param t    the task that was marked or unmarked
     * @param mark true if marked as done, false if unmarked
     * @return formatted message string
     */
    public String printMarkUnmark(Task t, boolean mark) {
        String message;
        if (mark) {
            message = "Awesome! One layer of onion(task) has been removed\n"
                    + "(marked done)\n";
        } else {
            message = "One layer of onion(task) has been added back\n"
                    + "(marked as not done yet)\n";
        }
        return message + "  " + t;
    }

    /**
     * Returns a confirmation message when a task is successfully deleted.
     *
     * @param tasks the task list after deletion
     * @param t     the task that was deleted
     * @return formatted message string
     */
    public String printDeleteTask(ArrayList<Task> tasks, Task t) {
        return "One onion has been YEETED! (task removed)\n"
                + "  " + t + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Returns a formatted string of tasks that occur on a specific date using streams.
     *
     * @param tasks the list of tasks to filter by date
     * @param date  the date to filter tasks by
     * @return formatted message string
     */
    public String printTasksOnDate(ArrayList<Task> tasks, LocalDate date) {
        List<String> matchingTasks = tasks.stream()
                .filter(task -> isTaskOnDate(task, date))
                .map(task -> "  " + task + "\n")
                .collect(Collectors.toList());

        if (matchingTasks.isEmpty()) {
            return "No onions (tasks) on " + date + "!";
        }

        return "Tasks on " + date + ":\n" + String.join("", matchingTasks);
    }

    /**
     * Returns a boolean if task is on or during date.
     *
     */
    private boolean isTaskOnDate(Task task, LocalDate date) {
        if (task instanceof Deadline d) {
            return d.getBy().equals(date);
        } else if (task instanceof Event e) {
            LocalDate startDate = e.getFrom().toLocalDate();
            LocalDate endDate = e.getTo().toLocalDate();
            return (date.isEqual(startDate) || date.isAfter(startDate))
                    && (date.isEqual(endDate) || date.isBefore(endDate));
        }
        return false;
    }

    /**
     * Returns a formatted string of tasks that match the search keyword.
     *
     * @param tasks the list of tasks to search through
     * @param word  the keyword to search for
     * @return formatted message string
     */
    public String printFind(ArrayList<Task> tasks, String word) {
        StringBuilder sb = new StringBuilder();
        sb.append("Matching onions (tasks) with: ")
                .append(word)
                .append("\n");
        boolean found = false;

        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getDescription().toLowerCase().contains(word.toLowerCase())) {
                sb.append("  ")
                        .append(i + 1)
                        .append(". ")
                        .append(t)
                        .append("\n");
                found = true;
            }
        }

        if (!found) {
            return "No matching onions (tasks) for: " + word;
        }
        return sb.toString();
    }

    /**
     * Returns the welcome message.
     *
     * @return welcome message string
     */
    public String showWelcome() {
        return "Hello I'm Shrek!\n"
                + "Welcome to my Swamp!\n"
                + "What can I do for you?";
    }

    /**
     * Returns the goodbye message.
     *
     * @return goodbye message string
     */
    public String showGoodbye() {
        return "Bye! I'm going to find Princess Fiona :)\n"
                + "See ya later";
    }

    /**
     * Returns an error message.
     *
     * @param msg the error message to display
     * @return formatted error message string
     */
    public String showError(String msg) {
        return msg;
    }
}

//package ui;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//
//import task.Deadline;
//import task.Event;
//import task.Task;
//import util.ShrekException;
//
///**
// * Handles all user interface interactions for the Shrek application.
// * Responsible for displaying messages and formatting output.
// */
//public class Ui {
//    /**
//     * Parses a string input into a task index with validation.
//     *
//     * @param input the string input containing the task number
//     * @param size  the current size of the task list
//     * @return the zero-based index of the task
//     * @throws ShrekException if the input is not a valid integer or is out of range
//     */
//    // Parse index safely
//    public static int parseIndex(String input, int size) throws ShrekException {
//        try {
//            int index = Integer.parseInt(input.trim()) - 1;
//            if (index < 0 || index >= size) {
//                throw new ShrekException("BIG onion! or is it too small? Task number out of range.");
//            }
//            return index;
//        } catch (NumberFormatException e) {
//            throw new ShrekException("Task number must be an integer.");
//        }
//    }
//
//    /**
//     * Displays a confirmation message when a task is successfully added.
//     *
//     * @param t    the task that was added
//     * @param size the new size of the task list after addition
//     */
//    public static void printAddedTask(Task t, int size) {
//        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//        System.out.println("      Okies, onion (task) added:");
//        System.out.println("       " + t);
//        System.out.println("      Now you have " + size + " tasks in the list.");
//        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//    }
//
//    /**
//     * Displays all tasks in the task list with their current status.
//     *
//     * @param tasks the list of tasks to display
//     */
//    public static void printTaskList(ArrayList<Task> tasks) {
//        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//        for (int i = 0; i < tasks.size(); i++) {
//            System.out.println("      " + (i + 1) + ":" + tasks.get(i));
//        }
//        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//    }
//
//    /**
//     * Displays a confirmation message when a task is marked or unmarked.
//     *
//     * @param t    the task that was marked or unmarked
//     * @param mark true if marked as done, false if unmarked
//     */
//    public static void printMarkUnmark(Task t, boolean mark) {
//        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//        if (mark) {
//            System.out.println("      Awesome! One layer of onion(task) has been removed");
//            System.out.println("      (marked done)");
//        } else {
//            System.out.println("      One layer of onion(task) has been added back");
//            System.out.println("      (marked as not done yet)");
//        }
//        System.out.println("       " + t);
//        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//    }
//
//    /**
//     * Displays a confirmation message when a task is successfully deleted.
//     *
//     * @param tasks the task list after deletion
//     * @param t     the task that was deleted
//     */
//    public static void printDeleteTask(ArrayList<Task> tasks, Task t) {
//        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//        System.out.println("      One onion has been YEETED! (task removed)");
//        System.out.println("       " + t);
//        System.out.println("      Now you have " + tasks.size() + " tasks in the list.");
//        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//    }
//
//    /**
//     * Displays all tasks that occur on a specific date.
//     * Includes deadlines due on that date and events spanning that date.
//     *
//     * @param tasks the list of tasks to filter by date
//     * @param date  the date to filter tasks by
//     */
//    public static void printTasksOnDate(ArrayList<Task> tasks, LocalDate date) {
//        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//        System.out.println("      Tasks on " + date + ":");
//        boolean found = false;
//
//        for (Task t : tasks) {
//            // Use getter instead of direct access
//            if (t instanceof Deadline d && d.getBy().equals(date)) {
//                System.out.println("       " + t);
//                found = true;
//            } else if (t instanceof Event e) {
//                LocalDate startDate = e.getFrom().toLocalDate();
//                LocalDate endDate = e.getTo().toLocalDate();
//
//                // Check if query date is within [startDate, endDate]
//                if ((date.isEqual(startDate) || date.isAfter(startDate))
//                        && (date.isEqual(endDate) || date.isBefore(endDate))) {
//                    System.out.println("       " + t);
//                    found = true;
//                }
//            }
//        }
//        if (!found) {
//            System.out.println("      No onions (tasks) on this date!");
//        }
//        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//    }
//
//    /**
//     * Displays tasks that match the search keyword.
//     *
//     * @param tasks the list of tasks to search through
//     * @param word  the keyword to search for
//     */
//    public static void printFind(ArrayList<Task> tasks, String word) {
//        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//        System.out.println("      Matching onions (tasks) with: " + word);
//        boolean found = false;
//
//        for (int i = 0; i < tasks.size(); i++) {
//            Task t = tasks.get(i);
//            if (t.getDescription().toLowerCase().contains(word.toLowerCase())) {
//                System.out.println("      " + (i + 1) + "." + t);
//                found = true;
//            }
//        }
//
//        if (!found) {
//            System.out.println("      No matching onions (tasks) for: " + word);
//        }
//        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//    }
//
//    /**
//     * Displays the welcome message when the application starts.
//     */
//    public static void showWelcome() {
//        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//        System.out.println("      Hello I'm Shrek!");
//        System.out.println("      Welcome to my Swamp!");
//        System.out.println("      What can I do for you?");
//        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//    }
//
//    /**
//     * Displays the goodbye message when the application exits.
//     */
//    public static void showGoodbye() {
//        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//        System.out.println("      Bye! I'm going to find Princess Fiona :)");
//        System.out.println("      See ya later");
//        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//    }
//
//    /**
//     * Displays an error message to the user.
//     *
//     * @param msg the error message to display
//     */
//    public static void showError(String msg) {
//        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//        System.out.println("      " + msg);
//        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//    }
//}
