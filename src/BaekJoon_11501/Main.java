package BaekJoon_11501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            int N = Integer.parseInt(br.readLine());
            List<Integer> list = new ArrayList<>();
            String[] str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                list.add(Integer.valueOf(str[j]));
            }
            sb.append(solution(list)+"\n");
        }
        System.out.println(sb);
    }

    public static BigInteger solution(List<Integer> list){
        BigInteger bigNumber = new BigInteger("0");

        long value = list.get(list.size()-1);
        long cnt = 0;
        long sum = 0;

        for(int i=list.size()-2;i>=0;i--){
            if(value>list.get(i)){
                cnt++;
                sum+=list.get(i);

                if(i==0){
                    long v = (value*cnt)-sum;
                    bigNumber = bigNumber.add(BigInteger.valueOf(v));
                    value = list.get(i);
                }
            }else{
                long v = (value*cnt)-sum;
                bigNumber = bigNumber.add(BigInteger.valueOf(v));

                cnt = 0;
                sum = 0;
                value = list.get(i);
            }
        }

        return bigNumber;
    }
}