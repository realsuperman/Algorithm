package BaekJoon_2580;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] array = new int[9][9];
        for(int i=0;i<9;i++){
            String[] str = br.readLine().split(" ");
            for(int j=0;j<9;j++) array[i][j] = Integer.parseInt(str[j]);
        }

        back(array);
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void back(int[][] array){
        int i = 0, j = 0;
        boolean chk = false;
        List<Integer> list = new ArrayList<>();

        for(i=0;i<9;i++){
            for(j=0;j<9;j++){
                if(array[i][j]==0){
                    list = promising(array,i,j);
                    chk = true;
                    break;
                }
            }
            if(chk) break;
        }

        if(list.size()==0) return;
        else if(list.size()==1){ array[i][j] = list.get(0); if(!check(array)) back(array); }
        else{
            for(int k=0;k<list.size();k++){
                if(check(array)) return;
                array[i][j] = list.get(k);
                back(array);
            }
        }
        if(!check(array)) array[i][j]=0;
    }

    public static List<Integer> promising(int[][] array,int x,int y){
        int[] num = new int[10];
        for(int i=1;i<10;i++) num[i] = 1;

        int start = x%9-x%3;
        int end = y%9-y%3;

        for(int i=start;i<start+3;i++){
            for(int j=end;j<end+3;j++){
                num[array[i][j]]=0;
            }
        }

        for(int i=0;i<9;i++){
            num[array[x][i]]=0;
            num[array[i][y]]=0;
        }
        List<Integer> list = new ArrayList<>();
        for(int i=1;i<10;i++) if(num[i]==1) list.add(i);
        return list;
    }

    public static boolean check(int[][] array){
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[i].length;j++){
                if(array[i][j]==0) return false;
            }
        }
        return true;
    }
}