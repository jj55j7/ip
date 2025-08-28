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