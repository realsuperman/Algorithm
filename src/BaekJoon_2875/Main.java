package BaekJoon_2875;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int wCnt = sc.nextInt();
        int mCnt = sc.nextInt();
        int internCnt = sc.nextInt();
        int max = 0;

        int c = internCnt;
        while (c >= 0){
            int w = wCnt-c;
            int m = mCnt-(internCnt-c);

            int cnt = w/2;
            int realCnt = 0;
            while(cnt>0){
                if(m>0){
                    realCnt++;
                    m--;
                }
                cnt--;
            }
            if(max < realCnt) max = realCnt ;

            c--;
        }
        System.out.print(max);
    }
}