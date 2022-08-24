package Programmers_12947;

class Solution {
    public boolean solution(int x) {
        String str = String.valueOf(x);
        int sum = 0;
        for(int i=0;i<str.length();i++) sum+=str.charAt(i)-48;
        if(x%sum==0) return true;
        return false;
    }
}