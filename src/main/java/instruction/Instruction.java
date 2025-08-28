package instruction;

import task.TaskList;
import task.Task;
import ui.Ui;
import storage.Storage;
import util.ShrekException;

public abstract class Instruction {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ShrekException;

    public boolean isExit() {
        return false;
    }
}