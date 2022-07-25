package BaekJoon_1254;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        if(check(str)){ System.out.println(str.length()); return;}

        int max = str.length();
        int result = 0;
        for(int i=1;i<str.length();i++){
            String temp = "";
            for(int j=i;j<str.length();j++) temp+=str.charAt(j);
            if(check(temp)){ // 펠린드롬
                result = temp.length();
                break;
            }
        }
        max -= result;
        System.out.println(str.length()+max);
    }

    public static boolean check(String str){
        String comp1 = "";
        String comp2 = "";

        for(int i=0;i<str.length()/2;i++) comp1+=str.charAt(i);
        if(str.length()%2==0){
            for(int i=str.length()-1;i>=str.length()/2;i--) comp2+=str.charAt(i);
        }else{
            for(int i=str.length()-1;i>str.length()/2;i--) comp2+=str.charAt(i);
        }
        if(comp1.equals(comp2)) return true;

        return false;
    }
}