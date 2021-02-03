package BaekJoon_2839;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int satou = sc.nextInt();

        int[] array = new int[4];
        array[0] = satou%3==0?satou/3:-1;
        array[1] = satou%5==0?satou/5:-1;
        array[2] = 2147483647;
        array[3] = 2147483647;

        int s = satou;
        int i = 3;
        int divide = 0;

        while(s>0){
            s = s-i;
            divide++;
            if(s>=0 && s%5==0){
                int j = s/5+divide;
                if(array[2]>j) array[2] = s/5+divide;
            }
        }

        s = satou;
        i = 5;
        divide = 0;
        while(s>0){
            s = s-i;
            divide++;
            if(s>=0 && s%3==0){
                int j = s/3+divide;
                if(array[3]>j) array[3] = s/3+divide;
            }
        }

        if(array[2]==2147483647) array[2] = -1;
        if(array[3]==2147483647) array[3] = -1;


        boolean sw = true;
        for(i=0;i<4;i++){
            if(array[i]!=-1) sw = false;
        }
        if(sw){
            System.out.print(-1);
        }
        else{
            int min = 2147483647;
            for(i=0;i<4;i++){
                if(array[i]==-1) continue;
                if(min>array[i]) min = array[i];
            }
            System.out.print(min);
        }

    }
}