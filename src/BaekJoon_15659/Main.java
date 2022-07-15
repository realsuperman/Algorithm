package BaekJoon_15659;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    static int N,MIN = Integer.MAX_VALUE,MAX=Integer.MIN_VALUE;
    static List<Integer> list = new ArrayList<>();
    static int[] opCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) list.add(Integer.valueOf(str[i]));
        str = br.readLine().split(" ");
        opCnt = new int[str.length];
        for(int i=0;i<str.length;i++) opCnt[i]= Integer.parseInt(str[i]);

        List<Character> list = new ArrayList<>();
        solution(0,list);
        System.out.println(MAX);
        System.out.println(MIN);
    }

    public static void solution(int num,List<Character> op){
        if(num==N-1){
            int su = cal(list,op);
            if(su > MAX) MAX = su;
            if(su < MIN ) MIN = su;
            return;
        }
        for(int i=0;i<4;i++){
            if(opCnt[i]>=1){
                opCnt[i]--;
                if(i==0) op.add('+');
                else if(i==1) op.add('-');
                else if(i==2) op.add('*');
                else op.add('/');
                int size = op.size();
                solution(num+1,op);
                opCnt[i]++;
                op.remove(size-1);
            }
        }
    }

    public static int cal(List<Integer> numbers,List<Character> oprands){
        Stack<String> stack = new Stack<>();
        List<String> list = new ArrayList<>();


        for(int i=0;i<numbers.size();i++){
            list.add(String.valueOf(numbers.get(i)));
            if(i<oprands.size()) list.add(String.valueOf(oprands.get(i)));
        }

        for(int i=0;i<list.size();i++){
            if(list.get(i).equals("*")) {
                int number = Integer.parseInt(stack.pop())*Integer.parseInt(list.get(++i));
                stack.push(String.valueOf(number));
            }else if(list.get(i).equals("/")){
                int number = Integer.parseInt(stack.pop())/Integer.parseInt(list.get(++i));
                stack.push(String.valueOf(number));
            }else{
                stack.push(list.get(i));
            }
        }

        List<String> temp = new ArrayList<>();
        while(!stack.isEmpty()) temp.add(stack.pop());
        List<String> calList = new ArrayList<>();
        for(int i=temp.size()-1;i>=0;i--) calList.add(temp.get(i));

        int sum = 0;
        for(int i=0;i<calList.size();i++){
            if(calList.get(i).equals("+")){
                sum = sum + Integer.parseInt(calList.get(++i));
            }else if(calList.get(i).equals("-")){
                sum = sum - Integer.parseInt(calList.get(++i));
            }else{
                sum+=Integer.parseInt(calList.get(i));
            }
        }

        return sum;
    }
}