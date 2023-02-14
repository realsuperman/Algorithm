package BaekJoon_2002.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<String> start = new LinkedList<>();
        List<String> end = new LinkedList<>();
        for(int i=0;i<N;i++) start.add(br.readLine());
        for(int i=0;i<N;i++) end.add(br.readLine());
        int result = 0;
        for(String s : end){
            if(!s.equals(start.get(0))) result++;
            start.remove(s);
        }
        System.out.println(result);
    }
}