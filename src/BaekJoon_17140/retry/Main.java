package BaekJoon_17140.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] array = new int[3][3];
    static int R,C,K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        R = Integer.parseInt(str[0])-1;
        C = Integer.parseInt(str[1])-1;
        K = Integer.parseInt(str[2]);
        for(int i=0;i<3;i++){
            str=br.readLine().split(" ");
            for(int j=0;j<3;j++) array[i][j] = Integer.parseInt(str[j]);
        }

        int time = 0;
        while(time<=100){
            if(R<array.length&&C<array[0].length&&array[R][C] == K) {
                System.out.println(time);
                return;
            }
            int rowCount = array.length;
            int columnCount = array[0].length;
            if(rowCount>=columnCount) rOpration();
            else cOpration();
            resizeArray();
            time++;
        }
        System.out.println(-1);
    }

    private static void resizeArray() {
        int r = array.length;
        int c = array[0].length;
        int[][] temp = new int[r][c];
        if(array.length>100) r = 100;
        if(array[0].length>100) c = 100;

        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                temp[i][j]=array[i][j];
            }
        }
        array = temp;
    }

    private static void rOpration(){
        int r = 0;
        int c = 0;
        String[] str;
        String[] temp;

        List<String> list = new ArrayList<>();
        for(int i=0;i<array.length;i++){
            Map<Integer, Integer> map = new HashMap<>();
            for(int j=0;j<array[i].length;j++){
                if(array[i][j]==0) continue;

                if(map.get(array[i][j])==null) map.put(array[i][j],1);
                else map.put(array[i][j],map.get(array[i][j])+1);
            }
            Queue<V> queue = new PriorityQueue<>();
            for(int v : map.keySet()) queue.add(new V(v, map.get(v)));
            StringBuilder sb = new StringBuilder();
            while(!queue.isEmpty()){
                V v = queue.remove();
                sb.append(v.index+"/"+v.value+" ");
            }
            list.add(sb.toString());
            str = list.get(i).split(" ");
            c = c<str.length*2?str.length*2:c;
        }

        r = list.size();
        array = new int[r][c];

        for(int i=0;i<r;i++){
            List<String> value = new ArrayList<>();
            str = list.get(i).split(" ");
            for(int k=0;k<str.length;k++){
                temp = str[k].split("/");
                for(int j=0;j<temp.length;j++) value.add(temp[j]);
            }

            int j = 0;
            for(String v : value) array[i][j++] = Integer.parseInt(v);
        }
    }

    private static void cOpration(){
        int r = 0;
        int c = 0;
        String[] str;
        String[] temp;

        List<String> list = new ArrayList<>();
        for(int i=0;i<array[0].length;i++){
            Map<Integer, Integer> map = new HashMap<>();
            for(int j=0;j<array.length;j++){
                if(array[j][i]==0) continue;

                if(map.get(array[j][i])==null) map.put(array[j][i],1);
                else map.put(array[j][i],map.get(array[j][i])+1);
            }
            Queue<V> queue = new PriorityQueue<>();
            for(int v : map.keySet()) queue.add(new V(v, map.get(v)));
            StringBuilder sb = new StringBuilder();
            while(!queue.isEmpty()){
                V v = queue.remove();
                sb.append(v.index+"/"+v.value+" ");
            }
            list.add(sb.toString());
            str = list.get(i).split(" ");
            c = c<str.length*2?str.length*2:c;
        }

        r = list.size();
        array = new int[c][r];

        for(int i=0;i<r;i++){
            List<String> value = new ArrayList<>();
            str = list.get(i).split(" ");
            for(int k=0;k<str.length;k++){
                temp = str[k].split("/");
                for(int j=0;j<temp.length;j++) value.add(temp[j]);
            }

            int j = 0;
            for(String v : value) array[j++][i] = Integer.parseInt(v);
        }
    }
}

class V implements Comparable<V>{
    int index;
    int value;
    public V(int index, int value){
        this.index=index;
        this.value=value;
    }
    @Override
    public int compareTo(V o) {
        if(this.value==o.value){
            return this.index-o.index;
        }
        return this.value-o.value;
    }
}