package com.softserve.itacademy.model;

import java.util.LinkedList;
import java.util.List;

public class TaskDao {
    private List<Task> tasks = new LinkedList<>();
    private static TaskDao taskDaoInstance = null;

    private TaskDao(){   }

    public static TaskDao getInstance() {
        if (taskDaoInstance == null) {
            taskDaoInstance = new TaskDao();
            return taskDaoInstance;
        }
        return taskDaoInstance;
    }

    public boolean create(Task task) {
        if (task != null) {
            return tasks.add(task);
        }
        return false;
    }

    public Task read(int id) {
        return tasks.stream().filter(task -> task.getId() == id).findFirst().orElse(null);
    }

    public boolean update(int id, Task task) {
        int index = tasks.indexOf(read(id));
        if(tasks.set(index, task) != null) {
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        return tasks.remove(read(id));
    }

    public List<Task> readAll() {
        return tasks;
    }
}
