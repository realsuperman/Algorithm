package BaekJoon_7576.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static int N, M;
    static int[][] array;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[1]);
        M = Integer.parseInt(str[0]);
        array = new int[N][M];
        for (int i = 0; i < N; i++) {
            str = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                array[i][j] = Integer.parseInt(str[j]);
            }
        }
        System.out.println(bfs());
    }

    public static int bfs() {
        if (isOk()) return 0;
        Queue<V> queue = new PriorityQueue<>();
        boolean[][] check = new boolean[N][M];
        int day = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (array[i][j] == 1) {
                    queue.add(new V(i, j, day));
                    check[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            while (!queue.isEmpty()) {
                V v = queue.remove();
                if (v.depth != day){ queue.add(v); break;}
                for (int i = 0; i < 4; i++) {
                    int x = v.x + dx[i];
                    int y = v.y + dy[i];
                    if (x >= N || x < 0 || y >= M || y < 0 || array[x][y] != 0 || check[x][y]) continue;
                    check[x][y] = true;
                    array[x][y] = 1;
                    queue.add(new V(x, y, day + 1));
                }
            }
            day++;
            if (isOk()) return day;
        }
        if (isOk()) return day;
        return -1;
    }

    public static boolean isOk() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (array[i][j] == 0) return false;
            }
        }
        return true;
    }
}
class V implements Comparable<V> {
    int x;
    int y;
    int depth;
    public V(int x, int y, int depth) {
        this.x = x;
        this.y = y;
        this.depth = depth;
    }

    @Override
    public int compareTo(V o) {
        return this.depth - o.depth;
    }

}