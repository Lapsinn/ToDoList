package org.todo.todolist;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class ToDoList {
    public enum Hierarchy{
        IMPORTANT(4),
        HIGH(3),
        MEDIUM(2),
        LOW(1);

        private final int order;

        Hierarchy(int order) {
            this.order = order;
        }
        public int getOrder() {
            return order;
        }
    }
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyy");

    Hierarchy taskImportance = Hierarchy.LOW;
    String taskName;
    LocalDate deadline;

    ArrayList<Tasks> taskList = new ArrayList<>();
    ArrayList<Activity> activityTasklist = new ArrayList<>();
    ArrayList<Events> eventsList = new ArrayList<>();
    ArrayList<ToDoList> doneList = new ArrayList<>();

    public ToDoList(){

    }

    public void addTask(Tasks task) {
        taskList.add(task);

        for(int i = 0; i<taskList.size()-1; i++){
            for(int j = 0; j<taskList.size()-1 - i; j++) {
                if (!taskList.get(j).deadline.isBefore(taskList.get(j + 1).deadline)) {
                    LocalDate temp = taskList.get(j).deadline;
                    taskList.get(j).deadline = taskList.get(j + 1).deadline;
                    taskList.get(j + 1).deadline = temp;
                }
            }
        }
    }
    public void addActivity(Activity activity){
        activityTasklist.add(activity);

        for(int i = 0; i<activityTasklist.size()-1; i++){
            for(int j = 0; j<activityTasklist.size()-1 - i; j++) {
                if (!activityTasklist.get(j).deadline.isBefore(activityTasklist.get(j + 1).deadline)) {
                    LocalDate temp = activityTasklist.get(j).deadline;
                    activityTasklist.get(j).deadline = activityTasklist.get(j + 1).deadline;
                    activityTasklist.get(j + 1).deadline = temp;
                }
            }
        }
    }
    public void addEvent(Events event){
        eventsList.add(event);

        for(int i = 0; i<eventsList.size()-1; i++){
            for(int j = 0; j<eventsList.size()-1 - i; j++) {
                if (!eventsList.get(j).deadline.isBefore(eventsList.get(j + 1).deadline)) {
                    LocalDate temp = eventsList.get(j).deadline;
                    eventsList.get(j).deadline = eventsList.get(j + 1).deadline;
                    eventsList.get(j + 1).deadline = temp;
                }
            }
        }
    }
    public void deadLineChecker() {
        LocalDate current = LocalDate.now();
        for (Tasks t : taskList) {
            if (!current.isBefore(t.deadline)) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, "Deadline reached for: " + t.taskName, "Deadline Notification", JOptionPane.WARNING_MESSAGE);
                });
                doneList.add(t);
            }
        }
        taskList.removeAll(doneList);

        for (Activity t : activityTasklist) {
            if (!current.isBefore(t.deadline)) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, "Deadline reached for: " + t.taskName, "Deadline Notification", JOptionPane.WARNING_MESSAGE);
                });
                doneList.add(t);
            }
        }
        taskList.removeAll(doneList);

        for (Events t : eventsList) {
            if (!current.isBefore(t.deadline)) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, "Deadline reached for: " + t.taskName, "Deadline Notification", JOptionPane.WARNING_MESSAGE);
                });
                doneList.add(t);
            }
        }
        taskList.removeAll(doneList);
    }
}

