import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Shrek {

    private Storage storage;
    private Ui ui;

    public static void main(String[] args) {
        Ui.showWelcome();

        Scanner myObj = new Scanner(System.in);
        Storage storage = new Storage("./data/shrek.txt");
        ArrayList<Task> tasks = storage.load();


        while (true) {
            String input = myObj.nextLine().trim();
            if (input.isEmpty()) continue; // ignore empty input

            String[] parts = input.split(" ", 2);
            String commandWord  = parts[0].trim().toLowerCase();

            try {
                Command command = Command.fromString(commandWord);
                switch (command) {
                    case BYE -> {
                        Ui.showGoodbye();
                        myObj.close();
                        return; // exit program
                    }
                    case TODO -> {
                        Task t = Parser.parseTodo(parts);
                        tasks.add(t);
                        storage.save(tasks);
                        Ui.printAddedTask(t, tasks.size());
                    }
                    case DEADLINE -> {
                        Task t = Parser.parseDeadline(parts);
                        tasks.add(t);
                        storage.save(tasks);
                        Ui.printAddedTask(t, tasks.size());
                    }
                    case EVENT -> {
                        Task t = Parser.parseEvent(parts);
                        tasks.add(t);
                        storage.save(tasks);
                        Ui.printAddedTask(t, tasks.size());
                    }
                    case LIST -> Ui.printTaskList(tasks);
                    case MARK -> {
                        int idx = Parser.parseIndex(parts, tasks.size());
                        tasks.get(idx).markAsDone();
                        Ui.printMarkUnmark(tasks.get(idx), true);
                        storage.save(tasks);
                    }
                    case UNMARK -> {
                        int idx = Parser.parseIndex(parts, tasks.size());
                        tasks.get(idx).markAsNotDone();
                        Ui.printMarkUnmark(tasks.get(idx), false);
                        storage.save(tasks);

                    }
                    case DELETE -> {
                        int idx = Parser.parseIndex(parts, tasks.size());
                        Task t = tasks.get(idx);
                        tasks.remove(idx);
                        Ui.printDeleteTask(tasks, t);
                        storage.save(tasks);
                    }
                    case ONDATE -> {
                        Parser.handleOnDate(parts, tasks);
                    }
                    default -> throw new ShrekException("I don't speak your language. I don't understand: " + command);
                }
            } catch (ShrekException e) {
                Ui.showError(e.getMessage());
            }

        }
    }
}