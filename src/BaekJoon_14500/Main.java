package BaekJoon_14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int min = Integer.MIN_VALUE;
    static int[][][] BLOCKS = {
            {{0,0}, {0,1}, {0,2}, {0,3}}
            ,{{0,0}, {1,0}, {2,0}, {3,0}}
            ,{{0,0}, {1,0}, {0,1}, {1,1}}
            ,{{0,0}, {0,1}, {0,2}, {1,2}}
            ,{{0,0}, {1,0}, {2,0}, {0,1}}
            ,{{0,0}, {1,0}, {1,1}, {1,2}}
            ,{{2,0}, {0,1}, {1,1}, {2,1}}
            ,{{1,0}, {1,1}, {1,2}, {0,2}}
            ,{{0,0}, {0,1}, {1,1}, {2,1}}
            ,{{0,0}, {1,0}, {0,1}, {0,2}}
            ,{{0,0}, {1,0}, {2,0}, {2,1}}
            ,{{0,0}, {1,0}, {2,0}, {1,1}}
            ,{{1,0}, {0,1}, {1,1}, {1,2}}
            ,{{0,1}, {1,0}, {1,1}, {2,1}}
            ,{{0,0}, {0,1}, {0,2}, {1,1}}
            ,{{0,0}, {0,1}, {1,1}, {1,2}}
            ,{{0,1}, {1,1}, {1,0}, {2,0}}
            ,{{1,0}, {1,1}, {0,1}, {0,2}}
            ,{{0,0}, {1,0}, {1,1}, {2,1}}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        int[][] array = new int[N][M];

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                array[i][j] = Integer.parseInt(str[j]);
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                solution(i,j,array,N,M);
            }
        }
        System.out.println(min);

    }

    public static void solution(int i, int j, int[][] array,int N,int M) {
        for(int k=0;k<BLOCKS.length;k++){
            int sum = 0;
            for(int l=0;l<BLOCKS[k].length;l++){
                if(i+BLOCKS[k][l][0]>=0 && i+BLOCKS[k][l][0]<N && j+BLOCKS[k][l][1]>=0 && j+BLOCKS[k][l][1]<M){
                    sum+=array[i+BLOCKS[k][l][0]][j+BLOCKS[k][l][1]];
                }else{
                    sum = 0;
                    break;
                }
            }
            if(sum>0 && sum>min) min=sum;
        }
    }
}
