package Programmers_8;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(solution(new int[]{2,1,1,2,3,1,2,3,1}));
        System.out.println(solution(new int[]{1,3,2,1,2,1,3,1,2}));
        System.out.println(solution(new int[]{1,2,1,2,3,1,3,1}));
    }

    public static int solution(int[] ingredient) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();

        for(int i=0;i<ingredient.length;i++){
            stack.push(ingredient[i]);
            if(stack.peek()==1){
                StringBuilder sb = new StringBuilder();
                List<Integer> temp = new ArrayList<>();

                int size = 4;
                while(!stack.isEmpty()){
                    temp.add(stack.pop());
                    size--;
                    if(size==0) break;
                }

                if(temp.size()<4){
                    for(int index=temp.size()-1;index>=0;index--) stack.push(temp.get(index));
                }
                else{
                    for(int v: temp) sb.append(v);
                    if(sb.toString().equals("1321")) answer++;
                    else{
                        for(int index=temp.size()-1;index>=0;index--) stack.push(temp.get(index));
                    }
                }
            }
        }

        return answer;
    }
}