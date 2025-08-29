package task;

/**
 * Abstract base class representing a task in the Shrek application.
 * Provides common functionality for all task types (Todo, Deadline, Event).
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Returns the file format representation of the task.
     * Must be implemented by concrete task subclasses.
     *
     * @return string representation suitable for file storage
     */
    public abstract String toFileFormat();

    /**
     * Constructs a Task with the specified description.
     * Initializes the task as not done.
     *
     * @param description the task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon representing the task's completion state.
     *
     * @return "X" if done, " " (space) if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the Task.
     * Includes status icon and description.
     *
     * @return formatted string representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
