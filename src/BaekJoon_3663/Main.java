package BaekJoon_3663;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            System.out.println(solution(br.readLine()));
        }
    }
    public static int solution(String name) {
        int answer = 0;
        int min = name.length()-1;
        for(int i=0;i<name.length();i++) answer+=Math.min(Math.abs('A'-name.charAt(i)),Math.abs('Z'-name.charAt(i))+1);
        for(int i=0;i<name.length();i++){
            int index,left,right;
            index = i;
            while(index<name.length()){
                if(name.charAt(index)!='A') break;
                index++;
            }
            left = i==0?0:i-1;
            right=name.length()-index;
            min = Math.min(min,left+right+Math.min(left,right));
        }
        return answer+min;
    }
}