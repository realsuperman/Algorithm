package BaekJoon_9252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();
        int[][] array = new int[str1.length()+1][str2.length()+1];

        for(int i=1;i<array.length;i++){
            for(int j=1;j<array[i].length;j++){
                if(str2.charAt(j-1)==str1.charAt(i-1)){
                    array[i][j] = array[i-1][j-1]+1;
                }else{
                    array[i][j] = Integer.max(array[i-1][j],array[i][j-1]);
                }
            }
        }

        System.out.println(array[str1.length()][str2.length()]);
        if(array[str1.length()][str2.length()]>0){
            int x = str1.length();
            int y = str2.length();
            StringBuilder sb = new StringBuilder();

            while(array[x][y]>0){
                if(array[x-1][y]==array[x][y]){
                    x=x-1;
                }else if(array[x][y-1]==array[x][y]){
                    y=y-1;
                }else{
                    sb.append(str1.charAt(x-1));
                    x=x-1;
                    y=y-1;
                }
            }
            System.out.println(sb.reverse().toString());
        }
    }
}
