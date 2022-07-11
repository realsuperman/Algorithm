package BaekJoon_16637;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,cnt;
    static String str = "";
    static boolean[] check;
    static int MAX;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        str = br.readLine();

        cnt = str.length()/2;
        check = new boolean[cnt];

        if(N==1){
            System.out.println(str);
            return;
        }
        Queue<String> init = new LinkedList<>();
        for(int i=0;i<str.length();i++) init.add(String.valueOf(str.charAt(i)));
        MAX = cal(init);
        for(int i=1;i<=cnt/2;i++){
            List<Integer> list = new ArrayList<>();
            solution(0,0,i,list);
        }

        System.out.println(MAX);
    }

    public static void solution(int start,int index,int size,List<Integer> list){
        if(start==size){
            Stack<String> stack = new Stack<>();
            Queue<String> queue = new LinkedList<>();

            int i=0;
            int op = -1;
            for(int value : list){
                for(;i<str.length();i++){
                    if(str.charAt(i)=='+' || str.charAt(i)=='-' || str.charAt(i)=='*') op++;

                    if(op==value){
                        if(str.charAt(i)=='+'){
                            i++;
                            int a = (str.charAt(i)-48)+Integer.parseInt(stack.pop());
                            stack.push(String.valueOf(a));
                        }else if(str.charAt(i)=='-'){
                            i++;
                            int a = Integer.parseInt(stack.pop())-(str.charAt(i)-48);
                            stack.push(String.valueOf(a));
                        }else if(str.charAt(i)=='*'){
                            i++;
                            int a = (str.charAt(i)-48)*Integer.parseInt(stack.pop());
                            stack.push(String.valueOf(a));
                        }
                        i++;
                        break;
                    }else{
                        stack.add(String.valueOf(str.charAt(i)));
                    }
                }
            }
            for(;i<str.length();i++) stack.push(String.valueOf(str.charAt(i)));
            ArrayDeque<String> temp = new ArrayDeque<>();
            while(!stack.isEmpty()) temp.add(stack.pop());
            while(!temp.isEmpty()) queue.add(temp.pollLast());
            int result = cal(queue);
            MAX = MAX<result?result:MAX;
            return;
        }
        for(int i=index;i<cnt;i++){
            if(check[i] || (i>0 && check[i-1])) continue;
            check[i]=true;
            list.add(i);
            int number = list.size();
            solution(start+1,i,size,list);
            list.remove(number-1);
            check[i]=false;
        }
    }

    public static int cal(Queue<String> queue){
        int sum = 0;
        int number = 0;
        boolean chk = false;

        while(!queue.isEmpty()){
            String str = queue.remove();
            if(str.equals("+")){
                if(chk){
                    sum=sum+Integer.parseInt(queue.remove());
                }else {
                    number = number + Integer.parseInt(queue.remove());
                    sum += number;
                    chk = true;
                }
            }else if(str.equals("-")){
                if(chk){
                    sum=sum-Integer.parseInt(queue.remove());
                }else {
                    number = number - Integer.parseInt(queue.remove());
                    sum += number;
                    chk = true;
                }
            }else if(str.equals("*")){
                if(chk){
                    sum = sum*Integer.parseInt(queue.remove());
                }else {
                    number = number*Integer.parseInt(queue.remove());
                    sum += number;
                    chk = true;
                }
            }else{
                number = Integer.parseInt(str);
            }
        }
        return sum;
    }
}