package BaekJoon_16953.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int from = Integer.parseInt(str[0]);
        int to = Integer.parseInt(str[1]);

        int cnt = 0;
        while(true){
            if(to<from) { System.out.println(-1); return;}
            if(to==from) {System.out.println(cnt+1); return;}

            cnt++;
            StringBuilder sb = new StringBuilder();
            sb.append(to);
            if(to%2==0) to/=2;
            else if(sb.charAt(sb.length()-1)=='1'){
                to = Integer.parseInt(sb.deleteCharAt(sb.length()-1).toString());
            }
            else { System.out.println(-1); return; }
        }

    }
}