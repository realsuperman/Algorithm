package BaekJoon_2138;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder from = new StringBuilder(br.readLine());
        StringBuilder to = new StringBuilder(br.readLine());
        if(from.toString().equals(to.toString())){System.out.println(0); return;}

        int result = 0,result2 = 0;
        result = simulation(from.toString(),to.toString(),0);

        if (from.charAt(0) == '0') {
            char ch = from.charAt(1);
            from.delete(0,2);

            if(ch=='0'){
                from.insert(0,"1");
            }else{
                from.insert(0,"0");
            }

            from.insert(0,"1");
        }else if(from.charAt(0)=='1'){
            char ch = from.charAt(1);
            from.delete(0,2);

            if(ch=='0'){
                from.insert(0,"1");
            }else{
                from.insert(0,"0");
            }

            from.insert(0,"0");
        }
        if(from.toString().equals(to.toString())){System.out.println(1); return;}
        result2 = simulation(from.toString(),to.toString(),1);

        if(result==0 && result2==0 ){System.out.println(-1); return;}
        if(result==0){ System.out.println(result2); return;}
        if(result2==0){ System.out.println(result); return;}

        if(result>result2){System.out.println(result2); return;}
        System.out.println(result);
    }
    public static int simulation(String from,String to,int cnt){
        int result = 0;
        boolean[] comp1 = new boolean[from.length()];
        boolean[] comp2 = new boolean[to.length()];
        for(int i=0;i<from.length();i++){
            if(from.charAt(i)=='0') comp1[i] = false;
            else comp1[i]=true;

            if(to.charAt(i)=='0') comp2[i] = false;
            else comp2[i]=true;
        }

        for(int i=0;i<from.length();i++){
            if(check(comp1,comp2)) return result+cnt;

            if(comp1[i]!=comp2[i]){
                result++;
                comp1[i] = !comp1[i];
                if(i+1<from.length()) comp1[i+1] = !comp1[i+1];
                if(i+2<from.length()) comp1[i+2] = !comp1[i+2];
            }
        }

        return 0;
    }

    public static boolean check(boolean[] from,boolean[] to){
        for(int i=0;i<from.length;i++){
            if(from[i]!=to[i]) return false;
        }
        return true;
    }
}