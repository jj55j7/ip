package instruction;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents an instruction to display all tasks in the task list.
 * This instruction shows the current state of all tasks to the user.
 */
public class ListInstruction extends Instruction {
    /**
     * Executes the list instruction by displaying all tasks in the task list.
     *
     * @param tasks   the task list to be displayed
     * @param ui      the user interface for displaying the task list
     * @param storage the storage system (unused in this instruction)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTaskList(tasks.getAllTasks());
    }
}
