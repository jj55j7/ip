import java.time.LocalDate;

public class OnDateInstruction extends Instruction {
    private LocalDate date;

    public OnDateInstruction(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTasksOnDate(tasks.getAllTasks(), date);
    }
}