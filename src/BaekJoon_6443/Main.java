package BaekJoon_6443;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    static int[] check;
    static Set<String> set;
    static char[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<N;i++){
            set = new TreeSet<>();
            arr = br.readLine().toCharArray();
            check = new int[26];
            for(char c : arr)  check[c-'a']++;
            solution(0,arr.length,new ArrayList<>());
            for(String str : set) sb.append(str+"\n");
        }
        System.out.println(sb);
    }
    public static void solution(int start, int dest, List<Character> list){
        if(start==dest){
            String str = "";
            for(char ch : list) str+=ch;
            set.add(str);
            return;
        }

        for(int i=0; i<26; i++){
            if(check[i] > 0) {
                check[i]--;
                list.add((char)(i+'a'));
                int size = list.size();
                solution(start+1,dest,list);
                list.remove(size-1);
                check[i]++;
            }
        }
    }
}