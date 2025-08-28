package task;

import instruction.Instruction;
import parser.Parser;
import storage.Storage;
import ui.Ui;
import util.ShrekException;
import java.util.Scanner;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) throws ShrekException {
        if (index < 0 || index >= tasks.size()) {
            throw new ShrekException("Task index out of bounds");
        }
        return tasks.get(index);
    }

    public Task remove(int index) throws ShrekException {
        if (index < 0 || index >= tasks.size()) {
            throw new ShrekException("Task index out of bounds");
        }
        return tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}