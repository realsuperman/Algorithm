package BaekJoon_1092;

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
        int N = Integer.parseInt(br.readLine());
        int[] temp = new int[N];
        String[] str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) temp[i] = Integer.parseInt(str[i]);

        int M = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) list.add(Integer.valueOf(str[i]));

        Arrays.sort(temp);
        int[] array = new int[N];
        int index = 0;
        for(int i=temp.length-1;i>=0;i--) array[index++]=temp[i];
        Collections.sort(list,Collections.reverseOrder());
        if(array[0]<list.get(0)){System.out.println(-1); return;}
        int cnt = 0;

        while(list.size()>0){
            for(int i=0;i<array.length;i++){
                for(int j=0;j<list.size();j++){
                    if(array[i]>=list.get(j)){
                        list.remove(j);
                        break;
                    }
                }
            }
            cnt++;
        }
        System.out.println(cnt);
    }
}