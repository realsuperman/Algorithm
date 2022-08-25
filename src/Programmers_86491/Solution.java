package Programmers_86491;

class Solution {
    public int solution(int[][] sizes) {
        for(int i=0;i<sizes.length;i++){
            if(sizes[i][1]>sizes[i][0]){
                int temp = sizes[i][0];
                sizes[i][0]=sizes[i][1];
                sizes[i][1]=temp;
            }
        }
        int a=Integer.MIN_VALUE;
        int b=a;
        for(int i=0;i<sizes.length;i++){
            if(sizes[i][0]>a) a=sizes[i][0];
            if(sizes[i][1]>b) b=sizes[i][1];
        }
        return a*b;
    }
}