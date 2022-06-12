package BaekJoon_1759;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int seatNumber;
    static int loop;
    static char[] array;
    static boolean[] check;
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        seatNumber = Integer.parseInt(str[0]);
        loop = Integer.parseInt(str[1]);
        array = new char[loop];
        check = new boolean[loop];
        str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) array[i] = str[i].charAt(0);
        Arrays.sort(array);
        password(0,0);
        System.out.println(result);
    }

    public static void password(int start,int num){
        if(num==seatNumber){
            StringBuilder sb = new StringBuilder();

            int m = 0;
            int g = 0;
            for(int i=0;i<loop;i++){
                if(check[i]){
                    sb.append(array[i]);
                    if(array[i]=='a' || array[i]=='e' || array[i]=='i' || array[i]=='o' || array[i]=='u'){
                        m++;
                    }else{
                        g++;
                    }
                }
            }
            if(m>=1 && g>=2) {
                result.append(sb+"\n");
            }
            return;
        }
        for(int i=start;i<loop;i++){
            if(check[i]) continue;
            check[i] = true;
            password(i,num+1);
            check[i] = false;
        }
    }
}