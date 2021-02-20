package BaekJoon_1541;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split("-");
        String str2[];
        int[] array = new int[str.length];
        int sum=0;
        int index=0;

        for(int i=0;i<str.length;i++){
            str2 = str[i].split("\\+");
            sum=0;
            for(int j=0;j<str2.length;j++){
                sum+=Integer.parseInt(str2[j]);
            }
            array[index++] = sum;
        }

        sum=array[0];
        for(int i=1;i<array.length;i++){
            sum = sum-array[i];
        }
        System.out.print(sum);
    }

}
