public abstract class Instruction {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ShrekException;

    public boolean isExit() {
        return false;
    }
}