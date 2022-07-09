package BaekJoon_1449;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int tape = Integer.parseInt(str[1]);
        List<Integer> list = new ArrayList<>();
        str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) list.add(Integer.valueOf(str[i]));
        Collections.sort(list);

        int result = 0;
        for(int i=0;i<list.size();){
            int value = list.get(i)+tape-1;
            int j;
            for(j=i+1;j<list.size();j++){
                if(list.get(j)>value) break;
            }
            i=j;
            result++;
        }
        System.out.println(result);
    }
}