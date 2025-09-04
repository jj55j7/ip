package instruction;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents an instruction to find tasks that contain a specific keyword.
 * This instruction searches through the list of tasks and displays those
 * that match the given keyword using the UI component.
 */
public class FindInstruction extends Instruction {
    private String word;

    /**
     * Constructs a FindInstruction with the specified keyword.
     *
     * @param word The keyword to search for in tasks.
     */
    public FindInstruction(String word) {
        this.word = word;
    }

    /**
     * Executes the find instruction by searching for tasks that contain the keyword.
     * The matching tasks are displayed using the UI.
     *
     * @param tasks   The list of tasks to search through.
     * @param ui      The UI component used to display the results.
     * @param storage The storage component (not used in this instruction).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printFind(tasks.getAllTasks(), word);
    }
}
