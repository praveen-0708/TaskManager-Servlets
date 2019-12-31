package com.servlets;

import com.praveen.Task;
import com.praveen.TaskManager;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Servlet extends HttpServlet {

    public Servlet(){

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TaskManager taskManager=new TaskManager();
        List<Task> listOfTasks=taskManager.getList();
        JSONArray jsonArray = new JSONArray(listOfTasks);
        PrintWriter out = resp.getWriter();

        out.println(jsonArray);
    }

}
