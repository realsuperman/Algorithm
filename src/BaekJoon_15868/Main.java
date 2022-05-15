package BaekJoon_15868;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static int MIN=Integer.MAX_VALUE;
    public static int M;
    public static int N;
    public static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        int[][] array = new int[N][N];
        int cnt = 0;
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                array[i][j] = Integer.parseInt(str[j]);
                if(array[i][j] == 2) cnt++;
            }
        }
        for(int i=0;i<M;i++) solution(array,cnt,0,i+1,0);
        System.out.println(MIN);
    }

    public static void solution(int[][] array,int target,int choice,int depth,int index){
        if(choice==depth){
            int[][] temp = new int[N][N];
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    temp[i][j] = array[i][j];
                }
            }
            int value = 0;
            int v1 = list.get(value);
            int v2 = 0;
            for(int j=0;j<N;j++){
                for(int k=0;k<N;k++){
                    if(temp[j][k] == 2) v2++;
                    if(temp[j][k] == 2&&v1==v2){
                        temp[j][k]=-1;
                        value++;
                        if(value>=list.size()) break;
                        v1 = list.get(value);
                    }
                }
            }


            for(int j=0;j<N;j++) {
                for (int k = 0; k < N; k++) {
                    if(temp[j][k]==2) temp[j][k] = 0;
                }
            }
            for(int j=0;j<N;j++) {
                for (int k = 0; k < N; k++) {
                    if(temp[j][k]==-1) temp[j][k] = 2;
                }
            }

            int a = minValue(temp);
            if(a!=0 && MIN > a) MIN = a;
            return;
        }
        for(int i=index;i<target;i++){
            list.add(i+1);
            int size = list.size();
            solution(array,target,choice+1,depth,i+1);
            list.remove(size-1);
        }
    }

    public static int minValue(int[][] array){
        int sum = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(array[i][j]==1){
                    int v = chicken(array,i,j);
                    if(v!=-1) sum+=v;
                }
            }
        }
        return sum;
    }

    public static int chicken(int[][] array,int v1,int v2){
        int value = -1;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(array[i][j]==2){
                    int a = Math.abs(v1-i) + Math.abs(v2-j);
                    if(value==-1){
                        value = a;
                    }else{
                        if(value>a) value = a;
                    }

                }
            }
        }
        return value;
    }
}