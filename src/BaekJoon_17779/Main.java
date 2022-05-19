package BaekJoon_17779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int MIN = Integer.MAX_VALUE;
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] array = new int[N+1][N+1];

        for(int i=1;i<=N;i++){
            String[] str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                array[i][j+1]= Integer.parseInt(str[j]);
            }
        }

        for(int x=1;x<=N;x++){
            for(int y=1;y<=N;y++){
                for(int d1=1;d1<=N;d1++){
                    for(int d2=1;d2<=N;d2++){
                        if((x+d1+d2<=N)&&(y-d1>=1)&&(y-d1<y)&&(y+d2<=N)){
                            solution(array,x,y,d1,d2);
                        }
                    }
                }
            }
        }
        System.out.println(MIN);

    }
    public static void solution(int[][] array,int x,int y,int d1,int d2){
        int[][] temp = new int[N+1][N+1];

        int i = x;
        int j = y;
        while(i<=x+d1 && j>=y-d1){
            temp[i][j] = 5;
            i = i+1;
            j = j-1;
        }

        i = x; j = y;
        while(i<=x+d2 && j<=y+d2){
            temp[i][j] = 5;
            i = i+1;
            j = j+1;
        }

        i = x+d1; j = y-d1;
        while(i<=x+d1+d2 && j<=y-d1+d2){
            temp[i][j] = 5;
            i = i+1;
            j = j+1;
        }

        i = x+d2; j = y+d2;
        while(i<=x+d2+d1 && j>=y+d2-d1){
            temp[i][j] = 5;
            i = i+1;
            j = j-1;
        }

        for(i=1;i<=N;i++){
            int cnt = 0;
            for(j=1;j<=N;j++){
                if(temp[i][j]==5) cnt++;
                if(cnt>=2){
                    for(int k=j-1;k>=0;k--){
                        if(temp[i][k]==5) break;
                        temp[i][k] = 5;

                    }
                    break;
                }
            }
        }

        for(int r=1;r<x+d1;r++){
            for(int c=1;c<=y;c++){
                if(temp[r][c]==5) continue;
                temp[r][c]=1;
            }
        }
        for(int r=1;r<=x+d2;r++){
            for(int c=y+1;c<=N;c++){
                if(temp[r][c]==5) continue;
                temp[r][c]=2;
            }
        }
        for(int r=x+d1;r<=N;r++){
            for(int c=1;c<y-d1+d2;c++){
                if(temp[r][c]==5) continue;
                temp[r][c]=3;
            }
        }
        for(int r=x+d2+1;r<=N;r++){
            for(int c=y-d1+d2;c<=N;c++){
                if(temp[r][c]==5) continue;
                temp[r][c]=4;
            }
        }

        int[] v = new int[5];
        for(i=1;i<=N;i++){
            for(j=1;j<=N;j++){
                if(temp[i][j]==1) v[0]+=array[i][j];
                else if(temp[i][j]==2) v[1]+=array[i][j];
                else if(temp[i][j]==3) v[2]+=array[i][j];
                else if(temp[i][j]==4) v[3]+=array[i][j];
                else v[4]+=array[i][j];
            }
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int value : v){
            if(max<value) max = value;
            if(min>value) min = value;
        }
        MIN = MIN>(max-min)?max-min:MIN;
    }
}