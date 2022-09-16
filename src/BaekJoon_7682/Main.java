package BaekJoon_7682;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[][] array = new char[3][3];
    static boolean chk;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String str = br.readLine();
            if(str.equals("end")) break;
            char[][] temp = new char[3][3];
            for(int i=0;i<3;i++) for(int j=0;j<3;j++) array[i][j]='.';
            chk=false;

            String[] v = str.split("");
            temp[0][0]=v[0].charAt(0); temp[0][1]=v[1].charAt(0); temp[0][2]=v[2].charAt(0);
            temp[1][0]=v[3].charAt(0); temp[1][1]=v[4].charAt(0); temp[1][2]=v[5].charAt(0);
            temp[2][0]=v[6].charAt(0); temp[2][1]=v[7].charAt(0); temp[2][2]=v[8].charAt(0);

            solution('X',temp);
            if(!chk) sb.append("invalid"+"\n");
        }
        System.out.println(sb);
    }

    public static void solution(char ch,char[][] temp){
        if(chk) return;

        if(checkDirection()){
            if(shapeCheck(temp)){
                chk=true;
                sb.append("valid"+"\n");
            }
            return;
        }

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(array[i][j]=='.'){
                    array[i][j]=ch;
                    char target = ch=='X'?'O':'X';
                    solution(target,temp);
                    array[i][j]='.';
                }
            }
        }

    }

    public static boolean shapeCheck(char[][] target) {
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(target[i][j]!=array[i][j]) return false;
            }
        }
        return true;
    }

    public static boolean checkDirection(){
        boolean chk = true;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(array[i][j]=='.'){ chk=false; break;}
            }
        }
        if(chk) return true;

        for(int i=0;i<3;i++){
            if(array[i][0]!='.'&&array[i][0]==array[i][1] && array[i][1]==array[i][2]) return true;
            if(array[0][i]!='.'&&array[0][i]==array[1][i] && array[1][i]==array[2][i]) return true;
        }
        if(array[0][0]!='.'&&array[0][0]==array[1][1] && array[1][1]==array[2][2]) return true;
        if(array[0][2]!='.'&&array[0][2]==array[1][1] && array[1][1]==array[2][0]) return true;

        return false;
    }
}