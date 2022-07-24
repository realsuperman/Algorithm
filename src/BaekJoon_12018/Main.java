package BaekJoon_12018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            int size = Integer.parseInt(str[0]);
            int max = Integer.parseInt(str[1]);

            if(size<max){
                br.readLine();
                list.add(1);
                continue;
            }

            int[] temp = new int[size];
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++) temp[j]= Integer.parseInt(str[j]);
            Arrays.sort(temp);

            int[] array = new int[size];
            int index = 0;
            for(int j=temp.length-1;j>=0;j--) array[index++] = temp[j];
            list.add(array[max-1]);
        }
        Collections.sort(list);


        int cnt = 0;
        for(int i : list){
            if(M<i) break;
            cnt++;
            M = M-i;
        }
        System.out.println(cnt);
    }
}