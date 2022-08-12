package Programmers_7;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(solution(2,1,20));
        System.out.println(solution(3,1,20));
        System.out.println(solution(3,2,3));
    }

    public static int solution(int a, int b, int n) {
        int answer = 0;
        while(n>=a){
            int num = n/a;
            answer+=num*b;
            n=n-(num*a)+(b*num);
        }
        return answer;
    }
}