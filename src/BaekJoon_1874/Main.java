package BaekJoon_1874;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception{
        Stack<Integer> stack = new Stack<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        StringBuilder s = new StringBuilder();
        StringBuilder s2 =new StringBuilder();
        StringBuilder sb = new StringBuilder();
        int i,index;

        for(i=0;i<n;i++){
            String str = br.readLine();
            list.add(Integer.parseInt(str));
            s = s.append(str);
        }
        i=1;
        index=0;

        while(i<=n){
            stack.push(i);
            sb.append("+"+"\n");

            if(stack.peek().intValue()==list.get(index).intValue()){
                s2=s2.append(stack.pop());
                sb.append("-"+"\n");
                index++;
                while(stack.size()>0&&stack.peek().intValue()==list.get(index).intValue()){
                    s2=s2.append(stack.pop());
                    index++;
                    sb.append("-"+"\n");
                    if(stack.size()==0) break;
                }
            }
            i++;
        }

        if(s.toString().equals(s2.toString())) System.out.print(sb);
        else System.out.print("NO");
    }
}