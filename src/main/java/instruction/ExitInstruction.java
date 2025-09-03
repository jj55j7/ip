package instruction;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents an instruction to exit the application.
 * This instruction handles the graceful termination of the Shrek application.
 */
public class ExitInstruction extends Instruction {
    /**
     * Executes the exit instruction by displaying the goodbye message.
     *
     * @param tasks   the task list (unused in this instruction)
     * @param ui      the user interface for displaying the goodbye message
     * @param storage the storage system (unused in this instruction)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Indicates that this instruction should terminate the application.
     *
     * @return true always, indicating the application should exit
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
