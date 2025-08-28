package ui;

import task.*;
import util.ShrekException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Ui {
    // Parse index safely
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

    public static void printAddedTask(Task t, int size) {
        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("      Okies, onion (task) added:");
        System.out.println("       " + t);
        System.out.println("      Now you have " + size + " tasks in the list.");
        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void printTaskList(ArrayList<Task> tasks) {
        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("      " + (i + 1) + ":" + tasks.get(i));
        }
        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void printMarkUnmark(Task t, boolean mark) {
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

    public static void printDeleteTask(ArrayList<Task> tasks, Task t) {
        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("      One onion has been YEETED! (task removed)");
        System.out.println("       " + t);
        System.out.println("      Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void printTasksOnDate(ArrayList<Task> tasks, LocalDate date) {
        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("      Tasks on " + date + ":");
        boolean found = false;

        for (Task t : tasks) {
            if (t instanceof Deadline d && d.getBy().equals(date)) {  // Use getter instead of direct access
                System.out.println("       " + t);
                found = true;
            } else if (t instanceof Event e) {
                LocalDate startDate = e.getFrom().toLocalDate();
                LocalDate endDate = e.getTo().toLocalDate();

                // Check if query date is within [startDate, endDate]
                if ((date.isEqual(startDate) || date.isAfter(startDate)) &&
                        (date.isEqual(endDate) || date.isBefore(endDate))) {
                    System.out.println("       " + t);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("      No onions (tasks) on this date!");
        }
        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void showWelcome() {
        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("      Hello I'm Shrek!");
        System.out.println("      Welcome to my Swamp!");
        System.out.println("      What can I do for you?");
        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void showGoodbye() {
        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("      Bye! I'm going to find Princess Fiona :)");
        System.out.println("      See ya later");
        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void showError(String msg) {
        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("      " + msg);
        System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
