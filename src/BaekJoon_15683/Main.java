package BaekJoon_15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int cnt = Integer.MAX_VALUE;
    public static int[] direction = {0,4,2,4,4,1};
    public static boolean[][] check;
    public static int[][] array;
    public static int N,M;
    public static int end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][M];
        check = new boolean[N][M];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                array[i][j]= Integer.parseInt(str[j]);
                if(array[i][j]>=1 && array[i][j]<=5) end +=1;
            }
        }
        if(end==0){
            cnt = 0;
            for(int i=0;i<N;i++) for(int j=0;j<M;j++) if(array[i][j]==0) cnt++;
            System.out.println(cnt);
            return;
        }
        solution(0);
        System.out.println(cnt);
    }

    public static void solution(int number){
        if(number==end){
            int size = minSize();
            cnt = cnt>size?size:cnt;
            return;
        }

        int i=0,j=0;
        for(i=0;i<N;i++){
            boolean sw = false;
            for(j=0;j<M;j++){
                if(!check[i][j] && array[i][j]>=1 && array[i][j]<=5){
                    sw = true;
                    break;
                }
            }
            if(sw) break;
        }

        int value;
        int temp = array[i][j];
        if(array[i][j]==1){
            value = 7;
        }else if(array[i][j]==2){
            value = 11;
        }else if(array[i][j]==3){
            value = 13;
        }else if(array[i][j]==4){
            value = 17;
        }else{
            value = 21;
        }
        check[i][j]=true;
        for(int k=0;k<direction[temp];k++){
            array[i][j]=value+k;
            solution(number+1);
        }
        check[i][j]=false;
        array[i][j] = temp;

    }

    public static int minSize(){
        int[][] temp = new int[N][M];
        for(int i=0;i<N;i++) for(int j=0;j<M;j++) temp[i][j]=array[i][j];

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(temp[i][j]>=7 &&temp[i][j]<22){
                    control(temp,temp[i][j],i,j);
                }
            }
        }
        int size = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(temp[i][j]==0) size++;
            }
        }
        return size;
    }

    public static void control(int[][] temp,int v,int i,int j){
        switch(v){
            case 7 : direction(7,temp,i,j); break;
            case 8 : direction(8,temp,i,j); break;
            case 9 : direction(9,temp,i,j); break;
            case 10 : direction(10,temp,i,j); break;
            case 11 : direction(9,temp,i,j); direction(7,temp,i,j);break;
            case 12 : direction(8,temp,i,j); direction(10,temp,i,j);break;
            case 13 : direction(7,temp,i,j); direction(10,temp,i,j);break;
            case 14 : direction(7,temp,i,j); direction(8,temp,i,j);break;
            case 15 : direction(8,temp,i,j); direction(9,temp,i,j);break;
            case 16 : direction(9,temp,i,j); direction(10,temp,i,j);break;
            case 17 : direction(7,temp,i,j); direction(9,temp,i,j); direction(10,temp,i,j);break;
            case 18 : direction(7,temp,i,j); direction(8,temp,i,j); direction(10,temp,i,j);break;
            case 19 : direction(7,temp,i,j); direction(8,temp,i,j); direction(9,temp,i,j);break;
            case 20 : direction(8,temp,i,j); direction(9,temp,i,j); direction(10,temp,i,j);break;
            case 21 : direction(7,temp,i,j); direction(8,temp,i,j); direction(9,temp,i,j); direction(10,temp,i,j);break;
        }
    }

    public static void direction(int direct,int[][] temp,int i,int j){
        if(direct==7){
            for(int index=j;index<M;index++){
                if(temp[i][index]==0) temp[i][index]=-1;
                else if(temp[i][index]==6) break;
            }
        }else if(direct==8){
            for(int index=i;index<N;index++){
                if(temp[index][j]==0) temp[index][j]=-1;
                else if(temp[index][j]==6) break;
            }
        }else if(direct==9){
            for(int index=j;index>=0;index--){
                if(temp[i][index]==0) temp[i][index]=-1;
                else if(temp[i][index]==6) break;
            }
        }else if(direct==10){
            for(int index=i;index>=0;index--){
                if(temp[index][j]==0) temp[index][j]=-1;
                else if(temp[index][j]==6) break;
            }
        }

    }

}