package Programmers_vowelDictionary;

import java.util.*;

class Solution {
    char[] words = {'A','E','I','O','U'};

    public int solution(String word) {
        int answer = 0;
        List<String> list = new ArrayList<>();

        for(int i=0;i<5;i++){
            StringBuilder sb1 = new StringBuilder();
            sb1.append(words[i]);
            list.add(sb1.toString());
            for(int j=0;j<5;j++){
                StringBuilder sb2 = new StringBuilder(sb1);
                sb2.append(words[j]);
                list.add(sb2.toString());
                for(int k=0;k<5;k++){
                    StringBuilder sb3 = new StringBuilder(sb2);
                    sb3.append(words[k]);
                    list.add(sb3.toString());
                    for(int p=0;p<5;p++){
                        StringBuilder sb4 = new StringBuilder(sb3);
                        sb4.append(words[p]);
                        list.add(sb4.toString());
                        for(int q=0;q<5;q++){
                            StringBuilder sb5 = new StringBuilder(sb4);
                            sb5.append(words[q]);
                            list.add(sb5.toString());
                        }
                    }
                }
            }
        }

        for(String str : list){
            if(word.equals(str)) break;
            answer++;
        }
        return answer+1;
    }
}
