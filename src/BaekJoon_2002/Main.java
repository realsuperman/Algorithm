package BaekJoon_2002;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] start = new String[N];
        String[] end = new String[N];
        for(int i=0;i<N;i++) start[i]=br.readLine();
        for(int i=0;i<N;i++) end[i]=br.readLine();

        int result = 0;
        boolean check[]=new boolean[N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(end[j].equals(start[i])) {
                    check[j]=true;
                    break;
                }
                if(check[j]) continue;
                result++;
                check[j]=true;
            }
        }

        System.out.println(result);
    }
}