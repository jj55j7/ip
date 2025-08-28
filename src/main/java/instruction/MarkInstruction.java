package instruction;

import task.TaskList;
import task.Task;
import ui.Ui;
import storage.Storage;
import util.ShrekException;

public class MarkInstruction extends Instruction {
    private int index;
    private boolean markAsDone;

    public MarkInstruction(int index, boolean markAsDone) {
        this.index = index;
        this.markAsDone = markAsDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ShrekException {
        Task task = tasks.get(index);
        if (markAsDone) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
        storage.save(tasks.getAllTasks());
        ui.printMarkUnmark(task, markAsDone);
    }
}