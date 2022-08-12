package Programmers_9;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        int[][] test = {{3,4},{5,8}};
        int[][] test2 = {{2,5},{4,3}};
        System.out.println(solution(10,test,test2));//8

        int[][] test3 = {{7,8},{4,6},{11,10}};
        int[][] test4 = {{2,2},{2,4},{3,3}};
        System.out.println(solution(12,test3,test4));//12
    }

    public static int solution(int distance, int[][] scope, int[][] times) {
        int answer = 0; // 유저의 위치

        List<V> monitor = new LinkedList<>();
        List<V> breakUser = new LinkedList<>();

        for(int i=0;i<scope.length;i++){
            monitor.add(new V(scope[i][0],scope[i][1],times[i][0],times[i][1],times[i][0],times[i][1]));
            //System.out.println(monitor.get(i).start+" "+monitor.get(i).end+" "+monitor.get(i).time+" "+monitor.get(i).rest+" "+monitor.get(i).originalTime+" "+monitor.get(i).originalRest);
        }

        for(answer=1;answer<distance;answer++){
            for(V v : monitor){
                if(answer>=v.start && answer<=v.end) return answer;
                v.time--;
            }
            for(V v : breakUser) v.rest--;

            List<V> temp = new LinkedList<>();
            List<V> list = new LinkedList<>(); // 쉴녀석들
            for(V v : monitor){
                if(v.time<=0) list.add(new V(v.start,v.end,0,v.rest,v.originalTime,v.originalRest));
                else temp.add(v);
            }
            monitor = temp; // time이 1이상인 애들

            temp = new LinkedList<>();
            for(V v : breakUser){
                if(v.rest<=0) monitor.add(new V(v.start,v.end,v.originalTime,v.originalRest,v.originalTime,v.originalRest));
                else temp.add(v);
            }
            breakUser=temp;
            for(V v : list) breakUser.add(v);

        }

        return answer;
    }
}

class V{
    int start;
    int end;
    int time;
    int rest;
    int originalTime; // 초기 근로시간
    int originalRest; // 초기 휴식시간

    public V(int start,int end,int time,int rest,int originalTime,int originalRest){
        if(start>end){
            this.start=end;
            this.end=start;
        }else{
            this.start=start;
            this.end=end;
        }
        this.time=time;
        this.rest=rest;

        this.originalTime=originalTime;
        this.originalRest = originalRest;
    }
}