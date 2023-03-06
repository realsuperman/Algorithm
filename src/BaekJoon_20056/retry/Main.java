package BaekJoon_20056.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] dx = {-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,1,1,1,0,-1,-1,-1};
    static List<V> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M,K;
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        K = Integer.parseInt(str[2]);

        while(M-->0){
            str = br.readLine().split(" ");
            list.add(new V(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]),Integer.parseInt(str[3]),Integer.parseInt(str[4])));
        }

        while(K-->0){
            Map<String, List<V>> map = new HashMap<>(); // 좌표(1,0) -> 객체
            move(map);
            integrity(map);
        }

        int sum = 0;
        while(!list.isEmpty()){
            V v = list.remove(0);
            sum+=v.m;
        }
        System.out.println(sum);
    }

    private static void integrity(Map<String, List<V>> map) {
        String[] str;
        for(String key : map.keySet()){
            List<V> temp = map.get(key);

            if(temp.size()>=2){
                int sumM = 0;
                int sumS = 0;
                List<Integer> sumD = new ArrayList<>();

                for(V v : temp){
                    sumM+=v.m;
                    sumS+=v.s;
                    sumD.add(v.d);
                }

                sumM = sumM/5;
                if(sumM<=0) continue;

                sumS = sumS/temp.size();

                boolean sw = false;
                for(int i=0;i<sumD.size();i++){ // 홀수체크
                    if(sumD.get(i)%2!=1){ sw=false; break;}
                    else sw = true;
                }
                if(!sw) {
                    for (int i = 0; i < sumD.size(); i++) { // 짝수체크
                        if (sumD.get(i) % 2 != 0) {
                            sw = false;
                            break;
                        } else
                            sw = true;
                    }
                }
                str = key.split(",");
                if(sw){
                    for(int i=0;i<=6;i+=2) list.add(new V(Integer.parseInt(str[0]),Integer.parseInt(str[1]),sumM,sumS,i));
                }else{
                    for(int i=1;i<=7;i+=2) list.add(new V(Integer.parseInt(str[0]),Integer.parseInt(str[1]),sumM,sumS,i));
                }

            }else{
                list.add(temp.get(0));
            }
        }
    }

    private static void move(Map<String, List<V>> map) {
        while(!list.isEmpty()){
            V v = list.remove(0);

            int speed = v.s;
            int x = v.x;
            int y = v.y;

            while(speed-->0){
                x = calculatorCoordinate(x+dx[v.d]);
                y = calculatorCoordinate(y+dy[v.d]);
            }

            List<V> temp = new ArrayList<>();
            if(map.get(x+","+y)==null){ // 값이 비어있다
                temp.add(new V(x,y,v.m,v.s,v.d));
                map.put(x+","+y,temp);
            }else{
                temp = map.get(x+","+y);
                temp.add(new V(x,y,v.m,v.s,v.d));
            }
        }
    }

    public static int calculatorCoordinate(int coordinate){
        if(coordinate>N) return 1;
        else if(coordinate<=0) return N;
        return coordinate;
    }
}

class V{
    int x; // x좌표
    int y; // y좌표
    int m; // 질량
    int s; // 속도
    int d; // 방향
    public V(int x,int y,int m, int s, int d){
        this.x=x;
        this.y=y;
        this.m=m;
        this.s=s;
        this.d=d;
    }
}