package BaekJoon_1110;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int n = num;
        int cnt=0;

        while(true){
            StringBuilder str = new StringBuilder();
            cnt++;

            if(n<10){
                str.append(String.valueOf(n)+n);
            }else{
                str.append(n%10+String.valueOf( ((n/10) + (n%10))%10 ));
            }

            if(num>=10&&str.toString().equals(String.valueOf(num))) break;
            else if(num<10&&str.toString().equals("0"+num)) break;

            n = Integer.parseInt(str.toString());
        }
        System.out.print(cnt);
    }

}