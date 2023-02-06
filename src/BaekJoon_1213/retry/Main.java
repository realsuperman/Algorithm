package BaekJoon_1213.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int[] alphabet = new int[26];
        for(int i=0;i<str.length();i++) alphabet[str.charAt(i)-'A']++;

        char[] result = new char[str.length()];
        if(str.length()%2==0){ // 짝수
            for(int v : alphabet) if(v%2!=0){System.out.println("I'm Sorry Hansoo"); return;}

            for(int i=0;i<result.length/2;i++){
                int index;
                for(index=0;index<alphabet.length;index++){
                    if(alphabet[index]>0){
                        alphabet[index]-=2;
                        break;
                    }
                }

                index+=65;
                char ch = (char) index;
                result[i] = ch;
                result[result.length-1-i] = ch;
            }

        }else{ // 홀수
            int checkCount = 0;

            for(int i=0;i<26;i++){
                if(alphabet[i]%2!=0) {
                    checkCount++;
                }
            }

            if(checkCount>1) {System.out.println("I'm Sorry Hansoo"); return;}

            for(int i=0;i<result.length/2;i++){
                int index;
                for(index=0;index<alphabet.length;index++){
                    if(alphabet[index]>1){
                        alphabet[index]-=2;
                        break;
                    }
                }

                index+=65;
                char ch = (char) index;
                result[i] = ch;
                result[result.length-1-i] = ch;
            }

            for(int i=0;i<result.length;i++){
                if( !(result[i]>='A' && result[i]<='Z')){
                    for(int j=0;j<26;j++){
                        if(alphabet[j]>0) {
                            alphabet[i]--;
                            result[i]= (char) ('A'+j);
                        }
                    }
                }
            }
        }
        for(char ch : result) System.out.print(ch);
    }
}