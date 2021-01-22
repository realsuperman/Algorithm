package BaekJoon_1789;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long findInt = sc.nextLong();
        long cnt=0,sum = 0,i=1;

        while(true){
            sum = sum+i++;
            if(findInt == sum){
                cnt++;
                break;
            }
            else if(findInt<sum){
                break;
            }
            cnt++;
        }
        System.out.println(cnt);
    }
}