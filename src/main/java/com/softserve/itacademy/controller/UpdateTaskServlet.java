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

@WebServlet(value = "/edit-task")
public class UpdateTaskServlet extends HttpServlet {

    private TaskRepository taskRepository;
    private Task task;

    @Override
    public void init() {
        taskRepository = TaskRepository.getTaskRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        task = taskRepository.read(Integer.parseInt(request.getParameter("id")));
        if (task != null) {
            request.getRequestDispatcher("/WEB-INF/pages/edit-task.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/create-task");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        Priority priority = Priority.valueOf(request.getParameter("priority"));
        Task taskExists = null;
        for (Task t : taskRepository.all()) {
            if (t.getTitle().equals(title)) {
                taskExists = t;
                break;
            }
            if (taskExists != null) {
                if (task.getTitle().equals(taskExists.getTitle())) {
                    request.setAttribute("isEdited", true);
                    request.setAttribute("task", taskExists);
                    taskExists.setTitle(title);
                    taskExists.setPriority(priority);
                    response.sendRedirect("/tasks-list");
                } else {
                    request.setAttribute("isEdited", false);
                    request.setAttribute("task", taskExists);
                    request.setAttribute("priority", priority);
                    request.getRequestDispatcher("/WEB-INF/pages/edit-task.jsp").forward(request, response);
                }
            } else {
                task.setTitle(title);
                task.setPriority(priority);
                taskRepository.update(task);
                response.sendRedirect("/tasks-list");

            }
        }
    }
}

