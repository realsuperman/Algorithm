package Programmers_42889;

import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int depth = 1;
        List<Double> list = new ArrayList<>();
        double[] array = new double[N];

        while(depth<=N){
            int currentDepthCnt = 0;
            int allCnt = 0;
            for(int v : stages){
                if(v==depth) currentDepthCnt++;
                if(v>=depth) allCnt++;
            }
            if(currentDepthCnt==0){ list.add(0.0); array[depth-1]=0.0;}
            else{ list.add((double)currentDepthCnt/(double)allCnt);
                array[depth-1] = (double)currentDepthCnt/(double)allCnt;
            }
            depth++;
        }
        Collections.sort(list,Collections.reverseOrder());

        int index = 0;
        for(double v : list){
            for(int i=0;i<array.length;i++){
                if(v==array[i]){answer[index++]=i+1; array[i]=-1.0; break;}
            }
        }
        return answer;
    }
}