package BaekJoon_15684;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N,M,H,result=-1;
    static boolean[][][] connect;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        H = Integer.parseInt(str[2]);
        connect = new boolean[H+2][N+1][N+1];
        for(int i=0;i<M;i++){
            str = br.readLine().split(" ");
            int from = Integer.parseInt(str[0]);
            int to = Integer.parseInt(str[1]);
            connect[from][to][to+1]=true;
            connect[from][to+1][to]=true;
        }
        boolean chk = false;
        for(int i=1;i<=N;i++) if(!ladder(i)){chk=true; break;}
        if(!chk){System.out.println(0); return;}
        for(int i=1;i<=3;i++){ // 사다리 설치 횟수
            solution(0,i);
            if(result>-1) break;
        }
        System.out.println(result==-1?-1:result);
    }
    public static void solution(int start,int num){
        if(start==num) {
            boolean chk = false;
            for (int i = 1; i <= N; i++)
                if (!ladder(i)) {
                    chk = true;
                    break;
                }
            if (!chk) result = num;
            return;
        }
        for(int i=1;i<=H;i++){
            for(int j=1;j<=N;j++){
                if(j-1>=1 && !connect[i][j][j-1]){
                    if(j-2>=1 && connect[i][j-1][j-2]) continue;
                    if(j+1<=N && connect[i][j][j+1]) continue;
                    connect[i][j][j-1]=true;
                    connect[i][j-1][j]=true;
                    solution(start+1,num);
                    connect[i][j][j-1]=false;
                    connect[i][j-1][j]=false;

                }else if(j+1<=N && !connect[i][j][j+1]){
                    if(j+2<=N && connect[i][j+1][j+2]) continue;
                    if(j-1>=1 && connect[i][j][j-1]) continue;
                    connect[i][j][j+1]=true;
                    connect[i][j+1][j]=true;
                    solution(start+1,num);
                    connect[i][j][j+1]=false;
                    connect[i][j+1][j]=false;
                }
            }
        }

    }
    public static boolean ladder(int start){
        int x = start;
        int y = 1;
        int target=start;
        while(y<=H){
            if(x-1>=1 && connect[y][x][x-1]){
                x--;
                y++;
            }else if(x+1<=N && connect[y][x][x+1]){
                x++;
                y++;
            }else{
                y++;
            }
        }
        if(target==x) return true;
        return false;
    }
}