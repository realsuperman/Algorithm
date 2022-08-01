package Programmers_5;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(solution(new int[]{1, 2, 1, 3, 1, 4, 1, 2}));
        System.out.println(solution(new int[]{1, 2, 3, 1, 4}));
    }

    public static int solution(int[] topping) {
        int answer = 0;
        Map<Integer,Integer> left = new HashMap<>();
        Map<Integer,Integer> right = new HashMap<>();

        for(int i=0;i<topping.length;i++){
            if(right.get(topping[i])==null) right.put(topping[i],1);
            else right.put(topping[i],right.get(topping[i])+1);
        }
        if(left.size()==right.size()) answer++;

        for(int i=0;i<topping.length;i++){
            if(left.get(topping[i])==null) left.put(topping[i],1);
            else left.put(topping[i],left.get(topping[i])+1);
            right.put(topping[i],right.get(topping[i])-1);
            if(right.get(topping[i])==0) right.remove(topping[i]);
            if(left.size()==right.size()) answer++;
        }

        return answer;
    }
}