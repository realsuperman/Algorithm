package BaekJoon_14888;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    public static int max = -1000000001;
    public static int min = 1000000001;
    public static List<String> list = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];
        String[] str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) array[i] = Integer.parseInt(str[i]);
        str = br.readLine().split(" ");
        int[] op = new int[4];
        for(int i=0;i<str.length;i++) op[i] = Integer.parseInt(str[i]); // +,-,*,/
        solution(array,op,0,N-1);
        System.out.println(max);
        System.out.println(min);
    }
    public static void solution(int[] array,int[] op,int start,int N){
        if(start==N){
            list.add(Integer.toString(array[start]));
            cal();
            list.remove(list.size()-1);
            return;
        }
        for(int i=0;i<op.length;i++){
            if(op[i]==0) continue;
            op[i] = op[i]-1;
            list.add(Integer.toString(array[start]));
            switch(i){
                case 0 : list.add("+"); break;
                case 1 : list.add("-"); break;
                case 2 : list.add("*"); break;
                default : list.add("/"); break;
            }
            int index1 = list.size()-1;
            int index2 = list.size()-2;
            solution(array,op,start+1,N);
            list.remove(index1);
            list.remove(index2);
            op[i] = op[i]+1;
        }
    }

    public static void cal(){
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.parseInt(list.get(0)));
        for(int i=1;i<list.size();i=i+2){
            if(list.get(i).equals("+")){
                stack.push(stack.pop()+Integer.parseInt(list.get(i+1)));
            }else if(list.get(i).equals("-")){
                stack.push(stack.pop()-Integer.parseInt(list.get(i+1)));
            }else if(list.get(i).equals("*")){
                stack.push(stack.pop()*Integer.parseInt(list.get(i+1)));
            }else if(list.get(i).equals("/")){
                stack.push(stack.pop()/Integer.parseInt(list.get(i+1)));
            }
        }
        if(max<stack.peek()) max = stack.peek();
        if(min>stack.peek()) min = stack.peek();
    }
}
