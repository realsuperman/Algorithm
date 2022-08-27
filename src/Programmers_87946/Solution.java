package Programmers_87946;

import java.util.*;
class Solution {
    boolean[] check;
    int len;
    int answer = 0;

    public int solution(int k, int[][] dungeons) {
        len = dungeons.length;
        check = new boolean[len];
        DFS(0,new ArrayList<>(),0,dungeons,k);
        return answer;
    }

    public void DFS(int start,List<Integer> list,int v,int[][] dungeons, int max){
        if(v==len){
            int cnt = 0;
            for(int v1 : list){
                if(max>=dungeons[v1][0]){
                    cnt++;
                    max-=dungeons[v1][1];
                }else{
                    break;
                }
            }
            answer = answer<cnt?cnt:answer;
            return;
        }

        for(int i=0;i<len;i++){
            if(check[i]) continue;
            check[i]=true;
            list.add(i);
            int size = list.size();
            DFS(i,list,v+1,dungeons,max);
            list.remove(size-1);
            check[i]=false;
        }

    }

}