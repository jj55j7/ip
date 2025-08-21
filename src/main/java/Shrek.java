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
            String[] parts = input.split(" ", 2);
            String command = parts[0];

            if (command.equalsIgnoreCase("bye")) {
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("      Bye! I'm going to find Princess Fiona :)");
                System.out.println("      See ya later");
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                break;
            } else if (command.equalsIgnoreCase("list")) {
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                for (int i = 0; i < tasks.size(); i++) {
                    Task t = tasks.get(i);
                    System.out.println("      " + (i + 1) + ":[" +  t.getStatusIcon() + "] " + t.description);
                }
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else if (command.equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(parts[1]) - 1;
                Task t = tasks.get(index);
                t.markAsDone();
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("      Awesome! One layer of onion(task) has been removed");
                System.out.println("      (marked done)");
                System.out.println("       " + "[" +  t.getStatusIcon() + "] " + t.description);
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else if (command.equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(parts[1]) - 1;
                Task t = tasks.get(index);
                t.markAsNotDone();
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("      Awesome! One layer of onion(task) has been added");
                System.out.println("      (marked as not done yet)");
                System.out.println("       " + "[" +  t.getStatusIcon() + "] " + t.description);
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

