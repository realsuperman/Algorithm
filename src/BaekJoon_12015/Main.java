package BaekJoon_12015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        String[] str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) list.add(Integer.valueOf(str[i]));

        List<Integer> result = new ArrayList<>();
        result.add(list.get(0));
        for(int i=1;i<list.size();i++){
            if(list.get(i)>result.get(result.size()-1)) result.add(list.get(i));
            else{
                int left = 0;
                int right = result.size();
                while(left<right){
                    int mid = (left+right)/2;
                    if(result.get(mid)<list.get(i)) left = mid+1;
                    else right = mid;
                }
                result.set(left,list.get(i));
            }
        }
        System.out.println(result.size());
    }
}