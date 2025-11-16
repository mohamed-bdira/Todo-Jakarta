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

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head><title>Task Added</title>");
        out.println("<style>");
        out.println("body { font-family: monospace; background-color: #111; color: #00ff99; margin: 0; display: flex; justify-content: center; align-items: center; min-height: 100vh; }");
        out.println(".container { background-color: #222; padding: 40px; border: 1px solid #00ff99; box-shadow: 0 0 20px #00ff99; text-align: center; }");
        out.println("a { color: #00ccff; text-decoration: none; display: block; margin-top: 20px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h2>// TASK LOG //</h2>");
        out.println("<p>The task has been added to the list. [STATUS: OK]</p>");
        out.println("<a href='index.html'>// SYSTEM RESTART //</a>");
        out.println("</div>");
        out.println("</body></html>");
    }
}