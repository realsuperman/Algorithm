package BaekJoon_9207.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[] result = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        char[][] array = new char[5][9];
        while (T-- > 0) {
            result[0] = Integer.MAX_VALUE;
            result[1] = Integer.MAX_VALUE;
            for (int i = 0; i < 5; i++) {
                String[] str = br.readLine().split("");
                for (int j = 0; j < str.length; j++) array[i][j] = str[j].charAt(0);
            }
            if (T > 0) br.readLine();
            dfs(array, 0);
            System.out.println(result[0] + " " + result[1]);
        }
    }

    public static void dfs(char[][] array, int cnt) {
        char[][] temp = new char[5][9];
        for (int i = 0; i < 5; i++) for (int j = 0; j < 9; j++) temp[i][j] = array[i][j];
        cal(array, cnt);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (array[i][j] == 'o') {
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (x >= 5 || x < 0 || y >= 9 || y < 0 || array[x][y] != 'o') continue;
                        int moveX = x + dx[k];
                        int moveY = y + dy[k];
                        if (moveX >= 5 || moveX < 0 || moveY >= 9 || moveY < 0 || array[moveX][moveY] != '.') continue;
                        array[x][y] = '.';
                        array[moveX][moveY] = 'o';
                        array[i][j] = '.';
                        dfs(array, cnt + 1);
                        for (int index1 = 0; index1 < 5; index1++)
                            for (int index2 = 0; index2 < 9; index2++) array[index1][index2] = temp[index1][index2];
                    }
                }
            }
        }
    }

    public static void cal(char[][] array, int moveCnt) {
        int count = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) if (array[i][j] == 'o') count++;
        }
        if (count < result[0] || (count == result[0] && moveCnt < result[1])) {
            result[0] = count;
            result[1] = moveCnt;
        }
    }
}