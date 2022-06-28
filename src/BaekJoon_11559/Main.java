package BaekJoon_11559;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] array = new char[12][6];
        for(int i=0;i<12;i++){
            String[] str = br.readLine().split("");
            for(int j=0;j<str.length;j++) array[i][j]=str[j].charAt(0);
        }
        int cnt = 0;
        while(true){
            int number = 0;
            changeArray(array);
            char[][] copy = new char[12][6];
            for(int i=0;i<12;i++) for(int j=0;j<6;j++) copy[i][j]=array[i][j];

            number+=bfs(array,'R');
            number+=bfs(array,'G');
            number+=bfs(array,'B');
            number+=bfs(array,'P');
            number+=bfs(array,'Y');

            if(number>=1) cnt+=1;

            if(arrayCheck(copy,array)) break;
        }
        System.out.println(cnt);
    }
    public static void changeArray(char[][] array){
        for(int i=0;i<6;i++){
            for(int k=0;k<12;k++){
                for(int j=11;j>=0;j--){
                    if(array[j][i]=='.' && j>0){
                        array[j][i]=array[j-1][i];
                        array[j-1][i]='.';
                    }
                }
            }
        }
    }
    public static int bfs(char[][] array,char ch){
        int sum = 0;
        for(int i=0;i<12;i++){
            for(int j=0;j<6;j++){
                if(array[i][j]==ch){
                    Queue<V> queue = new LinkedList<>();
                    queue.add(new V(i,j));
                    sum+=solution(i,j,queue,array,ch);
                }
            }
        }
        return sum;
    }

    public static int solution(int X,int Y,Queue<V> queue,char[][] array,char ch){
        boolean[][] check = new boolean[12][6];
        check[X][Y]=true;
        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};
        Queue<V> temp = new LinkedList<>();
        temp.add(new V(X,Y));

        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x<0 || x>=12 || y<0 || y>=6) continue;
                if(!check[x][y] && array[x][y]==ch){
                    check[x][y]=true;
                    queue.add(new V(x,y));
                    temp.add(new V(x,y));
                }
            }
        }

        int cnt = 0;
        if(temp.size()>=4){
            while(!temp.isEmpty()){
                V v = temp.remove();
                array[v.x][v.y]='.';
            }
            cnt = 1;
        }
        return cnt;
    }

    public static boolean arrayCheck(char[][] from,char[][] to){
        for(int i=0;i<12;i++){
            for(int j=0;j<6;j++){
                if(from[i][j]!=to[i][j]) return false;
            }
        }
        return true;
    }

    public static void print(char[][] array){
        System.out.println("------------------");
        for(int i=0;i<12;i++){
            for(int j=0;j<6;j++){
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
    }
}

class V{
    int x;
    int y;
    public V(int x,int y){
        this.x=x;
        this.y=y;
    }
}
