package BaekJoon_2800;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class Main {
    public static Set<String> set = new TreeSet<>();
    public static boolean[] sw;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int N = 0;
        for(int i=0;i<str.length();i++) if(str.charAt(i) == '(') N++;

        int[] open = new int[N];
        int[] close = new int[N];
        sw = new boolean[N];

        int index1 = 0,index2=0;
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='('){
                stack.push(i);
            }
            else if(str.charAt(i)==')'){
                open[index1++] = stack.pop();
                close[index2++] = i;
            }
        }
        solution(str,open,close,0,N);
        for(String s : set) System.out.println(s);
    }

    public static void solution(String str,int[] open,int[] close,int start,int N){
        if (start == N) {
            boolean chk = false;
            for(int i=0;i<sw.length;i++) if(sw[i]==true) chk = true;
            if(!chk) return;

            StringBuilder sb = new StringBuilder();
            int[] array = new int[open.length+close.length];
            for(int i=0;i<array.length;i++) array[i]=-1;
            int index=0;
            for (int i = 0; i < sw.length; i++) {
                if(sw[i]){
                    array[index++] = open[i];
                    array[index++] = close[i];
                }
            }
            for(int i=0;i<str.length();i++){
                int sw = 0;
                for(int j=0;j<array.length;j++){
                    if(array[j]==i) {sw=1; break;}
                }
                if(sw==0) sb.append(str.charAt(i));
            }
            set.add(sb.toString());
            return;
        }

        sw[start] = false;
        solution(str,open,close,start+1,N);
        sw[start] = true;
        solution(str,open,close,start+1,N);
    }
}