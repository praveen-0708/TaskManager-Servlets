package com.praveen;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTaskRepository implements TaskRepository{
    List<Task> listOfTasks =new ArrayList<Task>();
    public void addTask(Task n){
        listOfTasks.add(n);
    }
    public boolean deleteFromList(int id){
        for(Task i: listOfTasks) {
            if (i.getId()==id) {
                listOfTasks.remove(i);
                return true;
            }
        }
        return false;
    }
    public List<Task> getList(){
        return listOfTasks;
    }
    public Task searchById(int id){
        for(Task s: listOfTasks) {
            if (s.getId()==id) {
                return s;
            }
        }
        return null;
    }
    public List<Task> listByStatus(TaskStatus s){
        List<Task> listOfStatus=new ArrayList<Task>();
        for(Task i: listOfTasks){
            if(i.getStatus().equals(s))
                listOfStatus.add(i);
        }
        return listOfStatus;
    }
    public int getTotalTasks(){
        return listOfTasks.size();
    }
    public void updateStatus(TaskStatus status,int id){
        for(Task s: listOfTasks){
            if(s.getId()==id){
                s.setStatus(status);
            }
        }
    }

    @Override
    public List<Task> getPendingStatus() {
        return null;
    }

    @Override
    public List<Task> getCurrentDateTask() {
        return null;
    }

}
