package Java.LeetCode;

import java.util.Arrays;

public class 比较版本号 {
    private static class Version implements Comparable<Version> {
        private String version;
        public Version(String version) {
            this.version=version;
        }
        public int compareTo(Version v){
            String[] v1=version.split("\\.");
            String[] v2=v.version.split("\\.");
            int[] V1=new int[v1.length];
            for(int i=0;i<v1.length;i++){
                V1[i]=Integer.parseInt(v1[i]);
            }
            int[] V2=new int[v2.length];
            for(int i=0;i<v2.length;i++){
                V2[i]=Integer.parseInt(v2[i]);
            }
            if(V1.length!=V2.length){
                if(V1.length>V2.length){
                    V2=Arrays.copyOf(V2,V1.length);
                }else{
                    V1=Arrays.copyOf(V1,V2.length);
                }
            }
            for(int i=0;i<V1.length;i++){
                if(V1[i]!=V2[i]){
                    return V1[i]-V2[i];
                }
            }
            return 0;
        }
    }
    static int compareVersion(String version1, String version2) {
        Version v1=new Version(version1);
        Version v2=new Version(version2);
        return v1.compareTo(v2)>0?1:v1.compareTo(v2)==0?0:-1;
    }
    public static void main(String[] args) {
        String version1="1.0.1";
        String version2="1";
        System.out.println(Arrays.toString(version1.split("\\.")));
        System.out.println(Arrays.toString(version2.split("\\.")));
        System.out.println(compareVersion(version1,version2));
    }
}
