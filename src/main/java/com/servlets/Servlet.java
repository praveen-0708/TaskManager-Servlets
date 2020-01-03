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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

public class Servlet extends HttpServlet {
    TaskManager taskManager=new TaskManager();
    public Servlet(){

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Task> listOfTasks=taskManager.getList();
        //JSONArray jsonArray = new JSONArray(listOfTasks);
        PrintWriter out = resp.getWriter();

        for(Task task:listOfTasks)
            out.println(task);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int generateId=100 + new Random().nextInt(900);
        String name=req.getParameter("name");
        String description=req.getParameter("desc");
        String date=req.getParameter("date");
        String status=req.getParameter("status");



        Task task=new Task();
        task.setId(generateId);
        task.setName(name);
        task.setDescription(description);
        try {
            task.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(date));
        } catch (ParseException e) {                                
            e.printStackTrace();
        }
        task.setStatus(TaskStatus.valueOf(status));

        taskManager.addTask(task);
        PrintWriter out = resp.getWriter();
        out.println("Task added!!");
        out.println(task);
        resp.setStatus(201);

    }
}
