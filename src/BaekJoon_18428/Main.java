package BaekJoon_18428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N;
    static char[][] array;
    static boolean[] check;
    static boolean chk=false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new char[N][N];
        check = new boolean[N*N+1];

        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++) array[i][j]=str[j].charAt(0);
        }
        solution(0,new ArrayList<>());
        System.out.println(chk?"YES":"NO");
    }

    public static void solution(int num, List<V> list){
        if(chk) return;

        if(num==3){
            char[][] temp = new char[N][N];
            for(int i=0;i<N;i++) for(int j=0;j<N;j++) temp[i][j]=array[i][j];
            for(V v:list) array[v.x][v.y]='O';
            if(check()) chk=true;
            array=temp;
            return;
        }

        int cnt = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(array[i][j]=='X'){
                    cnt++;

                    if(check[cnt]) continue;

                    check[cnt]=true;
                    list.add(new V(i,j));
                    int size = list.size();
                    solution(num+1,list);

                    check[cnt]=false;
                    list.remove(size-1);

                }
            }
        }
    }

    public static boolean check(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(array[i][j]=='T'){
                    boolean chk = lineCheck(i,j);
                    if(!chk) return false;
                }
            }
        }
        return true;
    }

    public static boolean lineCheck(int x,int y){
        for(int i=x;i>=0;i--){
            if(array[i][y]=='O') break;
            if(array[i][y]=='S') return false;
        }
        for(int i=x;i<N;i++){
            if(array[i][y]=='O') break;
            if(array[i][y]=='S') return false;
        }
        for(int j=y;j>=0;j--){
            if(array[x][j]=='O') break;
            if(array[x][j]=='S') return false;
        }
        for(int j=y;j<N;j++){
            if(array[x][j]=='O') break;
            if(array[x][j]=='S') return false;
        }

        return true;
    }

    public static void print(){
        System.out.println();
        System.out.println();
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
    }
}

class V{
    int x;
    int y;
    public V(int x,int y){
        this.x=x;
        this.y=y;
    }
}