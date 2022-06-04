package BaekJoon_20055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N,K;
    static int[] C;
    static boolean[] robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        K = Integer.parseInt(str[1]);
        C = new int[2*N];
        robot = new boolean[N];

        str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) C[i] = Integer.parseInt(str[i]);

        int cnt = 0;
        while(!check()){
            int tmp = C[C.length - 1];
            for (int i = C.length - 1; i > 0; i--) {
                C[i] = C[i - 1];
            }
            C[0] = tmp;

            for (int i = robot.length - 1; i > 0; i--) {
                robot[i] = robot[i - 1];
            }
            robot[0] = false;
            robot[N - 1] = false;

            for (int i = robot.length - 1; i > 0; i--) {
                if (robot[i - 1] && !robot[i] && C[i] >= 1) {
                    C[i]--;
                    robot[i] = true;
                    robot[i - 1] = false;
                }
            }

            if (C[0] > 0) {
                robot[0] = true;
                C[0]--;
            }

            cnt++;
        }
        System.out.println(cnt);
    }

    public static boolean check() {
        int cnt = 0;
        for(int i=0;i<C.length;i++) if(C[i]==0) cnt++;
        return cnt>=K?true:false;
    }
}