package BaekJoon_17779.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[][] array;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N][N];
        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++) array[i][j]= Integer.parseInt(str[j]);
        }

        for(int x=1;x<=N;x++){
            for(int y=1;y<=N;y++){
                for(int d1=1;d1<=N;d1++){
                    for(int d2=1;d2<=N;d2++){
                        if((x+d1+d2<=N)&&(y-d1>=1)&&(y-d1<y)&&(y+d2<=N)) solution(x,y,d1,d2);
                    }
                }
            }
        }

        System.out.println(result);
    }

    private static void solution(int x, int y,int d1, int d2){
        int[][] temp = new int[N][N];

        int i=x; int j=y;
        while(i<=x+d1 && j>=y-d1){
            temp[i-1][j-1]=5;
            i++;
            j--;
        }
        i=x; j=y;
        while(i<=x+d2 && j<=y+d2){
            temp[i-1][j-1]=5;
            i++;
            j++;
        }
        i=x+d1; j=y-d1;
        while(i<=x+d1+d2 && j<=y-d1+d2){
            temp[i-1][j-1]=5;
            i++;
            j++;
        }
        i=x+d2; j=y+d2;
        while(i<=x+d2+d1 && j>=y+d2-d1){
            temp[i-1][j-1]=5;
            i++;
            j--;
        }

        for(i=1;i<x+d1;i++){
            for(j=1;j<=y;j++){
                if(temp[i-1][j-1]==5) break;
                temp[i-1][j-1]=1;
            }
        }

        for(i=1;i<=x+d2;i++){
            for(j=N;j>=y+1;j--){
                if(temp[i-1][j-1]!=0) break;
                temp[i-1][j-1]=2;
            }
        }

        for(i=x+d1;i<=N;i++){
            for(j=1;j<y-d1+d2;j++){
                if(temp[i-1][j-1]!=0) break;
                temp[i-1][j-1]=3;
            }
        }

        for(i=x+d2+1;i<=N;i++){
            for(j=N;j>=y-d1+d2;j--){
                if(temp[i-1][j-1]!=0) break;
                temp[i-1][j-1]=4;
            }
        }

        for(i=0;i<N;i++) for(j=0;j<N;j++) if(temp[i][j]==0) temp[i][j]=5;

        int[] sum = new int[5];
        for(i=0;i<N;i++) for(j=0;j<N;j++) sum[temp[i][j]-1]+=array[i][j];

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int v : sum){
            max = max<v?v:max;
            min = min>v?v:min;
        }
        result = result>max-min?max-min:result;
    }
}