package Programmers_12935;

class Solution {
    public int[] solution(int[] arr) {
        int[] answer = new int[arr.length-1];
        if(answer.length==0){
            int[] t = {-1};
            return t;
        }

        int max = Integer.MAX_VALUE;
        for(int v : arr) max = max>v?v:max;
        int index = 0;
        for(int i=0;i<arr.length;i++){
            if(max==arr[i]) continue;
            answer[index++] = arr[i];
        }

        return answer;
    }
}