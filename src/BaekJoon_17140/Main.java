package BaekJoon_17140;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static int R,C,K,rowNum=3,columnNum=3;
    static int[][] array;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        R = Integer.parseInt(str[0]);
        C = Integer.parseInt(str[1]);
        K = Integer.parseInt(str[2]);
        array = new int[4][4];
        for(int i=1;i<=3;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                array[i][j+1]=Integer.parseInt(str[j]);
            }
        }
        for(int k=0;k<=100;k++){
            if(R<=rowNum && C<=columnNum &&array[R][C] == K){System.out.println(k); return;}
            if(rowNum>=columnNum) solution('R');
            else solution('C');
        }
        System.out.println(-1);
    }
    public static void solution(char type){
        if(type=='R'){
            List<String> list = new ArrayList<>();
            for(int i=1;i<=rowNum;i++){
                Map<Integer,Integer> map = new HashMap<>();
                StringBuilder sb = new StringBuilder();
                for(int j=1;j<=columnNum;j++){
                    if(array[i][j]==0) continue;
                    if(map.get(array[i][j])==null) map.put(array[i][j],1);
                    else map.put(array[i][j],map.get(array[i][j])+1);
                }
                Queue<V> queue = new PriorityQueue<>();
                for(int index : map.keySet()) queue.add(new V(index,map.get(index)));
                while(!queue.isEmpty()){
                    V v = queue.remove();
                    sb.append(v.v+" "+v.cnt+" ");
                }
                list.add(sb.toString());
            }

            int MAX = Integer.MIN_VALUE;
            for(int i=0;i<list.size();i++) {
                String[] s = list.get(i).split(" ");
                MAX = MAX<s.length?s.length:MAX;
            }
            rowNum = list.size();
            columnNum = MAX;
            if(rowNum>100) rowNum=100;
            if(columnNum>100) columnNum=100;

            array = new int[rowNum+1][columnNum+1];
            for(int i=1;i<=list.size();i++){
                if(list.get(i-1).length()<1) continue;
                String[] s = list.get(i-1).split(" ");
                for(int j=1;j<=s.length;j++){
                    if(j>=101) continue;
                    array[i][j] = Integer.parseInt(s[j-1]);
                }
            }
        }else{
            List<String> list = new ArrayList<>();
            for(int i=1;i<=columnNum;i++){
                Map<Integer,Integer> map = new HashMap<>();
                StringBuilder sb = new StringBuilder();
                for(int j=1;j<=rowNum;j++){
                    if(array[j][i]==0) continue;
                    if(map.get(array[j][i])==null) map.put(array[j][i],1);
                    else map.put(array[j][i],map.get(array[j][i])+1);
                }
                Queue<V> queue = new PriorityQueue<>();
                for(int index : map.keySet()) queue.add(new V(index,map.get(index)));
                while(!queue.isEmpty()){
                    V v = queue.remove();
                    sb.append(v.v+" "+v.cnt+" ");
                }
                list.add(sb.toString());
            }
            int MAX = Integer.MIN_VALUE;
            for(int i=0;i<list.size();i++) {
                String[] s = list.get(i).split(" ");
                MAX = MAX<s.length?s.length:MAX;
            }
            rowNum = MAX;
            columnNum = list.size();
            if(rowNum>100) rowNum=100; if(columnNum>100) columnNum=100;

            array = new int[rowNum+1][columnNum+1];
            for(int i=1;i<=list.size();i++){
                if(list.get(i-1).length()<1) continue;
                String[] s = list.get(i-1).split(" ");
                for(int j=1;j<=s.length;j++){
                    if(j>=101) continue;
                    array[j][i] = Integer.parseInt(s[j-1]);
                }
            }
        }
    }
}

class V implements Comparable<V>{
    int v;
    int cnt;

    public V(int v,int cnt){
        this.v=v;
        this.cnt=cnt;
    }

    public int compareTo(V o) {
        if(this.cnt==o.cnt){
            return this.v-o.v;
        }
        return this.cnt-o.cnt;
    }
}