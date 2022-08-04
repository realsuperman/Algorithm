package BaekJoon_20365;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] array = new char[N];
        String[] str = br.readLine().split("");

        int R = 0;
        int B = 0;
        for(int i=0;i<str.length;i++){
            array[i]=str[i].charAt(0);
            if(array[i]=='R') R++;
            else B++;
        }
        int t1 = solution(N,array,'R','B');
        int t2 = solution(N,array,'B','R');
        System.out.println(t1>t2?t2:t1);
    }

    public static int solution(int N,char[] array,char origin,char target){
        int result = 1;
        for(int i=0;i<N;i++){
            if(array[i]==origin){
                result++;
                int j;
                for(j=i+1;j<N;j++){
                    if(array[j]==target) break;
                }
                i=j-1;
            }
        }
        return result;
    }
}