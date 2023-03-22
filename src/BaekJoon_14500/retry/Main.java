package BaekJoon_14500.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, M;
    static int[][] array;
    static boolean[][] check;
    static int MAX = Integer.MIN_VALUE;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][][] value = {{{0, 0}, {1, 0}, {1, 1}, {2, 0}}, {{0, 0}, {0, 1}, {0, 2}, {-1, 1}}, {{0, 0}, {0, 1}, {0, 2}, {1, 1}}, {{0, 0}, {0, 1}, {-1, 1}, {1, 1}}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][M];
        check = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            str = br.readLine().split(" ");
            for (int j = 0; j < M; j++) array[i][j] = Integer.parseInt(str[j]);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(i, j, 0, 0);
                int sum = 0;
                for (int k = 0; k < 4; k++) {
                    sum = 0;
                    for (int k2 = 0; k2 < 4; k2++) {
                        int x = i + value[k][k2][0];
                        int y = j + value[k][k2][1];
                        if (x < 0 || x >= N || y < 0 || y >= M) {
                            sum = 0;
                            break;
                        }
                        sum += array[x][y];
                    }
                    MAX = Math.max(sum, MAX);
                }
            }
        }
        System.out.println(MAX);
    }

    public static void dfs(int startX, int startY, int sum, int depth) {
        if (depth == 4) {
            MAX = Math.max(MAX, sum);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int x = startX + dx[i];
            int y = startY + dy[i];
            if (x < 0 || x >= N || y < 0 || y >= M || check[x][y]) continue;
            check[x][y] = true;
            dfs(x, y, sum + array[x][y], depth + 1);
            check[x][y] = false;
        }
    }
}