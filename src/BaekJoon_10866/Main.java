package BaekJoon_10866;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int i=0;
        Deque<Integer> deque = new LinkedList<>();
        String[] str;
        StringBuilder sb = new StringBuilder();

        while(i<n){
            str = br.readLine().split(" ",2);
            if(str[0].equals("push_front")){
                deque.offerFirst(Integer.valueOf(str[1]));
            }else if(str[0].equals("push_back")){
                deque.offerLast(Integer.valueOf(str[1]));
            }else if(str[0].equals("pop_front")){
                try{
                    sb.append(deque.removeFirst()+"\n");
                }catch (Exception e){
                    sb.append("-1"+"\n");
                }
            }else if(str[0].equals("pop_back")){
                try{
                    sb.append(deque.removeLast()+"\n");
                }catch (Exception e){
                    sb.append("-1"+"\n");
                }
            }else if(str[0].equals("size")){
                sb.append(deque.size()+"\n");
            }else if(str[0].equals("empty")){
                if(deque.isEmpty()){
                    sb.append("1"+"\n");
                }else{
                    sb.append("0"+"\n");
                }
            }else if(str[0].equals("front")){
                try{
                    sb.append(deque.getFirst()+"\n");
                }catch(Exception e){
                    sb.append("-1"+"\n");
                }
            }else if(str[0].equals("back")){
                try{
                    sb.append(deque.getLast()+"\n");
                }catch(Exception e){
                    sb.append("-1"+"\n");
                }
            }
            i++;
        }
        System.out.print(sb);
    }
}