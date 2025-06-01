package JAVA.LeetCode;

import java.util.*;

public class 设计任务管理器 {
    private static class Task
    implements Comparable<Task>{
        final int taskId;
        int priority;
        Task(int taskId,int priority){
            this.taskId=taskId;
            this.priority=priority;
        }
        public int compareTo(Task task){
            return priority==task.priority?
                task.taskId-taskId:task.priority-priority;
        }
        public int hashCode(){
            return Objects.hash(taskId,priority);
        }
        public boolean equals(Object tsk){
            if(tsk==this) return true;
            if(tsk==null) return false;
            return tsk instanceof Task t
                &&taskId==t.taskId
                &&priority==t.priority;
        }
    }
    private static class TaskManager{
        Map<Integer,Task> idTasks;
        TreeMap<Task,Integer> allTasks;
        TaskManager(List<List<Integer>> tasks) {
            this.idTasks=new HashMap<>();
            this.allTasks=new TreeMap<>();
            tasks.forEach(tsk->{
                int userId=tsk.get(0),taskId=tsk.get(1),priority=tsk.get(2);
                var task=new Task(taskId,priority);
                idTasks.put(taskId,task);
                allTasks.put(task,userId);
            });
        }
        void add(int userId, int taskId, int priority) {
            var task=new Task(taskId,priority);
            idTasks.put(taskId,task);
            allTasks.put(task,userId);
        }
        void edit(int taskId, int newPriority) {
            var oldTsk=idTasks.get(taskId);
            var oldUserId=allTasks.remove(oldTsk);
            oldTsk.priority=newPriority;
            allTasks.put(oldTsk,oldUserId);
        }
        void rmv(int taskId) {
            allTasks.remove(idTasks.remove(taskId));
        }
        int execTop() {
            if(allTasks.isEmpty()){
                return -1;
            }else{
                var tsk=allTasks.firstKey();
                idTasks.remove(tsk.taskId);
                return allTasks.remove(tsk);
            }
        }
    }
    public static void main(String[] args) {
        TaskManager tm=new TaskManager(List.of(
            List.of(1,101,10),
            List.of(2,102,20),
            List.of(3,103,30)
        ));
        tm.add(4,104,5);
        tm.edit(102,8);
        System.out.println(tm.execTop());
        tm.rmv(101);
        tm.add(5,105,15);
        System.out.println(tm.execTop());
    }
}
