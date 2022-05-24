package BaekJoon_14503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int value;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        int[][] array = new int[N][M];
        str = br.readLine().split(" ");

        int startX = Integer.parseInt(str[0]);
        int startY = Integer.parseInt(str[1]);
        int direction = Integer.parseInt(str[2]);

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                array[i][j]=Integer.parseInt(str[j]);
            }
        }

        int cnt = 0;

        while(true){
            array[startX][startY]=9;

            if(direction==0){ // 북
                direction = Direction.lotate(direction);
                if(startY-1>0 && array[startX][startY-1]==0){
                    startY=startY-1;
                    cnt = 0;
                }else{
                    cnt++;
                }
            }else if(direction==1){ // 동
                direction = Direction.lotate(direction);
                if(startX-1>0 && array[startX-1][startY]==0){
                    startX=startX-1;
                    cnt = 0;
                }else{
                    cnt++;
                }
            }else if(direction==2){ // 남
                direction = Direction.lotate(direction);
                if(startY+1<M && array[startX][startY+1]==0){
                    startY=startY+1;
                    cnt = 0;
                }else{
                    cnt++;
                }
            }else{ // 서
                direction = Direction.lotate(direction);
                if(startX+1<N && array[startX+1][startY]==0){
                    startX=startX+1;
                    cnt = 0;
                }else{
                    cnt++;
                }
            }

            if(cnt==4){ // 2-b
                cnt = 0;
                if(direction==0){
                    if(startX+1<N && array[startX+1][startY]!=1){
                        startX = startX+1;
                    }else{
                        break;
                    }
                }else if(direction==1){
                    if(startY-1>0 && array[startX][startY-1]!=1){
                        startY=startY-1;
                    }else{
                        break;
                    }
                }else if(direction==2){
                    if(startX-1>0 && array[startX-1][startY]!=1){
                        startX=startX-1;
                    }else{
                        break;
                    }
                }else{
                    if(startY+1<M && array[startX][startY+1]!=1){
                        startY=startY+1;
                    }else{
                        break;
                    }
                }
            }
        }

        int solution = 0;
        for(int i=0;i<N;i++) for(int j=0;j<M;j++) if(array[i][j]==9) solution++;
        System.out.println(solution);
    }
}

class Direction{
    public static int lotate(int v){
        switch(v){
            case 0 : return 3;
            case 1 : return 0;
            case 2 : return 1;
            default : return 2;
        }
    }
}
    