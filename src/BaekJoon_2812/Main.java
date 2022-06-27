package BaekJoon_2812;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int K = Integer.parseInt(str[1]);
        int tempK=K;

        String result = br.readLine();
        Stack<Integer> stack = new Stack<>();
        stack.push(result.charAt(0)-48);

        for(int i=1;i<result.length();i++){
            int v = result.charAt(i)-48;
            if(K>0){
                while(!stack.isEmpty()){
                    if(stack.peek()<v){
                        stack.pop();
                        K--;
                    }else{
                        break;
                    }
                    if(K==0) break;
                }
            }
            stack.push(v);
        }
        while(!stack.isEmpty()){
            if(stack.size()>N-tempK){
                stack.pop();
            }else{
                break;
            }
        }
        StringBuilder solution = new StringBuilder();
        while(!stack.isEmpty()) solution.append(stack.pop());
        System.out.println(solution.reverse());
    }
}