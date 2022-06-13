package BaekJoon_14226;

import java.util.*;

public class Main{
    static boolean[][] check = new boolean[1001][1001];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(0,1,0));
        check[1][0]=true;
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.curScreen==N){System.out.println(v.time); return;}
            if(!check[v.curScreen][v.curScreen]){check[v.curScreen][v.curScreen]=true; queue.add(new V(v.curScreen,v.curScreen,v.time+1));}
            if(v.cnt>0 && v.curScreen+v.cnt<=1000 && !check[v.curScreen+v.cnt][v.cnt]){check[v.curScreen+v.cnt][v.cnt]=true; queue.add(new V(v.cnt,v.curScreen+v.cnt,v.time+1));}
            if(v.curScreen-1>=2&&!check[v.curScreen-1][v.cnt]){check[v.curScreen-1][v.cnt]=true; queue.add(new V(v.cnt,v.curScreen-1,v.time+1));}
        }
    }
}

class V{
    int cnt;
    int curScreen;
    int time;
    public V(int cnt,int curScreen,int time){
        this.cnt=cnt;
        this.curScreen=curScreen;
        this.time=time;
    }
}