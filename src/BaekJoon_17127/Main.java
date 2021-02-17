package BaekJoon_17127;

import java.util.Scanner;

public class Main {
    static int max = 0;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int i=0;
        int[] array = new int[n];

        while(i<n){
            array[i] = sc.nextInt();
            i++;
        }

        for(i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                for(int k=j+1;k<n;k++){
                    solve(array,i,j,k,n);
                }
            }
        }
        System.out.print(max);

    }

    public static void solve(int array[],int i,int j,int k,int n){
        int a=0,b=i+1,c=j+1,d=k+1;
        int sum = 1;
        int hap = 0;
        boolean[] sw = new boolean[4];

        while(a<=i){
            sw[0] = true;
            sum *=array[a];
            a++;
        }
        hap +=sum;
        sum=1;
        while(b<=j){
            sw[1] = true;
            sum *=array[b];
            b++;
        }
        hap +=sum;
        sum=1;
        while(c<=k){
            sw[2] = true;
            sum *=array[c];
            c++;
        }
        hap +=sum;
        sum=1;
        while(d<n){
            sw[3] = true;
            sum *=array[d];
            d++;
        }
        hap +=sum;
        if(sw[0]==true&&sw[1]==true&&sw[2]==true&&sw[3]==true){
            if(max<hap) max = hap;
        }

    }
}