package Programmers_64061;

import java.util.*;
class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for(int v : moves){
            int x = 0;
            int y = v-1;
            int element = 0;
            while(x<board.length){
                if(board[x][y]>0){
                    element = board[x][y];
                    board[x][y]=0;
                    break;
                }
                x++;
            }
            if(element>0){
                if(stack.size()>0 && stack.peek()==element){
                    int cnt = 2;
                    stack.pop();
                    while(!stack.isEmpty()){
                        if(stack.peek() == element){
                            stack.pop();
                            cnt++;
                        }else{
                            break;
                        }
                    }
                    answer+=cnt;
                }
                else stack.push(element);
            }
        }

        return answer;
    }
}
/*
    00000
    00103
    02501
    42442
    35131

*/