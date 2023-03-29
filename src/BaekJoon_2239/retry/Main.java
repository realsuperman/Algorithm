package BaekJoon_2239.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static boolean sw = false;
    static int[][] array = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < str.length; j++) array[i][j] = Integer.parseInt(str[j]);
        }
        dfs(findZero());
    }

    public static void dfs(int start) {
        if (sw || isEnd()) {
            if (!sw) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) System.out.print(array[i][j]);
                    System.out.println();
                }
                sw = true;
            }
            return;
        }
        int x = start / 9;
        int y = start % 9;
        for (int k = 1; k <= 9; k++) {
            array[x][y] = k;
            if (checkConditions(x, y)) continue;
            dfs(findZero());
        }
        array[x][y] = 0;
    }

    public static boolean isEnd() {
        for (int i = 0; i < 9; i++) for (int j = 0; j < 9; j++) if (array[i][j] == 0) return false;
        return true;
    }

    public static int findZero() {
        int count = -1;
        boolean sw = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                count++;
                if (array[i][j] == 0) {
                    sw = true;
                    break;
                }
            }
            if (sw) break;
        }
        return count;
    }

    public static boolean checkConditions(int x, int y) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 9; i++) if (array[i][y] != 0 && !set.add(array[i][y])) return true;
        set = new HashSet<>();
        for (int i = 0; i < 9; i++) if (array[x][i] != 0 && !set.add(array[x][i])) return true;
        set = new HashSet<>();
        int startX = (x / 3) * 3;
        int startY = (y / 3) * 3;
        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
                if (array[i][j] != 0 && !set.add(array[i][j])) return true;
            }
        }
        return false;
    }
}