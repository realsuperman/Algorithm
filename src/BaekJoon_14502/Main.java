package BaekJoon_14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class Main {
    public static int MAX = 0;

    public static boolean[][] chk;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");

        int N = Integer.parseInt(str[0]);

        int M = Integer.parseInt(str[1]);

        int[][] array = new int[N][M];

        chk = new boolean[N][M];



        for(int i=0;i<N;i++){

            str = br.readLine().split(" ");

            for(int j=0;j<str.length;j++){

                array[i][j] = Integer.parseInt(str[j]);

            }

        }

        solution(array,0,N,M);

        System.out.println(MAX);

    }



    public static void solution(int[][] array,int depth,int N,int M){

        if(depth==3){

            int v = bfs(array,N,M);

            MAX = MAX<v?v:MAX;

            return;

        }



        for(int i=0;i<array.length;i++){

            for(int j=0;j<array[i].length;j++){

                if(array[i][j]==0 && !chk[i][j]){

                    array[i][j]=1;

                    chk[i][j]=true;

                    solution(array,depth+1,N,M);

                    array[i][j]=0;

                    chk[i][j]=false;

                }

            }

        }

    }



    public static int bfs(int[][] array,int N,int M){

        boolean[][] check = new boolean[N][M];

        Queue<V> queue = new LinkedList<>();

        int[][] temp = new int[N][M];

        for(int i=0;i<array.length;i++){

            for(int j=0;j<array[i].length;j++){

                temp[i][j] = array[i][j];

            }

        }





        for(int i=0;i<temp.length;i++){

            for(int j=0;j<temp[i].length;j++){

                if(temp[i][j]==2){

                    queue.add(new V(i,j));

                    check[i][j]=true;

                }

            }

        }



        while(!queue.isEmpty()){

            V v = queue.remove();

            if(v.x-1>=0 && !check[v.x-1][v.y] && temp[v.x-1][v.y]!=1){

                check[v.x-1][v.y]=true;

                temp[v.x-1][v.y]=2;

                queue.add(new V(v.x-1,v.y));

            }

            //System.out.println(v.x+1+" "+v.y);

            if(v.x+1<N && !check[v.x+1][v.y] && temp[v.x+1][v.y]!=1){

                check[v.x+1][v.y]=true;

                temp[v.x+1][v.y]=2;

                queue.add(new V(v.x+1,v.y));

            }

            if(v.y+1<M && !check[v.x][v.y+1] && temp[v.x][v.y+1]!=1){

                check[v.x][v.y+1]=true;

                temp[v.x][v.y+1]=2;

                queue.add(new V(v.x,v.y+1));

            }

            if(v.y-1>=0 && !check[v.x][v.y-1] && temp[v.x][v.y-1]!=1){

                check[v.x][v.y-1]=true;

                temp[v.x][v.y-1]=2;

                queue.add(new V(v.x,v.y-1));

            }

        }



        return search(temp);

    }



    public static int search(int[][] array){

        int cnt=0;

        for(int i=0;i<array.length;i++){

            for(int j=0;j<array[i].length;j++){

                if(array[i][j]==0) cnt++;

            }

        }

        return cnt;

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