package BaekJoon_17825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int[] array = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
    static int result = Integer.MIN_VALUE;
    static List<Integer> value = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        for(String s : str) value.add(Integer.valueOf(s));
        dfs(0);
        System.out.println(result);
    }

    public static void dfs(int depth){
        if(depth==10){
            simulation();
            return;
        }

        for(int i=0;i<4;i++){
            array[depth] = i;
            dfs(depth+1);
            array[depth] = -1;
        }
    }

    private static void simulation() {
        boolean[][] check = new boolean[4][41];

        boolean[] finishedNodes = new boolean[4];
        int sum = 0;

        int[] load1 = new int[22];
        for(int i=1;i<22;i++) load1[i]=2*i;
        int[] load2 = new int[9];
        load2[0]=10; load2[1] = 13; load2[2]=16; load2[3]=19; load2[4] = 25; load2[5] = 30; load2[6]=35; load2[7]=40;
        int[] load3 = new int[8];
        load3[0]=20; load3[1] = 22; load3[2]=24; load3[3]=25; load3[4] = 30; load3[5] = 35; load3[6]=40;
        int[] load4 = new int[9];
        load4[0]=30; load4[1] = 28; load4[2]=27; load4[3]=26; load4[4] = 25; load4[5] = 30; load4[6]=35; load4[7]=40;

        V[] locations = new V[4];
        for(int i=0;i<4;i++) locations[i] = new V(0,0,load1);

        for(int i=0;i<10;i++){
            if(finishedNodes[array[i]]) return; // 끝에 도착한 노드를 움직이는 경우 잘못된 경우이므로 return한다

            V v = locations[array[i]]; // 움직일 타겟 뽑기
            int plus = value.get(i); // 어느만큼 가야하는가

            if(v.value[v.index]==10){
                check[0][10]=false;
            }else if(v.value[v.index]==20){
                check[0][20]=false;
            }else if(v.value[v.index]==25) {
                for(int j=0;j<4;j++) check[j][25]=false;
            }else if(v.value[v.index]==30){
                if(check[0][30]) check[0][30]=false;
                else for(int j=1;j<4;j++) check[j][30]=false;
            }else if(v.value[v.index]==35) {
                for (int j=0;j<4;j++) check[j][35] = false;
            }else if(v.value[v.index]==40){
                for(int j=0;j<4;j++) check[j][40]=false;
            }else{
                check[v.sector][v.value[v.index]]=false;
            }

            if(plus+v.index >= v.value.length-1){ finishedNodes[array[i]]=true; continue;} // 배열의 범위 이상을 뛰는 경우 도착지점에 간거임

            int value = v.value[plus+v.index]; // 얻게 될 점수

            sum+=value;
            v.index = plus+v.index;

            if(value==10){
                if(check[0][10]) return;
                check[0][10]=true;
                v.sector=1;
                v.index=0;
                v.value=load2;
            }else if(value==20){
                if(check[0][20]) return;
                check[0][20]=true;
                v.sector=2;
                v.index=0;
                v.value=load3;
            }else if(value==30){
                if(v.sector==0){
                    if(check[0][30]) return;
                    check[0][30]=true;
                    v.sector=3;
                    v.value=load4;
                    v.index=0;
                }else{
                    for(int j=1;j<4;j++) if(check[j][30]) return;
                    for(int j=1;j<4;j++) check[j][30]=true;
                }
            }else if(value==35){
                for(int j=0;j<4;j++) if(check[j][35]) return;
                for(int j=0;j<4;j++) check[j][35]=true;
            }else if(value==40){
                for(int j=0;j<4;j++) if(check[j][40]) return;
                for(int j=0;j<4;j++) check[j][40]=true;
            }else{
                if(check[v.sector][value]) return;
                check[v.sector][value]=true;
            }
        }
        result = result<sum?sum:result;
    }
}

class V{
    int sector;
    int index;
    int[] value;
    public V(int sector,int index,int[] value){
        this.sector=sector;
        this.index=index;
        this.value=value;
    }
}