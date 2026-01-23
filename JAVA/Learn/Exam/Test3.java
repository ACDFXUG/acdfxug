package Java.Learn.Exam;

import java.time.*;
import java.time.format.*;
import java.util.*;

public class Test3 {
    static final String info=
    "10001,张无忌,男,2023-07-22 11:11:12,东湖-黄鹤楼"+
    "#10002,赵敏,女,2023-07-22 09:11:21,黄鹤楼-归元禅寺"+
    "#10003,周芷若,女,2023-07-22 04:11:21,木兰文化区-东湖"+
    "#10004,小昭,女,2023-07-22 08:11:21,东湖"+
    "#10005,灭绝,女,2023-07-22 17:11:21,归元禅寺";
    static final DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {
        String[] students=info.split("#");
        List<Student> stus=new ArrayList<>(5);
        for(var student:students){
            String[] infos=student.split(",");
            long id=Long.parseLong(infos[0]);
            String name=infos[1];
            boolean isMale=infos[2].equals("男");
            var chooseTime=LocalDateTime.parse(infos[3],dtf);
            List<String> chooseAddr=new ArrayList<>(2);
            if(infos[4].contains("-")){
                String[] addrs=infos[4].split("-");
                chooseAddr.add(addrs[0]);
                chooseAddr.add(addrs[1]);
            }else{
                chooseAddr.add(infos[4]);
            }
            stus.add(new Student(id,name,isMale,chooseTime,chooseAddr));
        }
        Map<String,Integer> counter=new HashMap<>(5);
        stus.forEach(s->s.chooseAddr.forEach(addr->counter.merge(addr,1,Integer::sum)));
        var list=counter.entrySet().stream()
            .sorted((E1,E2)->E2.getValue()-E1.getValue())
            .toList();
        list.forEach(e->System.out.println(e.getKey()+" "+e.getValue()));
        System.out.println(list.get(0).getKey()+"想去的人最多,有"+list.get(0).getValue()+"人");
        for(var student:stus){
            if(!student.chooseAddr.contains(list.get(0).getKey())){
                System.out.println(student.name+"没有选择去"+list.get(0).getKey());
            }
        }
        
    }
}


class Student{
    long id;
    String name;
    boolean isMale;
    LocalDateTime chooseTime;
    List<String> chooseAddr;
    Student(long id,String name,boolean isMale,LocalDateTime chooseTime,List<String> chooseAddr){ 
        this.id=id;
        this.name=name;
        this.isMale=isMale;
        this.chooseTime=chooseTime;
        this.chooseAddr=chooseAddr;
    }
}