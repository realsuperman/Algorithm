package BaekJoon_5528;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();

        int[][] array = new int[str1.length()+1][str2.length()+1];
        int value = 0;
        for(int i=1;i<array.length;i++){
            for(int j=1;j<array[i].length;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    array[i][j] = array[i-1][j-1]+1;
                    if(array[i][j]>value) value = array[i][j];
                }
            }
        }
        System.out.println(value);
    }
}