package BaekJoon_17144.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int R,C,T;
    static int[][] array;
    static int airMachine1, airMachine2; // 열은 0 고정
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        R = Integer.parseInt(str[0]);
        C = Integer.parseInt(str[1]);
        T = Integer.parseInt(str[2]);
        array = new int[R][C];
        for(int i=0;i<R;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<C;j++){
                array[i][j]= Integer.parseInt(str[j]);
                if(airMachine1==0 && array[i][j]==-1){
                    airMachine1 = i;
                    airMachine2 = i+1;
                }
            }
        }

        while(T-->0){
            distribute(); // 확산
            lotate(); // 공기청정기
        }
        System.out.println(sumArrayValue());
    }

    public static void distribute(){
        int[][] temp = new int[R][C];
        for(int i=0;i<R;i++) for(int j=0;j<C;j++) temp[i][j]=array[i][j];

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(array[i][j]>0){
                    int count = 0;
                    int distributeValue = array[i][j]/5;
                    for(int index=0;index<4;index++){
                        int x = dx[index]+i;
                        int y = dy[index]+j;
                        if(x<0 || y<0 || x>=R || y>=C || array[x][y]==-1) continue;
                        count++;
                        temp[x][y]+=distributeValue;
                    }
                    temp[i][j] = temp[i][j] - (distributeValue*count);
                }
            }
        }
        array = temp;
    }

    public static void lotate(){
        int x = airMachine1-1;
        int y = 0;
        for(;x>0;x--) array[x][y] = array[x-1][y];
        for(;y<C-1;y++) array[x][y]=array[x][y+1];
        for(;x<airMachine1;x++) array[x][y]=array[x+1][y];
        for(;y>1;y--) array[x][y]=array[x][y-1];
        array[x][y]=0;

        x = airMachine2+1;
        y = 0;
        for(;x<R-1;x++) array[x][y]=array[x+1][y];
        for(;y<C-1;y++) array[x][y]=array[x][y+1];
        for(;x>airMachine2;x--) array[x][y] = array[x-1][y];
        for(;y>1;y--) array[x][y]=array[x][y-1];
        array[x][y]=0;
    }

    public static int sumArrayValue(){
        int sum = 0;
        for(int i=0;i<R;i++) for(int j=0;j<C;j++) if(array[i][j]>0) sum+=array[i][j];
        return sum;
    }
}