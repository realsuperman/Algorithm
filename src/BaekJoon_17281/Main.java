package BaekJoon_17281;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[][] array;
    static int result;
    static int[] seat = new int[9];
    static boolean[] check = new boolean[9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N][9];
        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                array[i][j] = Integer.parseInt(str[j]);
            }
        }
        check[0] = true;
        solution(0);
        System.out.println(result);
    }
    public static void solution(int num){
        if(num==9){
            int n = 1; // 이닝
            int i=0,sum=0;
            while(n<=N){
                int out = 0;
                int[] base = new int[4]; // 1루,2루,3루,4루?
                for(;;){
                    switch(array[n-1][seat[i]]){
                        case 0 : out++; break;
                        case 1 :
                            for(int index=3;index>0;index--){
                                base[index]+=base[index-1];
                                base[index-1]=0;
                            }
                            base[0]++;
                            break;
                        case 2 :
                            base[3] += base[2];
                            base[3] += base[1];
                            base[2] = 0;
                            base[1] = 0;
                            base[2] += base[0];
                            base[0]=0;
                            base[1]++;
                            break;
                        case 3 :
                            for(int index=0;index<3;index++){
                                base[3]+=base[index];
                                base[index]=0;
                            }
                            base[2]++;
                            break;
                        case 4 :
                            for(int index=0;index<3;index++){
                                base[3]+=base[index];
                                base[index]=0;
                            }
                            base[3]++;
                            break;
                    }
                    i++;
                    if(i>=9) i=0;
                    if(out==3) break;
                }
                sum+=base[3];
                n++;
            }
            if(sum>result) result=sum;
            return;
        }
        if(num==3){
            seat[3] = 0;
            solution(num+1);
        }


        for(int i=0;i<9;i++){
            if(check[i]) continue;
            check[i]=true;
            seat[num] = i;
            solution(num+1);
            check[i]=false;
        }
    }
}