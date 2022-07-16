package BaekJoon_2151;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] array = new char[N][N];
        Queue<V> queue = new LinkedList<>();
        boolean check = false;

        int[][][] chk = new int[4][N][N];
        for(int k=0;k<4;k++) for(int i=0;i<N;i++) for(int j=0;j<N;j++) chk[k][i][j] = Integer.MAX_VALUE;

        for(int i=0;i<N;i++){
            String[] str = br.readLine().split("");
            for(int j=0;j<str.length;j++){
                array[i][j]= str[j].charAt(0);
                if(!check && array[i][j]=='#'){
                    check=true;
                    for(int k=0;k<4;k++) chk[k][i][j]=Integer.MIN_VALUE;
                    queue.add(new V(i,j,0,-1));
                }
            }
        }

        V v = queue.remove();
        for(int i=0;i<4;i++){
            int x = v.x+dx[i];
            int y = v.y+dy[i];
            if(x<0 || x>=N || y<0 || y>=N || array[x][y]=='*') continue;
            chk[i][x][y]=0;
            queue.add(new V(x,y,0,i));
        }

        int min = Integer.MAX_VALUE;
        while(!queue.isEmpty()){
            v = queue.remove();

            if(array[v.x][v.y]=='#'){
                min = min>v.cnt?v.cnt:min;
                continue;
            }

            int x,y;
            if(v.direction==0){
                x = v.x - 1;
                y = v.y;
                if(x>=0 && x<N && y>=0 && y<N && array[x][y]!='*' && chk[v.direction][x][y]>v.cnt){
                    chk[v.direction][x][y] = v.cnt;
                    queue.add(new V(x,y,v.cnt,v.direction));
                }
            }else if(v.direction==1){
                x = v.x + 1;
                y = v.y;
                if(x>=0 && x<N && y>=0 && y<N && array[x][y]!='*' && chk[v.direction][x][y]>v.cnt){
                    chk[v.direction][x][y] = v.cnt;
                    queue.add(new V(x,y,v.cnt,v.direction));
                }
            }else if(v.direction==2){
                x = v.x;
                y = v.y-1;
                if(x>=0 && x<N && y>=0 && y<N && array[x][y]!='*' && chk[v.direction][x][y]>v.cnt){
                    chk[v.direction][x][y] = v.cnt;
                    queue.add(new V(x,y,v.cnt,v.direction));
                }
            }else{
                x = v.x;
                y = v.y+1;
                if(x>=0 && x<N && y>=0 && y<N && array[x][y]!='*' && chk[v.direction][x][y]>v.cnt){
                    chk[v.direction][x][y] = v.cnt;
                    queue.add(new V(x,y,v.cnt,v.direction));
                }
            }

            if(array[v.x][v.y]=='!'){
                if(v.direction==0){
                    x = v.x;
                    y = v.y-1; // left
                    if(x>=0 && x<N && y>=0 && y<N && array[x][y]!='*' && chk[2][x][y]>v.cnt+1){
                        chk[2][x][y] = v.cnt+1;
                        queue.add(new V(x,y,v.cnt+1,2));
                    }

                    y=v.y+1; // right
                    if(x>=0 && x<N && y>=0 && y<N && array[x][y]!='*' && chk[3][x][y]>v.cnt+1){
                        chk[3][x][y] = v.cnt+1;
                        queue.add(new V(x,y,v.cnt+1,3));
                    }
                }else if(v.direction==1){
                    x = v.x;
                    y = v.y-1; // left
                    if(x>=0 && x<N && y>=0 && y<N && array[x][y]!='*' && chk[2][x][y]>v.cnt+1){
                        chk[2][x][y] = v.cnt+1;
                        queue.add(new V(x,y,v.cnt+1,2));
                    }

                    y=v.y+1; // right
                    if(x>=0 && x<N && y>=0 && y<N && array[x][y]!='*' && chk[3][x][y]>v.cnt+1){
                        chk[3][x][y] = v.cnt+1;
                        queue.add(new V(x,y,v.cnt+1,3));
                    }
                }else if(v.direction==2){
                    x = v.x-1; // up
                    y = v.y;
                    if(x>=0 && x<N && y>=0 && y<N && array[x][y]!='*' && chk[0][x][y]>v.cnt+1){
                        chk[0][x][y] = v.cnt+1;
                        queue.add(new V(x,y,v.cnt+1,0));
                    }

                    x=v.x+1; // down
                    if(x>=0 && x<N && y>=0 && y<N && array[x][y]!='*' && chk[1][x][y]>v.cnt+1){
                        chk[1][x][y] = v.cnt+1;
                        queue.add(new V(x,y,v.cnt+1,1));
                    }
                }else{
                    x = v.x-1; // up
                    y = v.y;
                    if(x>=0 && x<N && y>=0 && y<N && array[x][y]!='*' && chk[0][x][y]>v.cnt+1){
                        chk[0][x][y] = v.cnt+1;
                        queue.add(new V(x,y,v.cnt+1,0));
                    }

                    x=v.x+1; // down
                    if(x>=0 && x<N && y>=0 && y<N && array[x][y]!='*' && chk[1][x][y]>v.cnt+1){
                        chk[1][x][y] = v.cnt+1;
                        queue.add(new V(x,y,v.cnt+1,1));
                    }
                }
            }
        }
        System.out.println(min);
    }
}

class V{
    int x;
    int y;
    int cnt;
    int direction; // up down left right
    public V(int x,int y,int cnt,int direction){
        this.x=x;
        this.y=y;
        this.cnt=cnt;
        this.direction=direction;
    }
}