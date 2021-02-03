package BaekJoon_10773;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int i=0,j=0;
        Stack<Integer> stack = new Stack<>();

        while(i<n){
            j=Integer.parseInt(br.readLine());
            if(j==0){
                stack.pop();
            }else{
                stack.push(j);
            }
            i++;
        }
        j=0;
        for(int su : stack){
            j+=su;
        }
        System.out.print(j);
    }

}