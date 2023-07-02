package BaekJoon_12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static V[][] array;
    static int MAX_BLOCK_SIZE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new V[N][N];
        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                int v = Integer.parseInt(str[j]);
                array[i][j]= new V(v,false);
                MAX_BLOCK_SIZE = MAX_BLOCK_SIZE<v?v:MAX_BLOCK_SIZE;
            }
        }

        for(int i=0;i<4;i++) solution(array,i,0);
        System.out.println(MAX_BLOCK_SIZE);
    }

    public static void solution(V[][] array,int direction,int time){
        if(time==6) return;
        V[][] temp = new V[N][N];
        for(int i=0;i<N;i++) for(int j=0;j<N;j++) temp[i][j] = new V(array[i][j].num, array[i][j].check);
        int maxBlockSize = getMaxBlockSize(temp);
        MAX_BLOCK_SIZE = MAX_BLOCK_SIZE<maxBlockSize?maxBlockSize:MAX_BLOCK_SIZE;

        if(direction==0){
            while(true){
                V[][] before = init(temp);
                for(int j=0;j<N;j++){
                    for(int i=0;i<N-1;i++){
                        settingValue(temp, i, j, i+1, j);
                    }
                }
                if(isEnd(before,temp)) break;
            }
        }else if(direction==1){
            while(true){
                V[][] before = init(temp);
                for(int j=0;j<N;j++){
                    for(int i=N-1;i>0;i--){
                        settingValue(temp, i, j, i-1, j);
                    }
                }
                if(isEnd(before,temp)) break;
            }
        }else if(direction==2){
            while(true){
                V[][] before = init(temp);
                for(int i=0;i<N;i++){
                    for(int j=0;j<N-1;j++){
                        settingValue(temp, i, j, i, j+1);
                    }
                }
                if(isEnd(before,temp)) break;
            }
        }else{
            while(true){
                V[][] before = init(temp);
                for(int i=0;i<N;i++){
                    for(int j=N-1;j>0;j--){
                        settingValue(temp, i, j, i, j-1);
                    }
                }
                if(isEnd(before,temp)) break;
            }
        }

        for(int i=0;i<N;i++) for(int j=0;j<N;j++) temp[i][j].check=false;
        for(int i=0;i<4;i++) solution(temp,i,time+1);
    }

    private static V[][] init(V[][] array) {
        V[][] before = new V[N][N];
        for(int i=0;i<N;i++) for(int j=0;j<N;j++) before[i][j] = new V(array[i][j].num,array[i][j].check);
        return before;
    }

    private static boolean isEnd(V[][] from, V[][] to){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(from[i][j].num != to[i][j].num || from[i][j].check != to[i][j].check){
                    return false;
                }
            }
        }
        return true;
    }

    private static int getMaxBlockSize(V[][] array){
        int MAX_SIZE = Integer.MIN_VALUE;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                MAX_SIZE = MAX_SIZE<array[i][j].num?array[i][j].num:MAX_SIZE;
            }
        }
        return MAX_SIZE;
    }

    private static void swapArray(V[][] array, int x1, int y1, int x2, int y2){
        array[x1][y1].num = array[x2][y2].num;
        array[x1][y1].check = array[x2][y2].check;
        resetArray(array,x2,y2);
    }

    private static void resetArray(V[][] array,int x,int y){
        array[x][y].num=0;
        array[x][y].check=false;
    }

    private static void settingValue(V[][] array, int x1, int y1, int x2, int y2){
        if(array[x1][y1].num>0 && !array[x1][y1].check && array[x1][y1].num==array[x2][y2].num && !array[x2][y2].check){
            array[x1][y1].num = array[x1][y1].num*2;
            array[x1][y1].check=true;
            resetArray(array,x2,y2);
        }else if(array[x1][y1].num == 0){
            swapArray(array,x1,y1,x2,y2);
        }
    }
}

class V{
    int num;
    boolean check; // 블록이 합쳐진적이 있는가?(true면 합쳐진 적이 있음)
    public V(int num, boolean check){
        this.num=num;
        this.check=check;
    }
}