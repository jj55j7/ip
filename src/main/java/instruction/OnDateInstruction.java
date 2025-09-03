package instruction;

import java.time.LocalDate;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents an instruction to display tasks occurring on a specific date.
 * This instruction filters and shows tasks (deadlines and events) that are
 * relevant to the specified date.
 */
public class OnDateInstruction extends Instruction {
    private LocalDate date;

    /**
     * Constructs an OnDateInstruction with the specified date for task filtering.
     *
     * @param date the date to filter tasks by
     */
    public OnDateInstruction(LocalDate date) {
        this.date = date;
    }

    /**
     * Executes the on-date instruction by displaying tasks that occur on the specified date.
     * Includes deadlines due on that date and events spanning that date.
     *
     * @param tasks   the task list to filter by date
     * @param ui      the user interface for displaying the filtered tasks
     * @param storage the storage system (unused in this instruction)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTasksOnDate(tasks.getAllTasks(), date);
    }
}
