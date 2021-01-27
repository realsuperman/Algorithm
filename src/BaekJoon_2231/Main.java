package BaekJoon_2231;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int findNum = sc.nextInt();
        int i;
        int sum;
        boolean sw = false;

        for(i=1;i<findNum;i++){
            sum = i;
            if(i>999999){

            }else if(i>99999){
                sum = sum+(i/100000)+((i%100000)/10000)+((i%10000)/1000)+((i%1000)/100)+((i%100)/10)+(i%10);
            }else if(i>9999){
                sum = sum+(i/10000)+((i%10000)/1000)+((i%1000)/100)+((i%100)/10)+(i%10);
            }else if(i>999){
                sum = sum+(i/1000)+((i%1000)/100)+((i%100)/10)+(i%10);
            }else if(i>99){
                sum = sum+(i/100)+((i%100)/10)+(i%10);
            }else if(i>9){
                sum = sum+(i/10)+(i%10);
            }else{
                sum = sum+i;
            }

            if(sum == findNum){
                sw = true;
                break;
            }

        }
        if(sw) System.out.print(i);
        else System.out.println(0);

    }
}