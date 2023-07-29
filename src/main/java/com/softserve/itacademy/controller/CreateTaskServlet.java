package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

 
public class CreateTaskServlet extends HttpServlet {

    private TaskRepository taskRepository;

    @Override
    public void init() {
        taskRepository = TaskRepository.getTaskRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/create-task.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("title");
        String priorityString = request.getParameter("priority");
        Priority priority = Priority.valueOf(priorityString);

        if (title == null || title.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Task name cannot be empty!");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/create-task.jsp");
            requestDispatcher.forward(request, response);
            return;
        }

        Task existingTask = taskRepository.findByTitle(title);

        if (existingTask != null) {
            request.setAttribute("errorMessage", "Task with a given name already exists!");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/create-task.jsp");
            requestDispatcher.forward(request, response);
        } else {
            Task task = new Task(title, priority);
            taskRepository.create(task);
            response.sendRedirect(request.getContextPath() + "/create-task");
        }
    }
}
