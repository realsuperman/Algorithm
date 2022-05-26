package BaekJoon_20056;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static int N,M,K;
    public static V[][] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int solution = 0;
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        K = Integer.parseInt(str[2]);
        array = new V[N+1][N+1];
        for(int i=1;i<=N;i++) for(int j=1;j<=N;j++) array[i][j]=new V();
        for(int i=0;i<M;i++){
            str = br.readLine().split(" ");
            array[Integer.parseInt(str[0])][Integer.parseInt(str[1])].m.add(Integer.parseInt(str[2]));
            array[Integer.parseInt(str[0])][Integer.parseInt(str[1])].s.add(Integer.parseInt(str[3]));
            array[Integer.parseInt(str[0])][Integer.parseInt(str[1])].d.add(Integer.parseInt(str[4]));
        }

        for(int i=0;i<K;i++){
            for(int j=1;j<=N;j++){ // 파이어볼 이동
                for(int k=1;k<=N;k++){
                    rotate(j,k);
                }
            }

            for(int j=1;j<=N;j++){
                for(int k=1;k<=N;k++){
                    if(array[j][k].m2.size()>1){ // 파이어볼 합쳐짐
                        int mSum=0;
                        int sSum=0;
                        int[] dire = new int[2]; // 0이면 홀수, 1이면 짝수

                        for(int num : array[j][k].m2) mSum+=num;
                        for(int num : array[j][k].s2) sSum+=num;
                        for(int num : array[j][k].d2){
                            if(num%2==0){
                                dire[1]+=1;
                            }else{
                                dire[0]+=1;
                            }
                        }
                        mSum=mSum/5;
                        sSum=sSum/array[j][k].m2.size();
                        if(dire[0]==array[j][k].m2.size() || dire[1]==array[j][k].m2.size()){
                            dire = new int[4];
                            dire[0] = 0;
                            dire[1] = 2;
                            dire[2] = 4;
                            dire[3] = 6;
                        }else{
                            dire = new int[4];
                            dire[0] = 1;
                            dire[1] = 3;
                            dire[2] = 5;
                            dire[3] = 7;
                        }
                        while(array[j][k].m2.size()>0){
                            array[j][k].m2.remove(0);
                            array[j][k].s2.remove(0);
                            array[j][k].d2.remove(0);
                        }
                        if(mSum>0){
                            array[j][k].m.add(mSum);
                            array[j][k].m.add(mSum);
                            array[j][k].m.add(mSum);
                            array[j][k].m.add(mSum);

                            array[j][k].s.add(sSum);
                            array[j][k].s.add(sSum);
                            array[j][k].s.add(sSum);
                            array[j][k].s.add(sSum);

                            array[j][k].d.add(dire[0]);
                            array[j][k].d.add(dire[1]);
                            array[j][k].d.add(dire[2]);
                            array[j][k].d.add(dire[3]);
                        }
                    }else if(array[j][k].m2.size()==1){
                        array[j][k].m.add(array[j][k].m2.remove(0));
                        array[j][k].s.add(array[j][k].s2.remove(0));
                        array[j][k].d.add(array[j][k].d2.remove(0));
                    }
                }
            }

        }

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                for(Integer v : array[i][j].m){
                    solution+=v;
                }
            }
        }
        System.out.println(solution);
    }

    public static void rotate(int r,int c){
        while(array[r][c].m.size()>0){
            int direction = array[r][c].d.remove(0);
            int speed = array[r][c].s.remove(0);
            int weight = array[r][c].m.remove(0);
            int[] coor = coordinate(r,c,direction,speed);
            array[coor[0]][coor[1]].m2.add(weight);
            array[coor[0]][coor[1]].s2.add(speed);
            array[coor[0]][coor[1]].d2.add(direction);
        }
    }

    public static int[] coordinate(int x,int y,int direction,int speed){
        int[] array = new int[2];
        int i = 0;
        while(i<speed) {
            switch (direction) {
                case 0:
                        x = x-1;
                        if(x==0) x = N;
                        break;
                case 1:
                        x = x-1;
                        y = y+1;
                        if(x==0) x = N;
                        if(y>N) y = 1;
                        break;
                case 2:
                        y = y+1;
                        if(y>N) y = 1;
                        break;
                case 3:
                        x = x+1;
                        y = y+1;
                        if(x>N) x = 1;
                        if(y>N) y = 1;
                        break;
                case 4:
                        x = x+1;
                        if(x>N) x = 1;
                        break;
                case 5:
                        x = x+1;
                        y = y-1;
                        if(x>N) x = 1;
                        if(y==0) y = N;
                        break;
                case 6:
                        y = y-1;
                        if(y==0) y = N;
                        break;
                default:
                        x = x-1;
                        y = y-1;
                        if(x==0) x = N;
                        if(y==0) y = N;
            }
            i++;
        }
        array[0] = x;
        array[1] = y;
        return array;
    }
}

class V{
    List<Integer> m = new ArrayList<>(); // 질량
    List<Integer> s = new ArrayList<>(); // 속도
    List<Integer> d = new ArrayList<>(); // 방향

    List<Integer> m2 = new ArrayList<>();
    List<Integer> s2 = new ArrayList<>();
    List<Integer> d2 = new ArrayList<>();
}