package BaekJoon_5052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            int N = Integer.parseInt(br.readLine());
            List<V> list = new ArrayList<>(N);
            for(int j=0;j<N;j++) list.add(new V(br.readLine()));
            Collections.sort(list);

            int[] array = new int[11];
            for(V v : list) array[v.str.length()]=1;

            boolean check = false;
            for(int j=0;j<11;j++){
                if(array[j]==0) continue;
                else{
                    Map<String,String> map = new HashMap<>();
                    for(V v : list){
                        if(v.str.length()<j) continue;

                        if(map.get(v.str.substring(0,j))!=null){
                            if(map.get(v.str.substring(0,j)).equals(v.str.substring(0,j))) {
                                check = true;
                                break;
                            }
                        }else {
                            map.put(v.str.substring(0, j).toString(),v.str.toString());
                        }
                    }
                    if(check){ break;}
                }
            }
            if(check) sb.append("NO"+"\n");
            else sb.append("YES"+"\n");
        }
        System.out.println(sb);
    }
}

class V implements Comparable<V>{
    StringBuilder str;
    public V(String str){
        this.str=new StringBuilder(str);
    }

    @Override
    public int compareTo(V o) {
        if(this.str.length()==o.str.length()){
            int comp1 = Integer.parseInt(this.str.toString());
            int comp2 = Integer.parseInt(o.str.toString());
            return comp1-comp2;
        }
        return this.str.length()-o.str.length();
    }
}