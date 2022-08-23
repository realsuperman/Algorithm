package Programmers_118666;

import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        Map<Character,Integer> map = new HashMap<>();
        map.put('R',0); map.put('T',0); map.put('C',0); map.put('F',0); map.put('J',0); map.put('M',0); map.put('A',0); map.put('N',0);

        for(int i=0;i<survey.length;i++){
            if(choices[i]==4) continue;
            String[] str = survey[i].split("");
            char from = str[0].charAt(0);
            char to = str[1].charAt(0);
            int v;

            if(choices[i]>=1 && choices[i]<=3){
                if(choices[i]==1) v= 3;
                else if(choices[i]==2) v=2;
                else v=1;
                map.put(from,map.get(from)+v);
            }else{
                if(choices[i]==5) v= 1;
                else if(choices[i]==6) v=2;
                else v=3;
                map.put(to,map.get(to)+v);
            }
        }
        String answer="";
        if(map.get('R')<map.get('T')) answer+='T';
        else answer+='R';
        if(map.get('C')<map.get('F')) answer+='F';
        else answer+='C';
        if(map.get('J')<map.get('M')) answer+='M';
        else answer+='J';
        if(map.get('A')<map.get('N')) answer+='N';
        else answer+='A';

        return answer;
    }
}