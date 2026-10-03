package Java.LeetCode;

import java.util.*;

public class 账户合并 {
    static List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> ans=new ArrayList<>();
        Map<String,String> names=new HashMap<>();
        HashMap<String,TreeSet<String>> emails=new HashMap<>();
        accounts.forEach(L->{
            String name=L.get(0);
            if(!emails.containsKey(name)){
                names.put(name,name);
                TreeSet<String> email=new TreeSet<>();
                for(int i=1;i<L.size();++i) email.add(L.get(i));
                emails.put(name,email);
            }else{
                var set=emails.get(name);
                boolean contain=false;
                for(int i=1;i<L.size();++i){
                    if(set.contains(L.get(i))){
                        for(int j=1;j<L.size();++j){
                            emails.get(name).add(L.get(j));
                        }
                        contain=true;
                        break;
                    }
                }
                if(!contain){
                    TreeSet<String> NEW=new TreeSet<>();
                    for(int i=1;i<L.size();++i) NEW.add(L.get(i));
                    emails.put(name+"1",NEW);
                    names.put(name+"1",names.get(name));
                }
            }
        });
        emails.forEach((name,email)->{
            List<String> sub=new ArrayList<>(){{
                add(names.get(name));
            }};
            email.forEach(sub::add);
            ans.add(sub);
        });
        return ans;
    }
    public static void main(String[] args) {
        List<List<String>> accounts=new ArrayList<>(){{
            add(List.of("John", "johnsmith@mail.com", "john00@mail.com"));
            add(List.of("John", "johnnybravo@mail.com"));
            add(List.of("John", "johnsmith@mail.com", "john_newyork@mail.com"));
            add(List.of("Mary", "mary@mail.com"));
        }};
        System.out.println(accountsMerge(accounts));
    }
}
