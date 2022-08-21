package Programmers_cache;

import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        Map<String,Integer> map = new HashMap<>();
        List<String> list = new LinkedList<>();
        if(cacheSize==0) return cities.length*5;

        for(String str : cities){
            str = str.toUpperCase();
            if(map.get(str)==null || map.get(str)==0){
                answer+=5;
                if(list.size()>=cacheSize){
                    String t = list.remove(0);
                    map.put(t,0);
                }
                list.add(str);
                map.put(str,1);
            }else if(map.get(str)>0){
                answer+=1;
                for(int i=0;i<list.size();i++){
                    if(list.get(i).equals(str)){
                        String temp = list.remove(i);
                        list.add(temp);
                    }
                }
            }
        }

        return answer;
    }
}