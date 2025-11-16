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

    // @SuppressWarnings("unchecked") recommended here to silence the cast warning
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        List<String> todoList = new ArrayList<>(); // Initialize to empty list by default

        if (session != null) {
            // Retrieve attribute and cast it. If the attribute doesn't exist, this returns null.
            List<String> sessionList = (List<String>) session.getAttribute("todoList");

            // Crucial Check: If the attribute was found, use it. Otherwise, keep the empty list.
            if (sessionList != null) {
                todoList = sessionList;
            }
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head><title>System Status</title>");
        out.println("<style>");
        //Style
        out.println("body { font-family: monospace; background-color: #111; color: #00ff99; margin: 0; display: flex; justify-content: center; align-items: center; min-height: 100vh; }");
        out.println(".container { background-color: #222; padding: 40px; border: 1px solid #00ff99; box-shadow: 0 0 20px #00ff99; width: 80%; max-width: 600px; }");
        out.println("a { color: #00ccff; text-decoration: none; }");
        out.println("a:hover { text-decoration: underline; }");
        out.println("h2 { border-bottom: 1px solid #00ff99; padding-bottom: 10px; margin-bottom: 20px; }");
        out.println("li { margin-bottom: 10px; display: flex; justify-content: space-between; padding: 5px 0; border-bottom: 1px dotted #333; }");
        out.println("ol { list-style: none; padding-left: 0; }");
        out.println(".delete { color: #ff6666; font-weight: bold; }");
        out.println("p b { color: #ff0099; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        out.println("<div class='container'>");
        out.println("<h2>// CURRENT TASK QUEUE //</h2>"); // Futuristic heading

        out.println("<p>Active Tasks: <b>" + todoList.size() + "</b></p>");

        if(todoList.isEmpty()){
            out.println("<p>[STATUS: IDLE] The task queue is empty.</p>");
        }else{
            out.println("<ol>");
            for (int i = 0; i<todoList.size(); i++){
                // Applied .delete class to the link
                out.println("<li>" + todoList.get(i) +
                        " <a href='DeleteTodo?taskOrder=" + i + "' class='delete'>[TERMINATE]</a></li>");
            }
            out.println("</ol>");
        }
        // Futuristic link text
        out.println("<p><a href='index.html'>// RETURN TO INTERFACE //</a></p>");
        out.println("</div>");
        out.println("</body></html>");
    }
}