import java.util.Scanner;

import instruction.Instruction;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;
import util.ShrekException;

public class Shrek {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Shrek(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public static void main(String[] args) {
        new Shrek("./data/shrek.txt").run();
    }

    public void run() {
        Ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;

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