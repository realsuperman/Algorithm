package BaekJoon_17471;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static int MIN = -1;
    public static int[] array;
    public static boolean[][] connect;
    public static int N;
    public static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N+1];
        connect = new boolean[N+1][N+1];
        String[] str = br.readLine().split(" ");

        for(int i=0;i<str.length;i++) array[i+1] = Integer.parseInt(str[i]);

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<Integer.parseInt(str[0]);j++){
                connect[i+1][Integer.parseInt(str[j+1])] = true;
            }
        }

        for(int i=1;i<N;i++){
            solution(0,1,i);
        }
        System.out.println(MIN);

    }
    public static void solution(int location,int start,int depth){
        if(location==depth){
            List<Integer> a = new ArrayList<>();
            for(int v : list) a.add(v);
            List<Integer> b = new ArrayList<>();
            for(int i=1;i<=N;i++){
                boolean chk = false;
                for(int v : a) if(v==i){chk=true; break;}
                if(!chk) b.add(i);
            }

            if(check(a) && check(b)){
                int diff1 = 0;
                int diff2 = 0;
                for(int i=1;i<=N;i++){
                    boolean chk = false;
                    for(int v : a) if(v==i){diff1+=array[i]; chk=true; break;}
                    if(!chk) diff2+=array[i];
                }
                if((MIN==-1) || (MIN>(Math.abs(diff1-diff2)))){
                    MIN = Math.abs(diff1-diff2);
                }
            }
            return;
        }
        for(int i=start;i<=N;i++){
            list.add(i);
            int t = list.size();
            solution(location+1,i+1,depth);
            list.remove(t-1);
        }
    }

    public static boolean check(List<Integer> v){
        if(v.size()==1) return true;
        List<Integer> t = new ArrayList<>();
        for(int i : v) t.add(i);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(t.remove(0));
        while(!queue.isEmpty()){
            int value = queue.remove();
            List<Integer> index = new ArrayList<>();
            for(int i=0;i<t.size();i++){
                if(connect[value][t.get(i)]){
                    queue.add(t.get(i));
                    index.add(i);
                }
            }

            for(int i=0;i<index.size();i++){
                t.remove(index.get(i)-i);
            }

        }
        if(t.size()==0) return true;
        return false;
    }
}