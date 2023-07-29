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
@WebServlet("/read-task")
public class ReadTaskServlet extends HttpServlet {

    private TaskRepository taskRepository;

    private int taskId;

    @Override
    public void init() {
        taskRepository = TaskRepository.getTaskRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        taskId = Integer.parseInt(request.getParameter("id"));
        Task task = TaskRepository.read(taskId);
        if (task != null) {
            request.setAttribute("task", task);
            request.getRequestDispatcher("/WEB-INF/pages/read-task.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/create-task");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response){

    }
}
