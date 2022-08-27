package Programmers_77885;

import java.util.*;
class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        int index = 0;
        for(long value : numbers){
            if(value%2==0) answer[index] = value+1;
            else{
                List<Long> list = new ArrayList<>();
                List<Long> result = new ArrayList<>();

                while(value>0){
                    list.add(value%2);
                    value/=2;
                }

                boolean check = false;

                for(long v : list){
                    if(v==0 && !check){
                        check=true;
                        result.remove(result.size()-1);
                        result.add(0L);
                        result.add(1L);
                    }else{
                        result.add(v);
                    }
                }
                if(!check) { result.remove(result.size()-1); result.add(0L); result.add(1L);}

                long constValue = 1L;
                for(long v : result){
                    answer[index]+=v*constValue;
                    constValue*=2;
                }
            }
            index++;
        }
        return answer;
    }
}