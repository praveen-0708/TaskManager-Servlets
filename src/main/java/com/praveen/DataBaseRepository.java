package com.praveen;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBaseRepository implements TaskRepository {

    public Statement stmt;
    public Connection con;

    public DataBaseRepository() {
        establishConnection();
    }

    public void establishConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskmanager", "user", "password");
            stmt = con.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTask(Task task) {

        try {

            String query = "insert into task values(" + task.getId() + ",'" + task.getName()
                    + "','" + task.getDescription() + "','" + dateToString(task.getDate(), "yyyy/MM/dd")
                    + "','" + task.getStatus() + "')";


            stmt.executeUpdate(query);

        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean deleteFromList(int taskId) {

        int totalTasksBeforeDelete = getTotalTasks();

        int totalTasksAfterDelete;
        try {
            //Statement stmt=con.createStatement();
            String query = "delete from task where id=" + taskId;
            stmt.executeUpdate(query);

            totalTasksAfterDelete = getTotalTasks();
            if (totalTasksBeforeDelete == totalTasksAfterDelete + 1)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Task> getList() {
        List<Task> tasks = new ArrayList<Task>();
        try {

            //Statement stmt = c.createStatement();

            String query = "select * from task";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
                tasks.add(createTask(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public Task searchById(int taskId) {
        Task task = null;
        try {
            //Statement stmt = con.createStatement();
            String query = "select * from task where id=" + taskId;
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                task = createTask(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public List<Task> listByStatus(TaskStatus status) {
        List<Task> tasks = new ArrayList<Task>();
        try {
            //Statement stmt = con.createStatement();
            String query = "select * from task where status='" + status + "'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Task task = createTask(rs);
                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public int getTotalTasks() {

        int totalTasks = 0;
        try {
            //Statement stmt = con.createStatement();
            String query = "select count(*) from task";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                totalTasks = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalTasks;
    }

    @Override
    public void updateStatus(TaskStatus status, int taskId) {
        try {
            //Statement stmt = con.createStatement();
            String query = "update task set status='" + status + "' where id=" + taskId;
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Task> getPendingStatus() {
        List<Task> tasks = new ArrayList<Task>();
        try {
            //Statement stmt = con.createStatement();
            String query = "select * from task where status='IN_PROGRESS' or status='CREATED' order by date asc";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Task task = createTask(rs);
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public List<Task> getCurrentDateTask() {
        List<Task> tasks = new ArrayList<Task>();
        String currentDate = dateToString(new Date(), "yyyy/MM/dd");
        try {
            // Statement stmt = con.createStatement();
            String query = "select * from task where date='" + currentDate + "'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                Task task = createTask(rs);
                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public Date stringToDate(String date, String format) {
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String dateToString(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public void printData(ResultSet rs) {
        try {

            System.out.println("ID:" + rs.getInt(1) +
                    "\nTask Name:" + rs.getString(2) +
                    "\nDescription:" + rs.getString(3) +
                    "\nDue Date:" + dateToString(rs.getDate(4), "dd/MM/yyyy") +
                    "\nStatus:" + rs.getString(5) + "\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Task createTask(ResultSet resultSet) {
        Task task = new Task();
        try {
            task.setId(resultSet.getInt(1));
            task.setName(resultSet.getString(2));
            task.setDescription(resultSet.getString(3));
            task.setDate(resultSet.getDate(4));
            task.setStatus(TaskStatus.valueOf(resultSet.getString(5)));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return task;
    }

}
