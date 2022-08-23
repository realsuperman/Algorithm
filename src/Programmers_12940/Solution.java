package Programmers_12940;

class Solution {
    public int[] solution(int n, int m) {
        int[] answer = new int[2];
        int max = n>m?n:m;
        int min = n<m?n:m;

        int a;
        while(true){
            a = max%min;
            if(a==0) break;
            max = min;
            min = a;
        }
        answer[0] = min;
        answer[1] = (n*m)/min;
        return answer;
    }
}

/*
    3,5
    -> a는 5 b는 3
    5%3 -> 2
    3%2 -> 1
    2%1 -> 0(나머지가 0이면 브레이크)

    정답은 항상 b임
*/