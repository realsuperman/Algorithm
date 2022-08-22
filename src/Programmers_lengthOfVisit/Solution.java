package Programmers_lengthOfVisit;

import java.util.*;

class Solution {
    public int solution(String dirs) {
        int answer = 0;
        int index = 0;

        int x = 0;
        int y = 0;
        Map<String,Integer> map = new HashMap<>();
        while(index < dirs.length() ){
            int tempX = x;
            int tempY = y;

            boolean chk = false;
            if(dirs.charAt(index)=='U'){
                if(y+1>=-5 && y+1<=5) y++;
                else chk = true;
            }else if(dirs.charAt(index)=='D'){
                if(y-1>=-5 && y-1<=5) y--;
                else chk = true;
            }else if(dirs.charAt(index)=='L'){
                if(x-1>=-5 && x-1<=5) x--;
                else chk = true;
            }else{
                if(x+1>=-5 && x+1<=5) x++;
                else chk = true;
            }
            if(chk){ index++; continue;}
            String temp = tempX+""+tempY+""+x+""+y;
            String temp2 = x+""+y+""+tempX+""+tempY;
            if(map.get(temp)==null && map.get(temp2)==null){answer++; map.put(temp,1); map.put(temp2,1);}
            index++;
        }
        return answer;
    }
}