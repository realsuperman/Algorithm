package BaekJoon_15685;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[][] array = new boolean[101][101];
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, -1, 0, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            String[] str = br.readLine().split(" ");
            drawDragonCurve(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]), Integer.parseInt(str[3]));
        }
        System.out.println(solution());
    }

    private static void drawDragonCurve(int startX, int startY, int startDirection, int generation) {
        int x = startX;
        int y = startY;
        array[x][y] = true;

        List<Integer> list = new ArrayList<>();
        list.add(startDirection);
        while (generation-- > 0) {
            int size = list.size() - 1;
            for (int i = size; i >= 0; i--)
                list.add((list.get(i) + 1) % 4);
        }
        for (int v : list) {
            x += dx[v];
            y += dy[v];
            if (x >= 101 || x < 0 || y >= 101 || y < 0)
                break;
            array[x][y] = true;
        }
    }

    private static int solution() {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (array[i][j] && array[i][j + 1] && array[i + 1][j] && array[i + 1][j + 1])
                    sum++;
            }
        }
        return sum;
    }
}