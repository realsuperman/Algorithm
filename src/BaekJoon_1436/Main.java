package BaekJoon_1436;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String num = "666";
        int cnt = 1;
        while(n>cnt){
            num = String.valueOf((Integer.parseInt(num)+1));
            if(num.contains("666")){
                cnt++;
            }
        }
        System.out.println(num);
    }
}