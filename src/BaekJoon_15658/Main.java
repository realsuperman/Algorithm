package BaekJoon_15658;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static int N,MAX = Integer.MIN_VALUE,MIN=Integer.MAX_VALUE;
    static List<Integer> list;
    static int[] op;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        String[] str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) list.add(Integer.valueOf(str[i]));

        op = new int[4];
        str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) op[i]=Integer.valueOf(str[i]);
        solution(0,new ArrayList<>());
        System.out.println(MAX);
        System.out.println(MIN);
    }

    public static void solution(int num,List<Character> charList){
        if(num==list.size()-1){
            List<String> result = new ArrayList<>();
            result.add(String.valueOf(list.get(0)));
            for(int i=0;i<charList.size();i++){
                result.add(String.valueOf(charList.get(i)));
                result.add(String.valueOf(list.get(i+1)));
            }
            int v = cal(result);
            MAX = MAX<v?v:MAX;
            MIN = MIN>v?v:MIN;
            return;
        }
        for(int i=0;i<4;i++){
            if(op[i]>0){
                op[i]--;

                if(i==0){
                    charList.add('+');
                }else if(i==1){
                    charList.add('-');
                }else if(i==2){
                    charList.add('*');
                }else{
                    charList.add('/');
                }
                int size = charList.size();
                solution(num+1,charList);

                op[i]++;
                charList.remove(size-1);
            }
        }
    }

    public static int cal(List<String> result) {
        Queue<Integer> queue = new LinkedList<>();

        for(int i=0;i<result.size();i++){
            if(result.get(i).equals("+")){
                queue.add(queue.remove()+Integer.parseInt(result.get(i+1)));
                i++;
            }else if(result.get(i).equals("-")){
                queue.add(queue.remove()-Integer.parseInt(result.get(i+1)));
                i++;
            }else if(result.get(i).equals("*")){
                queue.add(queue.remove()*Integer.parseInt(result.get(i+1)));
                i++;
            }else if(result.get(i).equals("/")){
                queue.add(queue.remove()/Integer.parseInt(result.get(i+1)));
                i++;
            }else{
                queue.add(Integer.parseInt(result.get(i)));
            }
        }
        return queue.peek();
    }
}