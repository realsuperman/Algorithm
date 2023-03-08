package BaekJoon_17281.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] array;
    static boolean[] check = new boolean[9];
    static int MAX_SCORE = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N][9];
        for(int i=0;i<N;i++){
            String[] str =  br.readLine().split(" ");
            for(int j=0;j<str.length;j++) array[i][j]= Integer.parseInt(str[j]);
        }
        dfs(0, new ArrayList<>());
        System.out.println(MAX_SCORE);
    }

    public static void dfs(int depth, List<Integer> list){
        if(depth==8){
            list.add(3, 0);
            int score = 0;
            int index = 0;

            for(int i=0;i<N;i++){ // 이닝까지 반복
                int out = 0;
                boolean[] base = new boolean[3]; // 1루, 2루, 3루

                while(out<3){
                    int v = array[i][list.get(index)]; // 해당 선수가 해당 이닝에 어떤 행위를 하는가
                    switch (v){
                        case 1 :
                            if(base[2]) { score++; base[2]=false; }
                            if(base[1]){ base[2]=true; base[1]=false;}
                            if(base[0]){ base[1]=true; base[0]=false;}
                            base[0]=true;
                            break;
                        case 2 :
                            if(base[2]) { score++; base[2]=false; }
                            if(base[1]) { score++; base[1]=false; }
                            if(base[0]) { base[2]=true; base[0]=false;}
                            base[1]=true;
                            break;
                        case 3 :
                            if(base[2]) { score++; base[2]=false; }
                            if(base[1]) { score++; base[1]=false; }
                            if(base[0]) { score++; base[0]=false;}
                            base[2]=true;
                            break;
                        case 4 :
                            for(int j=0;j<3;j++) if(base[j]) score++; // 주자에 나가있는 선수들의 합 (최대 3)
                            score++; // 내 자신의 합
                            base = new boolean[3];
                            break;
                        default : out++; // 아웃
                    }

                    index = index+1>8?0:index+1;
                }
            }

            MAX_SCORE = MAX_SCORE<score?score:MAX_SCORE;
            list.remove(3);
            return;
        }

        for(int i=1;i<9;i++){
            if(check[i]) continue;
            list.add(i);
            int size = list.size()-1;
            check[i]=true;
            dfs(depth+1, list);
            check[i]=false;
            list.remove(size);
        }
    }
}