package BaekJoon_2239;

import java.io.*;

public class Main {
    public static int[][] array;
    public static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        array = new int[9][9];
        for(int i=0;i<9;i++){
            String[] str = br.readLine().split("");
            for(int j=0;j<9;j++){
                array[i][j] = Integer.parseInt(str[j]);
                if(array[i][j]==0) cnt++;
            }
        }
        back(0);
    }
    public static void back(int num){
        if(num==cnt){
            for(int i=0;i<9;i++){
                for(int j=0;j<9;j++){
                    System.out.print(array[i][j]+"");
                }
                System.out.println();
            }
            return;
        }
        int i=0,j=0;
        for(i=0;i<9;i++){
            boolean sw = false;
            for(j=0;j<9;j++){
                if(array[i][j]==0) {sw=true; break;}
            }
            if(sw) break;
        }

        for(int k=1;k<=9;k++){
            array[i][j]=k;
            if(check(i,j)){
                back(num+1);
                if(!chk()) return;
            }
        }
        array[i][j] = 0;
    }
    public static boolean check(int x,int y){
        int[] row = new int[10];
        int[] column = new int[10];
        for(int i=0;i<9;i++){
            row[array[i][y]]++;
            column[array[x][i]]++;
        }
        for(int i=1;i<=9;i++){
            if(row[i]>=2 || column[i]>=2) return false;
        }
        int startX = (x%9) - (x%3);
        int startY = (y%9) - (y%3);
        row = new int[10];
        for(int i=startX;i<startX+3;i++){
            for(int j=startY;j<startY+3;j++){
                row[array[i][j]]++;
                if(array[i][j]>0 && row[array[i][j]]>=2) return false;
            }
        }
        return true;
    }
    public static boolean chk(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(array[i][j]==0) return true;
            }
        }
        return false;
    }
}