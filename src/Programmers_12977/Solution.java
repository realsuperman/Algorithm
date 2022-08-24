package Programmers_12977;

import java.util.*;
class Solution {
    static int answer;
    static boolean[] check;

    public int solution(int[] nums) {
        check = new boolean[nums.length];
        dfs(0,new ArrayList<>(),0,nums.length,nums);
        return answer;
    }

    public void dfs(int start,List<Integer> list,int index,int max,int[] nums){
        if(start==3){
            int sum = 0;
            for(int v : list){
                sum+=v;
            }
            System.out.println();
            if(primeNumber(sum)) answer++;
            return;
        }
        for(int i=index;i<max;i++){
            if(check[i]) continue;
            list.add(nums[i]);
            int size = list.size();
            check[i]=true;
            dfs(start+1,list,i,max,nums);
            check[i]=false;
            list.remove(size-1);

        }
    }

    public boolean primeNumber(int number){
        for(int i=2;i<=Math.sqrt(number);i++){
            if(number%i==0) return false;
        }
        return true;
    }
}