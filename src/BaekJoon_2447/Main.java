package BaekJoon_2447;

import java.util.Scanner;

public class Main {
    static char[][] str;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();
        str = new char[n][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                str[i][j]=' ';
            }
        }
        star(0,0,n);
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                sb.append(str[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);

    }
    public static void star(int x,int y,int n){
        if(n==1){
            str[x][y] = '*';
            return;
        }

        n=n/3;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(i==1&&j==1) continue;
                star(x+(i*n),y+(j*n),n);
            }
        }
    }

}