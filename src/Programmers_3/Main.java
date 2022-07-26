package Programmers_3;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        int[] array = {4,3,1,2,5};
        System.out.println(solution(array));

        int[] temp = {5,4,3,2,1};
        System.out.println(solution(temp));
    }

    public static int solution(int[] order) {
        int answer = 0;
        int max = order.length;
        Stack<Integer> stack = new Stack<>();

        int input = 1;
        int index = 0;
        while(input<=max){
            if(order[index]!=input) stack.push(input);
            else {
                answer++;
                index++;
                while(!stack.isEmpty()){
                    if(order[index]==stack.peek()){
                        answer++;
                        index++;
                        stack.pop();
                    }else{
                        break;
                    }
                }
            }
            input++;
        }

        return answer;
    }

}