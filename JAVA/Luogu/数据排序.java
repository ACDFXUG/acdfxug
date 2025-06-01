package JAVA.Luogu;

import java.util.*;
import java.lang.reflect.*;

public class 数据排序 {
    private static class CSVFile{
        Map<String,Integer> colName;
        List<Object[]> dataRow;
        List<Class<?>> types;
        final int COL;
        CSVFile(String title){
            this.colName=new HashMap<String,Integer>();
            this.dataRow=new ArrayList<>();
            this.types=new ArrayList<Class<?>>();
            String[] datas=title.split(",");
            this.COL=datas.length;
            for(int i=0;i<COL;i++){
                colName.put(datas[i],i);
            }
        }
        void addDataRow(String data){
            String[] row=data.split(",");
            Object[] datas=new Object[COL];
            for(int i=0;i<COL;i++){
                try{
                    datas[i]=Integer.valueOf(row[i]);
                    types.add(Integer.class);
                }catch(NumberFormatException nfe){
                    datas[i]=row[i];
                    types.add(String.class);
                }
            }
            dataRow.add(datas);
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        LinkedHashMap<String,Boolean> order=new LinkedHashMap<>();
        int n=sc.nextInt();
        String title=sc.next();
        CSVFile csv=new CSVFile(title);
        for(int i=1;i<n;i++){
            csv.addDataRow(sc.next());
        }
        int m=sc.nextInt();
        for(int i=0;i<m;i++){
            String ordered=sc.next();
            int L=ordered.length();
            String titleName=ordered.substring(0,L-1);
            order.put(titleName,ordered.charAt(L-1)=='+');
        }
        List<String> titleList=new ArrayList<>();
        order.keySet().forEach(titleList::add);
        csv.dataRow.sort((o1,o2)->{
            for(int i=0,l=titleList.size();i<l;){
                String dataName=titleList.get(i);
                int idx=csv.colName.get(dataName);
                try{
                    Method compareTo=csv.types.get(idx).getMethod("compareTo",Object.class);
                    if(i==l-1){
                        try{
                            return order.get(dataName)?
                            (int)compareTo.invoke(o1[idx],o2[idx]):
                            (int)compareTo.invoke(o2[idx],o1[idx]);
                        }catch(IllegalAccessException | InvocationTargetException i_e){
                            return 0;
                        }
                    }else{
                        if(o1[idx].equals(o2[idx])){
                            i++;
                        }else{
                            try{
                                return order.get(dataName)?
                                (int)compareTo.invoke(o1[idx],o2[idx]):
                                (int)compareTo.invoke(o2[idx],o1[idx]);
                            }catch(IllegalAccessException | InvocationTargetException i_e){
                                return 0;
                            }
                        }
                    }
                }catch(NoSuchMethodException nsme){
                    return 0;
                }
            }
            return 0;
        });
        StringJoiner SJ=new StringJoiner(",");
        csv.colName.entrySet().stream()
        .sorted((E1,E2)->{
            return E1.getValue().compareTo(E2.getValue());
        }).forEach(E->SJ.add(E.getKey()));
        System.out.println(SJ);
        csv.dataRow.forEach(Obj->{
            StringJoiner sj=new StringJoiner(",");
            for(int i=0;i<csv.COL;i++){
                sj.add(Obj[i].toString());
            }
            System.out.println(sj);
        });
        sc.close();
    }
}
