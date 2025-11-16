package org.example.todo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AddTodo")
public class AddTodo extends HttpServlet {

    protected void doGet(HttpServletRequest  request, HttpServletResponse  response) throws ServletException, IOException {
        String newTaskName = request.getParameter("taskName");
        HttpSession session = request.getSession();

        List<String> todoList = (List<String>) session.getAttribute("todoList");
        if(todoList == null){
            todoList = new ArrayList<>();
            session.setAttribute("todoList", todoList);
        }

        if(newTaskName !=null && !newTaskName.trim().isEmpty()){
            todoList.add(newTaskName.trim());
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>The task has been added to the list</h2>");
        out.println("<p><a href='index.html'>Back</a></p>");
        out.println("</body></html>");
    }
}