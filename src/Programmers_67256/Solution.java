package Programmers_67256;

import java.util.*;
class Solution {
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();

        int leftX = 3;
        int leftY = 0;
        int rightX = 3;
        int rightY = 2;

        for(int v : numbers){
            if(v==1 || v==4 || v==7){
                if(v==1){ leftX = 0; leftY=0;}
                else if(v==4){leftX=1; leftY=0;}
                else {leftX=2; leftY=0;}
                answer.append("L");
            }else if(v == 3 || v==6 || v==9){
                if(v==3){ rightX = 0; rightY=2;}
                else if(v==6){rightX=1; rightY=2;}
                else {rightX=2; rightY=2;}
                answer.append("R");
            }else{ // 가까운 거리의 손가락으로 이동시킴
                int v1 = dist(v,leftX,leftY);
                int v2 = dist(v,rightX,rightY);
                if(v1==v2){
                    if(hand.equals("left")){
                        if(v==2){
                            leftX = 0;
                            leftY = 1;
                        }else if(v==5){
                            leftX = 1;
                            leftY = 1;
                        }else if(v==8){
                            leftX = 2;
                            leftY = 1;
                        }else{
                            leftX = 3;
                            leftY = 1;
                        }
                        answer.append("L");
                    }else{
                        if(v==2){
                            rightX = 0;
                            rightY = 1;
                        }else if(v==5){
                            rightX = 1;
                            rightY = 1;
                        }else if(v==8){
                            rightX = 2;
                            rightY = 1;
                        }else{
                            rightX = 3;
                            rightY = 1;
                        }
                        answer.append("R");
                    }
                }else if(v1>v2){
                    if(v==2){
                        rightX = 0;
                        rightY = 1;
                    }else if(v==5){
                        rightX = 1;
                        rightY = 1;
                    }else if(v==8){
                        rightX = 2;
                        rightY = 1;
                    }else{
                        rightX = 3;
                        rightY = 1;
                    }
                    answer.append("R");
                }else if(v1<v2){
                    if(v==2){
                        leftX = 0;
                        leftY = 1;
                    }else if(v==5){
                        leftX = 1;
                        leftY = 1;
                    }else if(v==8){
                        leftX = 2;
                        leftY = 1;
                    }else{
                        leftX = 3;
                        leftY = 1;
                    }
                    answer.append("L");
                }
            }
        }
        return answer.toString();
    }

    public static int dist(int num,int startX,int startY){
        int destX,destY;
        if(num==2){
            destX = 0;
            destY = 1;
        }else if(num==5){
            destX = 1;
            destY = 1;
        }else if(num==8){
            destX = 2;
            destY = 1;
        }else{
            destX=3;
            destY=1;
        }

        int N = 4;
        int M = 3;

        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY,0));
        boolean[][] check = new boolean[N][M];
        check[startX][startY]=true;
        int cnt = 0;
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.x == destX && v.y==destY){
                cnt=v.time;
                break;
            }
            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x>=N || x<0 || y>=M || y<0 || check[x][y]) continue;
                check[x][y]=true;
                queue.add(new V(x,y,v.time+1));
            }
        }
        return cnt;
    }
}
class V{
    int x;
    int y;
    int time;
    public V(int x,int y,int time){
        this.x=x;
        this.y=y;
        this.time=time;
    }
}