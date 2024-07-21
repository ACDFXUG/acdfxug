package JAVA;

public class 卷积 {
    static int hadamard(int[][] a,int[][] b){
        int sum=0;
        for(int i=0;i<a.length;i++){
            for(int j=0;j<b.length;j++){
                sum+=a[i][j]*b[i][j];
            }
        }
        return sum;
    }
    static int[][] subMat(int[][] pfill,int l,int step,int x,int y){
        int[][] ans=new int[l][l];
        for(int i=0;i<l;i++){
            for(int j=0;j<l;j++){
                ans[i][j]=pfill[step*i+x][step*j+y];
            }
        }
        return ans;
    }
    static int[][] convolution(int[][] p,int[][] core,int step,int zerofill){
        int[][] pfill=new int[p.length+2*zerofill][p[0].length+2*zerofill];
        for(int i=0;i<p.length;i++){
            for(int j=0;j<p[0].length;j++){
                pfill[i+zerofill][j+zerofill]=p[i][j];
            }
        }
        int L=(pfill.length-core.length)/step+1;
        int[][] conv=new int[L][L];
        for(int i=0;i<L;i++){
            for(int j=0;j<L;j++){
                conv[i][j]=hadamard(subMat(pfill,core.length,step,i,j),core);
            }
        }
        return conv;
    }
    public static void main(String[] args) {
        int[][] p={
            {1,1,1,1,1},
            {-1,0,-3,0,1},
            {2,1,1,-1,0},
            {0,-1,1,2,1},
            {1,2,1,1,1},
        };
        int[][] core={
            {1,0,0},
            {0,0,0},
            {0,0,-1},
        };
        int[][] ans=convolution(p,core,1,1);
        for(int i=0;i<ans.length;i++){
            for(int j=0;j<ans[0].length;j++){
                System.out.print(ans[i][j]+" ");
            }
            System.out.println();
        }
    }
}
