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
        ArrayList<String> tasks = new ArrayList<String>();

        while (true) {
            String input = myObj.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("      Bye! I'm going to find Princess Fiona :)");
                System.out.println("      See ya later");
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("      " + (i + 1) + ": " + tasks.get(i));
                }
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else {
                tasks.add(input);
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("      added: " + input);
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        }
        myObj.close();
    }
}

