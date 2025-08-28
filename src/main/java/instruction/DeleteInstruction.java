package instruction;

import task.TaskList;
import task.Task;
import ui.Ui;
import storage.Storage;
import util.ShrekException;

public class DeleteInstruction extends Instruction {
    private int index;

    public DeleteInstruction(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ShrekException {
        Task removedTask = tasks.remove(index);
        storage.save(tasks.getAllTasks());
        ui.printDeleteTask(tasks.getAllTasks(), removedTask);
    }
}