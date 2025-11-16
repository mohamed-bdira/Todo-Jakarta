package org.example.todo;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/DeleteTodo")
public class DeleteTodo extends HttpServlet {

    @SuppressWarnings("unchecked") // Recommended to suppress unchecked cast warning
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String order =  request.getParameter("taskOrder");
        int taskOrder = -1;
        if(order != null){
            try{
                taskOrder = Integer.parseInt(order);
            }catch (NumberFormatException ignored){

            }
        }

        HttpSession session  = request.getSession(false);

        // if list exists get it
        List<String> todoList = (session != null)
                ? (List<String>) session.getAttribute("todoList")
                : null;

        if(todoList != null && taskOrder >= 0 && taskOrder < todoList.size()){
            todoList.remove(taskOrder);
        }

        //redirects the user to DisplayTodo
        response.sendRedirect("DisplayTodo");
    }
}