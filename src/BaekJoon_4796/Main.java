package BaekJoon_4796;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int number = 1;
        while(true){
            String[] str = br.readLine().split(" ");
            if(Integer.parseInt(str[0])==0&&Integer.parseInt(str[1])==0&&Integer.parseInt(str[2])==0) break;
            int L = Integer.parseInt(str[0]);
            int P = Integer.parseInt(str[1]);
            int V = Integer.parseInt(str[2]);
            int N = (V/P)*L;

            if(V%P<L) N+=V%P;
            else N+=L;

            sb.append("Case "+number+": "+N+"\n");
            number++;
        }
        System.out.println(sb);
    }
}