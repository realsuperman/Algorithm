package Programmers_92334;

import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        Set<String> set = new HashSet<>();
        for(String str : report) set.add(str);

        Map<String,Integer> map = new HashMap<>();
        Map<String,StringBuilder> checkList = new HashMap<>();

        for(String str : set){
            String[] temp = str.split(" ");
            if(map.get(temp[1])==null) map.put(temp[1],1);
            else map.put(temp[1],map.get(temp[1])+1);

            if(checkList.get(temp[0])==null){
                StringBuilder sb = new StringBuilder();
                sb.append(temp[1]+" ");
                checkList.put(temp[0],sb);
            }else{
                StringBuilder sb = new StringBuilder();
                sb = checkList.get(temp[0]);
                sb.append(temp[1]+" ");
                checkList.put(temp[0],sb);
            }
        }

        int index = 0;
        for(String str : id_list){
            if(checkList.get(str)==null){index++; continue;}
            String[] value = checkList.get(str).toString().split(" ");
            int cnt = 0;
            for(int i=0;i<value.length;i++) if(map.get(value[i])>=k) cnt++;
            answer[index++]=cnt;
        }

        return answer;
    }
}