package com.praveen;
import java.util.List;

public class TaskManager{
    TaskRepository repository=new DataBaseRepository();

    public void addTask(Task n){
        repository.addTask(n);
    }
    public boolean deleteFromList(int id){
        return repository.deleteFromList(id);
    }
    public List<Task> getList(){
        return repository.getList();
    }
    public Task searchById(int id){
        return repository.searchById(id);
    }
    public List<Task> listByStatus(TaskStatus s){
        return repository.listByStatus(s);
    }
    public int getTotalTasks(){
        return repository.getTotalTasks();
    }
    public void updateStatus(TaskStatus status,int id){
        repository.updateStatus(status,id);
    }
    public List<Task> getPendingStatus(){
        return repository.getPendingStatus();
    }
    public List<Task> getCurrentDateTask(){
        return repository.getCurrentDateTask();
    }

}