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

@WebServlet("/DisplayTodo")
public class DisplayTodo extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        List <String> todoList = (session != null) ? (List<String>) session.getAttribute("todoList") : new ArrayList<>();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>To-Do List</title></head><body>");
        out.println("<h2>Current To-Do List</h2>");
        out.println("<p>Number of tasks: <b>" + todoList.size() + "</b></p>");

        if(todoList.isEmpty()){
            out.println("<p>The list is empty</p>");
        }else{
            out.println("<ol>");

            for (int i = 0; i<todoList.size(); i++){
                out.println("<li>" + todoList.get(i) + " - <a href='DeleteTodo?taskOrder=" + i + "'>Delete</a></li>");
            }
            out.println("</ol>");
        }
        out.println("<p><a href='index.html'>Go back to the main page</a></p>");
        out.println("</body></html>");
    }
}
