package Programmers_86051;

class Solution {
    public int solution(int[] numbers) {
        int answer=0;
        int[] array = new int[10];
        for(int v : numbers) array[v]=1;
        for(int i=0;i<array.length;i++){
            if(array[i]==0) answer+=i;
        }
        return answer;
    }
}