package BaekJoon_10871;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int target = Integer.parseInt(str[1]);

        str = br.readLine().split(" ");
        for(int i=0;i<n;i++){
            if(Integer.parseInt(str[i])<target) sb.append(str[i]+" ");
        }
        System.out.print(sb);
    }

}
