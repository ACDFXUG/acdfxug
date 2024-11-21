package LeetCode;

import java.util.*;
import java.util.stream.*;

public class 点菜展示表 {
    static List<List<String>> displayTable(List<List<String>> orders) {
        var odrs=new TreeMap<Integer,HashMap<String,Integer>>();
        final var foods=new TreeSet<String>();
        orders.forEach(L->{
            Integer table=Integer.valueOf(L.get(1));
            String food=L.get(2);
            foods.add(food);
            odrs.computeIfAbsent(table,$->new HashMap<>())
                .merge(food,1,(a,b)->a+b);
        });
        List<List<String>> ans=new ArrayList<List<String>>(odrs.size()+1);
        var head=new ArrayList<String>(foods.size()+1){{
            add("Table");
            addAll(foods);
        }};
        ans.add(head);
        odrs.forEach((table,odr)->{
            List<String> row=new ArrayList<>(foods.size()+1);
            row.add(table.toString());
            foods.forEach(food->row.add(
                odr.getOrDefault(food,0).toString()
            ));
            ans.add(row);
        });
        return ans;
    }
    public static void main(String[] args) {
        final String[][] orders={
            {"David","3","Ceviche"},
            {"Corina","10","Beef Burrito"},
            {"David","3","Fried Chicken"},
            {"Carla","5","Water"},
            {"Carla","5","Ceviche"},
            {"Rous","3","Ceviche"}
        };
        List<List<String>> ans=displayTable(Arrays.stream(orders)
            .map(Arrays::asList)
            .collect(Collectors.toList()));
        ans.forEach(System.out::println);
    }
}
