package BaekJoon_2798;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int findNum = sc.nextInt();
        int result = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<size;i++){
            list.add(sc.nextInt());
        }
        Collections.sort(list);

        for(int i=0;i<size;i++){
            int sum = list.get(i);
            for(int j=i+1;j<size;j++){
                sum+=list.get(j);
                for(int k=j+1;k<size;k++){
                    sum+=list.get(k);
                    if(sum<=findNum && result<sum) result = sum;
                    sum-=list.get(k);
                }
                sum = list.get(i);
            }
        }
        System.out.println(result);

    }
}