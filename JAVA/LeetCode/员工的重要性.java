package Java.LeetCode;

import java.util.*;

public class 员工的重要性 {
    private static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    };
    static int getImportance(List<Employee> employees, int id) {
        var imp=new HashMap<Integer,Employee>(employees.size()){{
            for(var e:employees) put(e.id,e);
        }};
        var q=new LinkedList<Integer>(){{add(id);}};
        var ans=0;
        while(!q.isEmpty()){
            var cur=q.poll();
            ans+=imp.get(cur).importance;
            imp.get(cur).subordinates.forEach(q::add);
        }
        return ans;
    }
    public static void main(String[] args) {
        
    }
    
}
