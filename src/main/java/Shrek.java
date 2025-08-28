import java.util.Scanner;

import instruction.Instruction;
import parser.Parser;
import storage.Storage;
import ui.Ui;
import util.ShrekException;
import task.TaskList;

public class Shrek {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Shrek(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
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
                ui.showError(e.getMessage());
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Shrek("./data/shrek.txt").run();
    }
}