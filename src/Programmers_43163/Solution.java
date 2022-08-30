package Programmers_43163;

class Solution {
    Boolean[] value;
    int min = 2147483647;
    public int solution(String begin, String target, String[] words) {
        int v = 0;
        value = new Boolean[words.length];
        for(int i=0;i<words.length;i++) value[i]=false;
        for(String s : words) if(s.equals(target)) v=1;
        if(v==0) return 0;
        dfs(begin,target,words,0);
        return min;
    }

    public int dfs(String begin, String target, String[] words,int count){
        if(begin.equals(target)){
            if(min>count) min=count;
        }
        for(int i=0;i<words.length;i++){
            if(value[i]) continue;
            int cnt = 0;
            for(int j=0;j<begin.length();j++) if(begin.charAt(j) == words[i].charAt(j)) cnt++;
            if(cnt==begin.length()-1){value[i]=true; count++; dfs(words[i],target,words,count); count--; value[i]=false;}
        }
        return 0;
    }

}