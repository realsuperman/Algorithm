package BaekJoon_7569.retry;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    static int[] dz = {0,0,0,0,1,-1};
    static int[] dx = { 1, -1, 0, 0,0,0 };
    static int[] dy = { 0, 0, 1, -1,0,0 };
    static int N, M, H;
    static int[][][] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[1]);
        M = Integer.parseInt(str[0]);
        H = Integer.parseInt(str[2]);
        array = new int[H][N][M];

        for(int k=0;k<H;k++) {
            for (int i = 0; i < N; i++) {
                str = br.readLine().split(" ");
                for (int j = 0; j < M; j++) {
                    array[k][i][j] = Integer.parseInt(str[j]);
                }
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        if (isOk()) return 0;

        Queue<V> queue = new PriorityQueue<>();
        boolean[][][] check = new boolean[H][N][M];
        int day = 0;
        for(int k=0;k<H;k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (array[k][i][j] == 1) {
                        queue.add(new V(k,i, j, day));
                        check[k][i][j] = true;
                    }
                }
            }
        }

        while (!queue.isEmpty()) {
            while (!queue.isEmpty()) {
                V v = queue.remove();
                if (v.depth != day){ queue.add(v); break;}
                for (int i = 0; i < 6; i++) {
                    int x = v.x + dx[i];
                    int y = v.y + dy[i];
                    int h = v.h + dz[i];
                    if (h>=H || h<0|| x >= N || x < 0 || y >= M || y < 0 || array[h][x][y] != 0 || check[h][x][y]) continue;
                    check[h][x][y] = true;
                    array[h][x][y] = 1;
                    queue.add(new V(h,x, y, day + 1));
                }
            }
            day++;
            if (isOk()) return day;
        }
        if (isOk()) return day;
        return -1;
    }

    public static boolean isOk() {
        for(int k=0;k<H;k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (array[k][i][j] == 0)
                        return false;
                }
            }
        }
        return true;
    }

}

class V implements Comparable<V> {
    int h;
    int x;
    int y;
    int depth;

    public V(int h,int x, int y, int depth) {
        this.h=h;
        this.x = x;
        this.y = y;
        this.depth = depth;
    }

    @Override
    public int compareTo(V o) {
        return this.depth - o.depth;
    }
}