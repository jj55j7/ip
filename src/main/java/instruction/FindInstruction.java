package instruction;

import task.TaskList;
import task.Task;
import ui.Ui;
import storage.Storage;
import util.ShrekException;

import java.time.LocalDate;

public class FindInstruction extends Instruction {
    private String word;

    public FindInstruction(String word) {
        this.word = word;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printFind(tasks.getAllTasks(), word);
    }
}