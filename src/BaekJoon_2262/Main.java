package BaekJoon_2262;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer> list = new ArrayList<>();
        String[] str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) list.add(Integer.parseInt(str[i]));

        int sum = 0;
        while(list.size()>1){
            int max = Integer.MIN_VALUE;
            int index = -1;
            for(int i=0;i<list.size();i++){
                if(max<list.get(i)){
                    max = list.get(i);
                    index = i;
                }
            }
            int left = Integer.MAX_VALUE;
            if(index-1>=0) left = Math.abs(list.get(index)-list.get(index-1));

            int right = Integer.MAX_VALUE;
            if(index+1<list.size()) right = Math.abs(list.get(index)-list.get(index+1));
            if(left>right) sum+=right;
            else sum+=left;
            list.remove(index);
        }

        System.out.println(sum);
    }
}