package BaekJoon_9466;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int size,cnt;
    static int[] array;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            size = Integer.parseInt(br.readLine());
            array = new int[size+1];
            check = new boolean[size+1];
            String[] str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++) array[j+1] = Integer.parseInt(str[j]);
            cnt = 0;
            for(int j=1;j<=size;j++){
                if(check[j]) continue;
                List<Integer> list = new ArrayList<>();
                solution(j,j,list);
                check[j]=true;
            }
            System.out.println(size-cnt);
        }
    }
    public static void solution(int index, int target, List<Integer> list){
        if(index==array[index]){
            check[index] = true;
            cnt++;
            return;
        }else if(target==array[index]){
            check[index] = true;
            cnt++;
            cnt+=list.size();
            return;
        }
        if(!check[array[index]]) { // 선택 노드가 방문 안된 경우
            check[index] = true;
            list.add(index);
            solution(array[index], target, list);
        }else for(int i : list) check[i]=false;
    }
}