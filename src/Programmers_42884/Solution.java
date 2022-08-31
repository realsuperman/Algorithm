package Programmers_42884;

import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        List<Camera> list = new ArrayList<>();
        for(int i=0;i<routes.length;i++) list.add(new Camera(routes[i][0],routes[i][1]));
        Collections.sort(list);
        while(list.size()>0){
            answer++;
            Camera camera = list.remove(0);
            int v= camera.to;
            for(;;){
                if(list.size() == 0) break;
                Camera c = list.get(0);
                if(c.to>=v && c.from<=v){
                    list.remove(0);
                }else{break;}
            }
        }
        return answer;
    }
    class Camera implements Comparable<Camera>{
        int from,to;
        public Camera(int from,int to){this.from=from; this.to=to;}
        public int compareTo(Camera camera){
            return this.to-camera.to;
        }
    }
}