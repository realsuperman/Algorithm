package BaekJoon_2210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static Set<String> set = new HashSet<>();
    static int[][] array;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        array = new int[5][5];

        for(int i=0;i<5;i++){
            String[] str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++) array[i][j]= Integer.parseInt(str[j]);
        }

        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                dfs(i,j,1, String.valueOf(array[i][j]));
            }
        }
        System.out.println(set.size());
    }

    public static void dfs(int startX,int startY,int num,String str){
        if(num==6){
            set.add(str);
            return;
        }

        for(int i=0;i<4;i++){
            int x = startX+dx[i];
            int y = startY+dy[i];
            if(x<0 || x>=5 || y<0 || y>=5) continue;
            dfs(x,y,num+1,str+array[x][y]);
        }
    }
}