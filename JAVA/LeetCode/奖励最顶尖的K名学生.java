package JAVA.LeetCode;

import java.util.*;

public class 奖励最顶尖的K名学生 {
    private static class Student
    implements Comparable<Student>{
        final int id;
        int score;
        Student(int id){
            this.id=id;
            this.score=0;
        }
        public int compareTo(Student stu){
            return score==stu.score?id-stu.id:stu.score-score;
        }
        public boolean equals(Object stu){
            if(stu==this) return true;
            if(stu==null) return false;
            return stu instanceof Student s
                &&id==s.id&&score==s.score;
        }
        public int hashCode(){
            return Objects.hash(id,score);
        }
    }
    static List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        final int n=report.length;
        Set<String> positive=new HashSet<>(List.of(positive_feedback));
        Set<String> negative=new HashSet<>(List.of(negative_feedback));
        var students=Arrays.stream(student_id)
            .mapToObj(Student::new)
            .toList();
        for(int i=0;i<n;++i){
            var rpts=report[i].split(" ");
            var stu=students.get(i);
            for(var rpt:rpts){
                if(positive.contains(rpt)) stu.score+=3;
                else if(negative.contains(rpt)) --stu.score;
            }
        }
        return students.stream()
            .sorted()
            .limit(k)
            .map(stu->stu.id)
            .toList();
    }
    public static void main(String[] args) {
        String[] positive={"smart","brilliant","studious"};
        String[] negative={"not"};
        String[] report={"this student is not studious","the student is smart"};
        int[] id={1,2};
        System.out.println(topStudents(positive,negative,report,id,2));
    }
}
