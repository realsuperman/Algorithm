package Programmers_42579;

import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        //int[] answer = {};
        Map<String,Integer> map = new HashMap<>();
        for(int i=0;i<genres.length;i++){
            if(map.get(genres[i])==null){
                map.put(genres[i],plays[i]);
            }else{
                map.put(genres[i],map.get(genres[i])+plays[i]);
            }
        }
        List<String> listKeySet = new ArrayList<>(map.keySet());
        Collections.sort(listKeySet, (value1, value2) -> (map.get(value2).compareTo(map.get(value1))));

        List<Integer> list = new ArrayList<>();
        for(String key : listKeySet) {
            Map<Integer,Integer> map2 = new HashMap<>();
            for(int i=0;i<genres.length;i++){
                if(key.equals(genres[i])) map2.put(i,plays[i]);
            }

            List<Integer> listKeySet2 = new ArrayList<>(map2.keySet());
            Collections.sort(listKeySet2,(value1, value2) -> (map2.get(value2).compareTo(map2.get(value1))));
            int index=0;
            for(int key2 : listKeySet2) {
                index++;
                if(index==3) break;
                list.add(key2);
            }

        }
        int[] answer = new int[list.size()];
        for(int i=0;i<list.size();i++){
            answer[i]=list.get(i);
        }
        return answer;
    }
}