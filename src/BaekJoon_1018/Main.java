package BaekJoon_1018;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static String[] chess1 ={"WBWBWBWB","BWBWBWBW","WBWBWBWB","BWBWBWBW","WBWBWBWB","BWBWBWBW","WBWBWBWB","BWBWBWBW"};
    static String[] chess2 ={"BWBWBWBW","WBWBWBWB","BWBWBWBW","WBWBWBWB","BWBWBWBW","WBWBWBWB","BWBWBWBW","WBWBWBWB"};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        String[][] ctr;
        int row = Integer.parseInt(str[0]); int column = Integer.parseInt(str[1]);
        ctr = new String[row][column];
        int cnt = 2147483647;

        for(int i=0;i<row;i++){
            str = br.readLine().split("");
            for(int j=0;j<column;j++){
                ctr[i][j]  = str[j];
            }
        }

        int start=0;
        int start2=0;
        str = new String[8];

        while(true){
            int min = 0;
            int min2 = 0;
            for(int i=0;i<8;i++) str[i] = "";

            for(int i=start;i<start+8;i++){
                for(int j=start2;j<start2+8;j++){
                    str[min] += ctr[i][j];
                }
                min++;
            }

            min=0;
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if(str[i].charAt(j) != chess1[i].charAt(j)) min++;
                    if(str[i].charAt(j) != chess2[i].charAt(j)) min2++;
                }
            }

            min = min<min2?min:min2;
            if(min<cnt) cnt = min;

            if(((start+8) == row) && ((start2+8) == column)){
                break;
            }

            if(start2+8 < column){
                start2++;
            }
            else if(start+8 < row){
                start++;
                start2 = 0;
            }
        }
        System.out.print(cnt);
    }
}