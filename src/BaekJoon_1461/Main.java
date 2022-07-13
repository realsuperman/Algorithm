package BaekJoon_1461;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);

        List<Integer> minus = new ArrayList<>();
        List<Integer> plus = new ArrayList<>();
        str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++){
            if(Integer.parseInt(str[i])<0){
                minus.add(Integer.valueOf(str[i]));
            }else{
                plus.add(Integer.valueOf(str[i]));
            }
        }
        Collections.sort(minus);
        Collections.sort(plus,Collections.reverseOrder());
/*        for(int i : minus) System.out.print(i+" ");
        System.out.println();
        for(int i : plus) System.out.print(i+" ");*/

        int result = 0;
        while(true){
            if(result==0){
                if(plus.size()>0 && minus.size()>0) {
                    if (plus.get(0) > Math.abs(minus.get(0))) { // +를 마지막 방문
                        result += plus.remove(0);
                        for(int i=1;i<M;i++){
                            if(plus.size()>0) plus.remove(0);
                        }
                    } else { // -를 마지막 방문
                        result += Math.abs(minus.remove(0));
                        for(int i=1;i<M;i++){
                            if(minus.size()>0) minus.remove(0);
                        }
                    }
                }else if(plus.size()>0){
                    result += plus.remove(0);
                    for(int i=1;i<M;i++){
                        if(plus.size()>0) plus.remove(0);
                    }
                }else if(minus.size()>0){
                    result += Math.abs(minus.remove(0));
                    for(int i=1;i<M;i++){
                        if(minus.size()>0) minus.remove(0);
                    }
                }
            }else{
                if(plus.size()>0){
                    int number = plus.remove(0);
                    result=result+(number*2);
                    for(int i=1;i<M;i++){
                        if(plus.size()>0) plus.remove(0);
                    }
                }

                if(minus.size()>0){
                    int number = Math.abs(minus.remove(0));
                    result=result+(number*2);
                    for(int i=1;i<M;i++){
                        if(minus.size()>0) minus.remove(0);
                    }
                }
            }
            if(plus.size()==0 && minus.size()==0) break;
        }
        System.out.println(result);
    }
}