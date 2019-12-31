package com.praveen;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {


    public static void main(String[] args) throws ParseException {
        int generateId;
        Scanner takeInput=new Scanner(System.in);
        TaskManager taskManager=new TaskManager();
        while(true) {
            System.out.print("------------\nMenu\n1.Add\n2.Display\n3.Delete\n4.Search\n" +
                    "5.List By Status\n6.Display Complete Details\n7.Total Tasks\n8.Update Status\n" +
                    "9.Display Pending Tasks\n10.Display Today's Tasks\n11.Exit\n");
            System.out.println("Enter choice:");
            int menuOption=takeInput.nextInt();
            switch (menuOption) {
                case 1:
                    generateId=100 + new Random().nextInt(900);
                    Task newTask=input(generateId);
                    taskManager.addTask(newTask);
                    break;
                case 2:
                    List<Task> listOfIdAndName=taskManager.getList();
                    if(listOfIdAndName.size()==0)
                        System.out.println("No Tasks!!");
                    else{
                        for(Task s:listOfIdAndName)
                            System.out.println("ID:"+s.getId()+"\nTask Name:"+s.getName());
                    }
                    break;
                case 3:
                    System.out.println("Enter task ID to delete:");
                    int id=takeInput.nextInt();
                    if(taskManager.deleteFromList(id))
                        System.out.println("Task Deleted!!");
                    else
                        System.out.println("Task Not Found!!");
                    break;
                case 4:
                    System.out.println("Enter task ID to search:");
                    Task foundTask=taskManager.searchById(takeInput.nextInt());
                    if(foundTask==null)
                        System.out.println("Not Found");
                    else
                        System.out.println(foundTask);
                    break;
                case 5:
                    System.out.println("Enter Status:");
                    TaskStatus s=TaskStatus.valueOf(takeInput.next());
                    List<Task> listOfStatusTasks=taskManager.listByStatus(s);
                    printList(listOfStatusTasks);
                    break;
                case 6:
                    List<Task> listOfAllDetails=taskManager.getList();
                    printList(listOfAllDetails);
                    break;
                case 7:
                    System.out.println("Total Tasks:"+taskManager.getTotalTasks());
                    break;
                case 8:
                    System.out.println("Enter Task ID:");
                    int taskId=takeInput.nextInt();
                    System.out.println("Enter New Status:");
                    String newStatus=takeInput.next();
                    taskManager.updateStatus(TaskStatus.valueOf(newStatus),taskId);
                    break;
                case 9:
                    System.out.println("Display Pending Tasks");
                    List<Task> pendingTasks=taskManager.getPendingStatus();
                    printList(pendingTasks);
                    break;
                case 10:
                    System.out.println("Display Today's Tasks");
                    List<Task> currentTasks=taskManager.getCurrentDateTask();
                    printList(currentTasks);

                    break;
                case 11:exit(0);
                default:System.out.println("Invalid Input");
            }
        }
    }
    public static Task input(int id) throws ParseException {
        Scanner takeInput=new Scanner(System.in);
        System.out.println("Enter task name:");
        String name=takeInput.nextLine();
        System.out.println("Enter description:");
        String description=takeInput.nextLine();
        System.out.println("Enter Due Date(dd/MM/yyyy):");
        Date date=new SimpleDateFormat("dd/MM/yyyy").parse(takeInput.nextLine());
        System.out.println("Enter status(CREATED,IN_PROGRESS,DONE):");
        TaskStatus status=TaskStatus.valueOf(takeInput.nextLine());
        return new Task(id,name,description,date,status);
    }
    public static void printList(List<Task> tasks){
        if(tasks.size()==0){
            System.out.println("No Tasks!!");
        }
        else{
            for(Task i:tasks)
            System.out.println(i);
        }
    }
}



