package Programmers_2;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        String[] str = {"banana", "apple", "rice", "pork", "pot"};
        int[] array = {3,2,2,2,1};
        String[] str2 = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};
        System.out.println(solution(str,array,str2));

        String[] temp = {"apple"};
        int[] age = {10};
        String[] temp2= {"banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana"};
        System.out.println(solution(temp,age,temp2));
    }

    public static int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        Map<String,Integer> map = new HashMap<>();

        int sum = 0;
        for(int i=0;i<number.length;i++) sum+=number[i];

        for(int j=0;j<discount.length;j++){
            if(j>=sum) break;

            if(map.get(discount[j])==null) map.put(discount[j],1);
            else map.put(discount[j],map.get(discount[j])+1);
        }
        if(checkLogic(want,number,map)) answer++;

        int cnt = 0;
        for(int i=sum;i<discount.length;i++){
            map.put(discount[cnt],map.get(discount[cnt])-1);
            if(map.get(discount[i])==null) map.put(discount[i],1);
            else map.put(discount[i],map.get(discount[i])+1);
            if(checkLogic(want,number,map)) answer++;

            cnt++;
        }


        return answer;
    }

    public static boolean checkLogic(String[] want, int[] number,Map<String,Integer> map){
        for(int i=0;i<want.length;i++){
            if(map.get(want[i])==null || map.get(want[i])<number[i]) return false;
        }

        return true;
    }

    public static void print(Map<String,Integer> map){
        System.out.println();
        for(String str : map.keySet()){
            System.out.print(str+" "+map.get(str)+"|| ");
        }
        System.out.println();
    }
}