package BaekJoon_17825.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int MAX = Integer.MIN_VALUE;
    static List<Integer> diceNumber = new ArrayList<>();
    static V[] locations; // 말들의 위치

    // 점프하는 타겟은 본인의 jump 배열 여부를 선정할 수 있어야함
    static int[] jump1 = {0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40}; // 10,20,30에서 끝나는 경우 jump2, jump3, jump4를 참조함
    static int[] jump2 = {10,13,16,19,25,30,35,40};
    static int[] jump3 = {20,22,24,25,30,35,40};
    static int[] jump4 = {30,28,27,26,25,30,35,40};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        for(String s : str) diceNumber.add(Integer.parseInt(s));

        dfs(0,new LinkedList<>());
        System.out.println(MAX);
    }

    public static void dfs(int depth,List<Integer> list){
        if(depth==10){
            locations = new V[4];
            for(int i=0;i<4;i++) locations[i] = new V(0,jump1); // 점프1을 기준으로 초기값 세팅
            int sum = 0;

            int index = 0;
            for(int v : list){
                V target = locations[v];
                if(target.index==-1) return; // 도착칸에 있는 말을 선택하는 경우

                int value = target.index + diceNumber.get(index++); // 이동할 인덱스
                if(value>=target.jump.length){ target.index=-1; continue;}
                target.index=value; // 인덱스 변경
                sum+=target.jump[target.index]; // 점수 획득

                if(target.jump == jump1){
                    if(target.jump[target.index]==10){
                        setIndexAndJump(target, jump2);
                    }else if(target.jump[target.index]==20){
                        setIndexAndJump(target, jump3);
                    }else if(target.jump[target.index]==30){
                        setIndexAndJump(target, jump4);
                    }
                }

                if(checkExistsCondition(target,v)) return;

            }

            MAX = MAX<sum?sum:MAX;
            return;
        }

        for(int i=0;i<4;i++){
            list.add(i);
            int size = list.size()-1;
            dfs(depth+1, list);
            list.remove(size);
        }
    }

    private static boolean checkExistsCondition(V v, int exclusionIndex) {
        for(int i=0;i<4;i++){
            if(i==exclusionIndex || locations[i].index==-1) continue;
            if(v.jump[v.index] == locations[i].jump[locations[i].index]){ // 값이 같음
                if(v.jump == locations[i].jump || v.jump[v.index]==25 || v.jump[v.index]== 35 || v.jump[v.index]==40){
                    return true;
                }
                else if(v.jump[v.index]==30 && ( (v.index==0 && locations[i].index==0) || (v.index!=0 && locations[i].index!=0) ) ){
                    return true;
                }
            }
        }
        return false;
    }

    private static void setIndexAndJump(V target, int[] jump){
        target.index=0;
        target.jump = jump;
    }
}

class V{
    int index;
    int[] jump;
    public V(int index,int[] jump){
        this.index=index;
        this.jump=jump;
    }
}