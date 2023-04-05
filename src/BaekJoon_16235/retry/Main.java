package BaekJoon_16235.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {-1,-1,-1,0,0,1,1,1};
    static int[] dy = {-1,0,1,-1,1,-1,0,1};
    static int N,M,K;
    static int[][] array; // 추가하는양
    static List<V>[][] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        K = Integer.parseInt(str[2]);

        array = new int[N][N];
        list = new List[N][N];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                array[i][j]= Integer.parseInt(str[j]);
                List<V> temp = new LinkedList<>();
                list[i][j] = temp;
            }
        }

        int[][] real = new int[N][N];
        for(int i=0;i<N;i++) for(int j=0;j<N;j++) real[i][j]=5;

        while(M-->0){
            str = br.readLine().split(" ");
            int x = Integer.parseInt(str[0])-1;
            int y = Integer.parseInt(str[1])-1;
            int age = Integer.parseInt(str[2]);
            list[x][y].add(new V(age));
        }

        // K초 이후의 시간이 흐른 뒤 살아남은 나무의 수
        while(K-->0){
            for(int i=0;i<N;i++) { // 봄 처리 후 여름 처리
                for (int j = 0; j < N; j++) {
                    List<V> temp = list[i][j];
                    if(temp.size()==0) continue;

                    List<V> nextList = new LinkedList<>(); // 봄 처리 후 저장되는 리스트들
                    int addValue = 0; // 더해지는 양분 양

                    for (V v : temp) { // 양분 공급여부 확인
                        if (v.age <= real[i][j]) { // 자신의 나이만큼 양분 먹기 가능
                            real[i][j] -= v.age;
                            nextList.add(new V(v.age + 1));
                        } else { // 자신의 나이만큼 양분 먹기 불가
                            addValue = addValue + (v.age / 2);
                        }
                    }

                    list[i][j]=nextList;
                    real[i][j] += addValue; // 여름 처리
                }
            }

            for(int i=0;i<N;i++){ // 가을
                for(int j=0;j<N;j++) {
                    List<V> temp = list[i][j];
                    for (V v : temp) {
                        if (v.age % 5 == 0) {
                            for (int k = 0; k < 8; k++) { // 번식
                                int x = i + dx[k];
                                int y = j + dy[k];
                                if (x >= N || x < 0 || y >= N || y < 0) continue;
                                List<V> t = list[x][y];
                                t.add(0,new V(1));
                            }
                        }
                    }
                }
            }

            for(int i=0;i<N;i++) for(int j=0;j<N;j++) real[i][j]+=array[i][j]; // 겨울 처리
        }

        int sum = 0;
        for(int i=0;i<N;i++) for(int j=0;j<N;j++){ List<V> temp = list[i][j]; if(temp.size()>0) sum+=temp.size();}
        System.out.println(sum);
    }
}

class V{
    int age;
    public V(int age){
        this.age=age;
    }
}