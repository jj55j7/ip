package task;

import java.util.ArrayList;

import util.ShrekException;

/**
 * Manages a collection of tasks and provides operations for task manipulation.
 * Serves as the main data structure for storing and managing tasks in the application.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with existing tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves a task from the task list by index.
     *
     * @param index the zero-based index of the task to retrieve
     * @return the task at the specified index
     * @throws ShrekException if the index is out of bounds
     */
    public Task get(int index) throws ShrekException {
        if (index < 0 || index >= tasks.size()) {
            throw new ShrekException("Task index out of bounds");
        }
        return tasks.get(index);
    }

    /**
     * Removes a task from the task list by index.
     *
     * @param index the zero-based index of the task to remove
     * @return the removed task
     * @throws ShrekException if the index is out of bounds
     */
    public Task remove(int index) throws ShrekException {
        if (index < 0 || index >= tasks.size()) {
            throw new ShrekException("Task index out of bounds");
        }
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns all tasks in the task list.
     *
     * @return an ArrayList containing all tasks
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list contains no tasks, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
