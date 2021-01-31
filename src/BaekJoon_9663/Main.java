package BaekJoon_9663;

import java.util.Scanner;

public class Main {
    static boolean[] flag_a = new boolean[15];
    static boolean[] flag_b = new boolean[100];
    static boolean[] flag_c = new boolean[100];
    static int cnt = 0;

    static void print(){
        cnt++;
    }

    static void set(int i,int max){
        for(int j=0;j<max;j++){
            if(flag_a[j]==false && flag_b[i+j]==false && flag_c[i-j+max] == false){
                if(i==max-1) print();
                else{
                    flag_a[j]=flag_b[i+j]=flag_c[i-j+max]=true;
                    set(i+1,max);
                    flag_a[j]=flag_b[i+j]=flag_c[i-j+max]=false;
                }
            }
        }
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        set(0,sc.nextInt());
        System.out.println(cnt);
    }

}