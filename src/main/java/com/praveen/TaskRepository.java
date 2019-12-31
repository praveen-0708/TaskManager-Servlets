package com.praveen;

import java.util.List;

public interface TaskRepository {
    void addTask(Task task);
    boolean deleteFromList(int taskId);
    List<Task> getList();
    Task searchById(int taskId);
    List<Task> listByStatus(TaskStatus status);
    int getTotalTasks();
    void updateStatus(TaskStatus status, int taskId);
    List<Task> getPendingStatus();
    List<Task> getCurrentDateTask();
}

//
//public class TaskList implements ArrayList<Task>