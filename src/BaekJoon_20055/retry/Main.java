package BaekJoon_20055.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int K = Integer.parseInt(str[1]);
        int[] array = new int[N*2]; // 내구도
        int[] currentRobot = new int[N]; // 칸에 있는 로봇 개수
        str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) array[i]= Integer.parseInt(str[i]);
        int time = 0;
        while(!isFinish(array,K)){
            int temp = array[N*2-1];
            for(int i=2*N-1;i>=1;i--) array[i] = array[i-1];
            array[0] = temp;
            if(currentRobot[N-1]==1){  currentRobot[N-1]=0;}
            for(int i=N-1;i>=1;i--) currentRobot[i]=currentRobot[i-1];
            if(currentRobot[N-1]==1){  currentRobot[N-1]=0;}
            currentRobot[0]=0;
            for(int i=N-2;i>=0;i--){
                if(currentRobot[i+1]==0 && currentRobot[i]>=1 && array[i+1]>=1) {
                    array[i+1]--;
                    currentRobot[i+1]=1;
                    currentRobot[i]=0;
                }
            }

            if(array[0]>=1){ currentRobot[0]++; array[0]--;}
            time++;
        }
        System.out.println(time);
    }

    public static boolean isFinish(int[] array,int k){
        int count = 0;
        for(int v : array) if(v==0) count++;
        return count>=k?true:false;
    }
}