package BaekJoon_17413.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        boolean openTag = false;
        StringBuilder result = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if(ch=='<'){
                openTag = true;
                result.append(temp.reverse());
                temp = new StringBuilder();
                temp.append('<');
            }else if(ch=='>'){
                openTag = false;
                temp.append('>');
                result.append(temp);
                temp = new StringBuilder();
            }else if(ch==' '){
                if(openTag){
                    temp.append(ch);
                }else{
                    result.append(temp.reverse()+" ");
                    temp = new StringBuilder();
                }
            }
            else{
                temp.append(ch);
            }
        }
        result.append(temp.reverse());
        System.out.println(result);
    }
}