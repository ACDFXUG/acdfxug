package JAVA.LeetCode;

import java.util.*;
import java.util.function.*;

public class 王位继承顺序 {
    private static class ThroneInheritance{
        Map<String,List<String>> children;
        Map<String,String> father;
        Set<String> isDead;
        BiFunction<String,Set<String>,String> successor;
        Set<String> order;
        final String kingName;
        ThroneInheritance(final String kingName) {
            this.kingName=kingName;
            this.children=new HashMap<>();
            this.father=new HashMap<>();
            this.isDead=new HashSet<>();
            this.order=new LinkedHashSet<>();
            this.successor=(name,order)->{
                if(!children.containsKey(name)
                    ||order.containsAll(children.get(name))){
                    if(name.equals(kingName)) return null;
                    else return successor.apply(father.get(name),order);
                }else{
                    for(var child:children.get(name)){
                        if(!order.contains(child)){
                            return child;
                        }
                    }
                    return null;
                }
            };
        }
        void birth(String parentName, String childName) {
            children.computeIfAbsent(parentName,$->new ArrayList<>()).add(childName);
            father.put(childName,parentName);
        }
        void death(String name) {
            isDead.add(name);
        }
        List<String> getInheritanceOrder() {
            order.clear();
            order.add(kingName);
            for(var suc=successor.apply(kingName,order);suc!=null;){
                order.add(suc);
                suc=successor.apply(suc,order);
            }
            return order.stream()
                .filter(name->!isDead.contains(name))
                .toList();
        }
    }
    public static void main(final String[] args) {
        ThroneInheritance ti=new ThroneInheritance("king");
        ti.birth("king","clyde");
        ti.death("king");
        ti.birth("clyde","shannon");
        System.out.println(ti.getInheritanceOrder());
        ti.birth("shannon","scott");
        ti.death("clyde");
        System.out.println(ti.getInheritanceOrder());
    }
}
