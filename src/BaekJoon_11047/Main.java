package BaekJoon_11047;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int findNum = sc.nextInt();
        int[] array = new int[n];
        int cnt = 0;
        int sum = 0;
        int index = 0;

        for(int i=0;i<n;i++){
            int value = sc.nextInt();
            array[i] = value;
            if(findNum >= value){
                index = i;
            }
        }
        int i = index;
        while(i>=0) {
            sum = sum + array[i];
            cnt++;
            if(sum == findNum) break;
            else if(sum > findNum){
                sum = sum-array[i];
                i--;
                cnt--;
            }
        }
        System.out.println(cnt);
    }
}