import java.util.Scanner;

import instruction.Instruction;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;
import util.ShrekException;

/**
 * The main Shrek application class. Initializes and runs the task management system.
 */
public class Shrek {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a new Shrek instance with the specified file path for data storage.
     *
     * @param filePath the path to the data file
     */
    public Shrek(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * The main entry point of the application.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        new Shrek("./data/shrek.txt").run();
    }

    /**
     * Starts the main application loop to process user commands.
     */
    public void run() {
        Ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                continue;
            }

            try {
                Instruction instruction = Parser.parse(input);
                instruction.execute(tasks, ui, storage);
                isExit = instruction.isExit();
            } catch (ShrekException e) {
                Ui.showError(e.getMessage());
            }
        }
        scanner.close();
    }
}
