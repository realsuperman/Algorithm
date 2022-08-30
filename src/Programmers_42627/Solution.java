package Programmers_42627;

import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs,(o1,o2)->{
            if(o1[0]==o2[0]) return Integer.compare(o1[1],o2[1]);
            else return Integer.compare(o1[0],o2[0]);
        });

        PriorityQueue<V> queue = new PriorityQueue<>();
        queue.add(new V(jobs[0][0],jobs[0][1]));

        int i=1;
        int end=jobs[0][0];
        int total=0;
        while(!queue.isEmpty()){
            V v = queue.poll();
            if(end <= v.start) total+=v.time;
            else total+=v.time+end-v.start;
            end += v.time;
            int sw = 0;
            for(;i<jobs.length;){
                if(jobs[i][0]<=end){ queue.add(new V(jobs[i][0],jobs[i][1])); i++; sw=1;}
                else break;
            }
            if(i<jobs.length && queue.isEmpty() && sw==0){
                queue.add(new V(jobs[i][0],jobs[i][1]));
                end = jobs[i][0];
                i++;
            }
        }
        return total/jobs.length;
    }
}

class V implements Comparable<V>{
    int start;
    int time;

    public V(int start,int time){
        this.start=start;
        this.time=time;
    }

    public int compareTo(V v) {
        if(this.time>v.time) return 1;
        else return -1;
    }
}