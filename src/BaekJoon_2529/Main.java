package BaekJoon_2529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static String MIN,MAX;
    static int N;
    static List<Character> list = new ArrayList<>();
    static List<Integer> value = new ArrayList<>();
    static boolean[] check = new boolean[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        String[] str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) list.add( str[i].charAt(0));
        solution(0,-2);
        System.out.println(MAX);
        System.out.println(MIN);
    }
    public static void solution(int num,int index){
        if(num>=2){
            boolean chk = false;
            char ch = list.get(index);
            int from = value.get(num-2);
            int to = value.get(num-1);
            if(ch=='>' && from>to) chk = true;
            else if(ch=='<' && from<to) chk=true;
            if(!chk) return;
        }

        if(num==N+1){
            String from = "";
            for(int i=0;i<value.size();i++) from+=value.get(i);

            long compare = Long.valueOf(from);
            if(MIN==null) MIN = from;
            else{
                long comp2 = Long.valueOf(MIN);
                if(compare<comp2) MIN = from;
            }

            if(MAX==null) MAX = from;
            else{
                long comp2 = Long.valueOf(MAX);
                if(compare>comp2) MAX = from;
            }
            return;
        }


        for(int i=0;i<=9;i++){
            if(check[i]) continue;
            check[i]=true;
            value.add(i);
            int size = value.size();
            solution(num+1,index+1);
            value.remove(size-1);
            check[i]=false;
        }

    }
}