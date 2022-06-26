package BaekJoon_1339;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static String[] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new String[N];
        Map<Character,Integer> map = new HashMap<>();
        int[] value = {10000000,1000000,100000,10000,1000,100,10,1};

        for(int i=0;i<N;i++) array[i]=br.readLine();

        for(int i=0;i<array.length;i++){
            int len = 8-array[i].length();

            for(int j=0;j<array[i].length();j++){
                if(map.get(array[i].charAt(j))==null){
                    map.put(array[i].charAt(j),value[j+len]);
                }else{
                    map.put(array[i].charAt(j),map.get(array[i].charAt(j))+value[j+len]);
                }
            }
        }

        List<V> list = new ArrayList<>();
        for(Character ch : map.keySet()) list.add(new V(ch,map.get(ch)));
        Collections.sort(list);
        map = new HashMap<>();

        int num = 9;
        for(int i=0;i<list.size();i++) map.put(list.get(i).ch,num--);

        int sum = 0;
        for(int i=0;i<array.length;i++){
            String st = "";
            for(int j=0;j<array[i].length();j++){
                st+=map.get(array[i].charAt(j));
            }
            sum+=Integer.parseInt(st);
        }
        System.out.println(sum);
    }
}

class V implements Comparable<V>{
    char ch;
    int num;
    public V(char ch,int num){
        this.ch=ch;
        this.num=num;
    }

    @Override
    public int compareTo(V o) {
        return o.num-this.num;
    }
}