package BaekJoon_7569;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int column = Integer.parseInt(str[0]); int row = Integer.parseInt(str[1]); int height = Integer.parseInt(str[1]);
        int[][][] array = new int[height][row][column];

        for(int i=0;i<height;i++){
            for(int j=0;j<row;j++){
                for(int k=0;k<column;k++){

                }
            }
        }



    }
}