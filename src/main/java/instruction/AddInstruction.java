package instruction;

import task.TaskList;
import task.Task;
import ui.Ui;
import storage.Storage;
import util.ShrekException;

public class AddInstruction extends Instruction {
    private Task taskToAdd;

    public AddInstruction(Task task) {
        this.taskToAdd = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ShrekException {
        tasks.add(taskToAdd);
        storage.save(tasks.getAllTasks());
        ui.printAddedTask(taskToAdd, tasks.size());
    }
}