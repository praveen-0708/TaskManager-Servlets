package com.servlets;

import com.praveen.Task;
import com.praveen.TaskManager;
import com.praveen.TaskStatus;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Servlet2 extends HttpServlet {
    TaskManager taskManager=new TaskManager();
    public Servlet2(){

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        Task task=taskManager.searchById(id);
        PrintWriter out = resp.getWriter();
        if(task==null)
        {
            resp.setStatus(404);
        }
        else{
            out.println(task);
            resp.setStatus(201);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        String status=req.getParameter("status");

        Task task=taskManager.searchById(id);
        if(task==null)
            resp.setStatus(404);
        else{
            taskManager.updateStatus(TaskStatus.valueOf(status),id);
            resp.setStatus(201);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        if(taskManager.deleteFromList(id))
            resp.setStatus(201);
        else
            resp.setStatus(404);
    }
}
