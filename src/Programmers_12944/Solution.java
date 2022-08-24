package Programmers_12944;

class Solution {
    public double solution(int[] arr) {
        double sum = 0;
        for(int v : arr) sum+=v;
        return sum/arr.length;
    }
}