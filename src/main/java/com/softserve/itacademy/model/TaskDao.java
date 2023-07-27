package com.softserve.itacademy.model;

import java.util.LinkedList;
import java.util.List;

public class TaskDao {
    private List<Task> tasks = new LinkedList<>();

    public boolean create(Task task) {
        if (task != null) {
                if(findByName(task.getTitle()) != null) return false;
                return tasks.add(task);
        }
        return false;
    }

    public Task findByName(String title) {
        return tasks.stream().filter(t -> t.getTitle().equals(title)).findFirst().orElse(null);
    }

    public Task read(int id) {
        return tasks.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public boolean update(int id, Task task) {
        if (findByName(task.getTitle()) != null) {
            int index = tasks.indexOf(read(id));
            if(tasks.set(index, task) !=  null) {
                return true;
            } else return false;
        } else return false;
    }

    public boolean delete(int id) {
        return tasks.remove(read(id));
    }

    public List<Task> readAll() {
        return tasks;
    }
}
