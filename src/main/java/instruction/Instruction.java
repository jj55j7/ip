package instruction;

import storage.Storage;
import task.TaskList;
import ui.Ui;
import util.ShrekException;

/**
 * Abstract base class representing an executable instruction in the Shrek application.
 * All specific instructions (Add, Delete, Mark, etc.) extend this class.
 */
public abstract class Instruction {
    /**
     * Executes the specific instruction logic.
     *
     * @param tasks   the task list to operate on
     * @param ui      the user interface for displaying messages
     * @param storage the storage system for data persistence
     * @throws ShrekException if an error occurs during instruction execution
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws ShrekException;

    /**
     * Indicates whether this instruction should terminate the application.
     * Default implementation returns false; override in subclasses that require exit behavior.
     *
     * @return false by default, true for exit instructions
     */
    public boolean isExit() {
        return false;
    }
}
