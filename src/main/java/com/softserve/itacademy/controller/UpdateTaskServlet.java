package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit-task?id=<task ID>")
public class UpdateTaskServlet extends HttpServlet {

    private TaskRepository taskRepository;
    private int taskId;

    @Override
    public void init() {
        taskRepository = TaskRepository.getTaskRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        taskId = Integer.parseInt(request.getParameter("id"));
        Task task = taskRepository.read(taskId);
        if (task != null) {
            request.setAttribute("task", task);
            request.getRequestDispatcher("/WEB-INF/pages/edit-task.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/create-task");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Task task = taskRepository.read(taskId);

        if (task != null) {
            String title = request.getParameter("title");
            Priority priority = Priority.valueOf(request.getParameter("priority"));

            if (title != null && priority != null) {
                Task existingTask = taskRepository.findByTitle(title);

                if (existingTask != null && existingTask.getId() != taskId) {
                    request.setAttribute("errorMessage", "Task with a given name already exists!");
                } else {
                    task.setTitle(title);
                    task.setPriority(priority);
                    taskRepository.update(task);
                }
            } else {
                request.setAttribute("errorMessage", "Please provide all the necessary details!");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/create-task");
        }
        doGet(request, response);
    }

}

