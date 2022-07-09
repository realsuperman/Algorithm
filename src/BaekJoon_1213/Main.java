package BaekJoon_1213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] alphabet = new int[26];
        String str = br.readLine();
        for(int i=0;i<str.length();i++) alphabet[str.charAt(i)-65]++;

        int number = 0;
        for(int i=0;i<26;i++) if(alphabet[i]%2==1) number++;
        if( (str.length()%2==0 && number>=1) || (str.length()%2==1 && number>=2)){System.out.println("I'm Sorry Hansoo"); return;}

        char[] result = new char[str.length()];

        int index=0;
        if(str.length()%2==0) number = 0;
        else number=1;

        for(int i=0;i<26;){
            if(alphabet[i]>=2){
                char ch = (char)(i+65);
                result[index] = ch;
                result[str.length()-index-1] = ch;
                index++;
                alphabet[i]-=2;
            }else if(alphabet[i]<=number) i++;
        }

        if(str.length()%2==1){
            for(int i=0;i<26;i++){
                if(alphabet[i]>=1){
                    result[str.length()/2] = (char)(i+65);
                    break;
                }
            }
        }
        for(int i=0;i<result.length;i++) System.out.print(result[i]);
    }
}