package Baekjoon_2751;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int cnt = sc.nextInt();
        ArrayList<Integer> nums = new ArrayList<>();

        for(int i=0;i<cnt;i++){
            nums.add(sc.nextInt());
        }
        Collections.sort(nums);
        for(int a : nums){
            sb.append(a).append('\n');
        }
        System.out.println(sb);
    }
}
