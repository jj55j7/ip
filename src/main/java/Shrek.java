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
        ArrayList<Task> tasks = new ArrayList<Task>();

        while (true) {
            String input = myObj.nextLine();
            String[] parts = input.trim().split(" ", 2);
            String command = parts[0].trim();

            if (command.equalsIgnoreCase("bye")) {
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("      Bye! I'm going to find Princess Fiona :)");
                System.out.println("      See ya later");
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                break;
            } else if (command.equalsIgnoreCase("todo")) {
                Task t = new Todo(parts[1].trim());
                tasks.add(t);
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("      Okies, task added:");
                System.out.println("       " + t);
                System.out.println("      " + tasks.size() + " tasks remain in the list.");
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else if (command.equalsIgnoreCase("deadline")) {
                String[] deadlineParts = parts[1].trim().split("/by", 2);
                String description = deadlineParts[0].trim();
                String by = deadlineParts[1].trim();
                Task t = new Deadline(description, by);
                tasks.add(t);
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("      Task with deadline added:");
                System.out.println("       " + t);
                System.out.println("      Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else if (command.equalsIgnoreCase("event")) {
                String[] eventParts = parts[1].split("/from", 2);
                String description = eventParts[0].trim();

                String[] timeParts = eventParts[1].split("/to", 2);
                String from = timeParts[0].trim();
                String to = timeParts[1].trim();

                Task t = new Event(description, from, to);
                tasks.add(t);
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("      Event added:");
                System.out.println("       " + t);
                System.out.println("      Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else if (command.equalsIgnoreCase("list")) {
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("      " + (i + 1) + ":" +  tasks.get(i));
                }
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else if (command.equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(parts[1].trim()) - 1;
                Task t = tasks.get(index);
                t.markAsDone();
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("      Awesome! One layer of onion(task) has been removed");
                System.out.println("      (marked done)");
                System.out.println("       " + t);
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else if (command.equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(parts[1]) - 1;
                Task t = tasks.get(index);
                t.markAsNotDone();
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("      One layer of onion(task) has been added back");
                System.out.println("      (marked as not done yet)");
                System.out.println("       " + t);
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else {
                Task t = new Task(input);
                tasks.add(t);
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("      added: " + input);
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        }
        myObj.close();
    }
}

