package BaekJoon_3197;

import java.io.*;
import java.util.*;

public class Main{
    public static boolean flag = true;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int R = Integer.parseInt(str[0]);
        int C = Integer.parseInt(str[1]);
        Character[][] array = new Character[R][C];

        Queue<V> water = new LinkedList<>();
        Queue <V> queue = new PriorityQueue<>();
        boolean[][] chk = new boolean[R][C];
        int n = 1;

        for(int i=0;i<R;i++){
            str = br.readLine().split("");
            for(int j=0;j<C;j++){
                char ch = str[j].charAt(0);
                array[i][j] = ch;
                if(ch=='.'){
                    water.add(new V(i,j,flag));
                }else if(ch=='L'){
                    water.add(new V(i,j,flag));
                    if(n==1){
                        queue.add(new V(i,j,flag));
                        chk[i][j]=true;
                        n++;
                    }else{
                        array[i][j]='P';
                    }
                }
            }
        }

        n = 0;
        int[] value = new int[2];
        while(true){
            while(!queue.isEmpty()){
                V v = queue.remove();
                if(array[v.x][v.y]=='P'){System.out.println(n); return;}
                if(v.csh!=flag){queue.add(v); break;}

                if(v.x+1<R && !chk[v.x+1][v.y]){
                    chk[v.x+1][v.y]=true;
                    if(array[v.x+1][v.y]=='X') queue.add(new V(v.x+1,v.y,!flag));
                    else queue.add(new V(v.x+1,v.y,flag));
                }

                if(v.x-1>=0 && !chk[v.x-1][v.y]){
                    chk[v.x-1][v.y]=true;
                    if(array[v.x-1][v.y]=='X') queue.add(new V(v.x-1,v.y,!flag));
                    else queue.add(new V(v.x-1,v.y,flag));
                }

                if(v.y+1<C && !chk[v.x][v.y+1]){
                    chk[v.x][v.y+1]=true;
                    if(array[v.x][v.y+1]=='X') queue.add(new V(v.x,v.y+1,!flag));
                    else queue.add(new V(v.x,v.y+1,flag));
                }

                if(v.y-1>=0 && !chk[v.x][v.y-1]){
                    chk[v.x][v.y-1]=true;
                    if(array[v.x][v.y-1]=='X') queue.add(new V(v.x,v.y-1,!flag));
                    else queue.add(new V(v.x,v.y-1,flag));
                }
            }

            value[0] = 0;
            value[1] = water.size();
            while(!water.isEmpty()){
                if(value[0]==value[1]) break;
                V v = water.remove();
                if(v.x+1<R && array[v.x+1][v.y]=='X'){
                    array[v.x+1][v.y]='.';
                    water.add(new V(v.x+1,v.y,false));
                }
                if(v.x-1>=0 && array[v.x-1][v.y]=='X'){
                    array[v.x-1][v.y]='.';
                    water.add(new V(v.x-1,v.y,false));
                }
                if(v.y+1<C && array[v.x][v.y+1]=='X'){
                    array[v.x][v.y+1]='.';
                    water.add(new V(v.x,v.y+1,false));
                }
                if(v.y-1>=0 && array[v.x][v.y-1]=='X'){
                    array[v.x][v.y-1]='.';
                    water.add(new V(v.x,v.y-1,false));
                }
                value[0]+=1;
            }
            n++;
            flag=!flag;
        }
    }
    static class V implements Comparable<V>{
        int x;
        int y;
        boolean csh;
        public V(int x,int y,boolean csh){this.x=x; this.y=y; this.csh=csh;}
        @Override
        public int compareTo(V o) {
            int a = this.csh?1:0;
            int b = o.csh?1:0;
            if(flag) return b-a;
            else return a-b;
        }
    }
}