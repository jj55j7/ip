public class ListInstruction extends Instruction {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTaskList(tasks.getAllTasks());
    }
}