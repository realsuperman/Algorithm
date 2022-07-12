package BaekJoon_17406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N,M,K;
    static List<V> list = new ArrayList<>();
    static boolean[] check;
    static int MIN = Integer.MAX_VALUE;
    static int[][] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        K = Integer.parseInt(str[2]);
        List<Integer> order = new ArrayList<>();

        array = new int[N+1][M+1];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                array[i+1][j+1]= Integer.parseInt(str[j]);
            }
        }

        check = new boolean[K];
        for(int i=0;i<K;i++){
            str = br.readLine().split(" ");
            list.add(new V(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2])));
        }
        ordering(0,order);
        System.out.println(MIN);
    }

    public static void ordering(int num,List<Integer> order){
        if(num==K){
            int[][] temp = new int[N+1][M+1];
            for(int i=0;i<array.length;i++) for(int j=0;j<array[i].length;j++) temp[i][j]=array[i][j];

            for(int i=0;i<order.size();i++){
                V v = list.get(order.get(i));
                rotate(temp,v.r,v.c,v.s);
            }
            int value = cal(temp);
            MIN = MIN>value?value:MIN;
        }
        for(int i=0;i<K;i++){
            if(check[i]) continue;
            check[i]=true;
            order.add(i);
            int size = order.size();
            ordering(num+1,order);
            order.remove(size-1);
            check[i]=false;
        }
    }

    public static void rotate(int[][] array,int r,int c,int s){
        int startX = r-s;
        int startY = c-s;
        int endX = r+s;
        int endY = c+s;

        while(true){
            if(startX>=endX || startY>=endY) break;


            int x = startX;
            int y = startY;
            int temp = array[startX][startY];

            int cnt = 0;
            while(cnt<4){
                if(cnt==0){
                    for(int i=x+1;i<=endX;i++) array[i-1][y]=array[i][y];
                    x = endX;
                }else if(cnt==1){
                    for(int i=y+1;i<=endY;i++) array[x][i-1]=array[x][i];
                    y=endY;
                }else if(cnt==2){
                    for(int i=x-1;i>=startX;i--) array[i+1][y]=array[i][y];
                    x=startX;
                }else if(cnt==3){
                    for(int i=y-1;i>=startY;i--) array[x][i+1]=array[x][i];
                }
                cnt++;
            }
            array[startX][startY+1]=temp;

            startX++;
            startY++;
            endX--;
            endY--;
        }
    }

    public static int cal(int[][] array){
        int min = Integer.MAX_VALUE;

        for(int i=1;i<array.length;i++){
            int sum = 0;
            for(int j=1;j<array[i].length;j++) sum+=array[i][j];
            if(min>sum) min = sum;
        }
        return min;
    }

    public static void print(){
        System.out.println();
        for(int i=1;i<array.length;i++){
            for(int j=1;j<array[i].length;j++){
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
    }
}

class V{
    int r;
    int c;
    int s;
    public V(int r,int c,int s){
        this.r=r;
        this.c=c;
        this.s=s;
    }
}