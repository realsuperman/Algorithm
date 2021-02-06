package BaekJoon_17144;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ",3);

        int r = Integer.parseInt(str[0]);
        int c = Integer.parseInt(str[1]);
        int t = Integer.parseInt(str[2]);
        int[][] array = new int[r][c];
        int[][] temp = new int[r][c];
        int[] cleaner = new int[2];
        int index = 0;

        for(int i=0;i<r;i++){
            str = br.readLine().split(" ",c);
            for(int j=0;j<c;j++){
                array[i][j] = Integer.parseInt(str[j]);
            }
        }

        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                temp[i][j] = array[i][j];
            }
        }

        while(index<t){
            for(int i=0;i<r;i++){
                if(array[i][0] == -1) cleaner[1] = i;
                for(int j=0;j<c;j++){
                    if(array[i][j]>0){ // 4방향
                        int cnt = 0;
                        int value = array[i][j]/5;

                        if( (i-1>=0) && (array[i-1][j]!=-1) ){ //위쪽방향
                            temp[i-1][j] = temp[i-1][j]+value;
                            cnt++;
                        }
                        if( (j-1>=0) && (array[i][j-1]!=-1) ){ //왼쪽방향
                            temp[i][j-1] = temp[i][j-1]+value;
                            cnt++;
                        }
                        if( (j+1<c) && (array[i][j+1]!=-1) ){ //오른쪽방향
                            temp[i][j+1] = temp[i][j+1]+value;
                            cnt++;
                        }
                        if( (i+1<r) && (array[i+1][j]!=-1) ){ //아래쪽방향
                            temp[i+1][j] = temp[i+1][j]+value;
                            cnt++;
                        }
                        temp[i][j] = temp[i][j] - (value*cnt);

                    }
                }
            }
            cleaner[0] = cleaner[1]-1; // cleaner[0]값으로 반시계방향 cleaner[1]값으로 시계방향

            for(int i=cleaner[0]-1;i>0;i--){
                temp[i][0]=temp[i-1][0];
            }
            for(int i=0;i<c-1;i++){
                temp[0][i]=temp[0][i+1];
            }
            for(int i=0;i<cleaner[0];i++){
                temp[i][c-1]=temp[i+1][c-1];
            }
            for(int i=c-1;i>0;i--){
                temp[cleaner[0]][i]=temp[cleaner[0]][i-1];
                if(i==1) temp[cleaner[0]][i] = 0;
            }

            for(int i=cleaner[1]+1;i<r-1;i++){
                temp[i][0] = temp[i+1][0];
            }
            for(int i=0;i<c-1;i++){
                temp[r-1][i] = temp[r-1][i+1];
            }
            for(int i=r-1;i>cleaner[1]-1;i--){
                temp[i][c-1] = temp[i-1][c-1];
            }
            for(int i=c-1;i>0;i--){
                temp[cleaner[1]][i] = temp[cleaner[1]][i-1];
                if(i==1) temp[cleaner[1]][i] = 0;
            }
            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){
                    array[i][j] = temp[i][j];
                }
            }
            index++;
        }

        int sum = 0;
        for(int i=0;i<r;i++)
            for(int j=0;j<c;j++) sum+=array[i][j];
        System.out.print(sum+2);
    }
}