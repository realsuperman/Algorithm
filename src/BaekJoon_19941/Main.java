package BaekJoon_19941;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int K = Integer.parseInt(str[1]);
        String table = br.readLine();

        boolean[] check = new boolean[N];
        for(int i=0;i<table.length();i++){
            if(table.charAt(i)=='P'){
                boolean chk = false;
                for(int j=i-K;j<=i;j++){
                    if (j >= table.length()) break;
                    if(j<0||check[j]) continue;
                    if(table.charAt(j)=='H'){ check[j]=true; chk=true; break;}
                }

                if(!chk) {
                    for (int j = i; j <= i + K; j++) {
                        if (j >= table.length()) break;
                        if (check[j]) continue;
                        if (table.charAt(j) == 'H') {
                            check[j] = true;
                            break;
                        }
                    }
                }
            }
        }

        int sum = 0;
        for(int i=0;i<N;i++) if(check[i]) sum+=1;
        System.out.println(sum);
    }
}