package Programmers_creatingJadenCaseStrings;

class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        String str = "";
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==' '){
                method(sb,str);
                str="";
            }else{
                str+=s.charAt(i);
            }
        }
        method(sb,str);
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public void method(StringBuilder sb,String value){
        if(value.length()==0){sb.append(" "); return;}
        if(value.charAt(0)>='0' && value.charAt(0)<='9'){
            sb.append(value.charAt(0));
            for(int i=1;i<value.length();i++){
                if(value.charAt(i)>=65 && value.charAt(i)<=90){
                    sb.append((char)((int)value.charAt(i)+32));
                }else{
                    sb.append(value.charAt(i));
                }
            }
        }else{
            if(value.charAt(0)>=97 && value.charAt(0)<=122){
                sb.append((char)((int)value.charAt(0)-32));
            }else{
                sb.append(value.charAt(0));
            }

            for(int i=1;i<value.length();i++){
                if(value.charAt(i)>=65 && value.charAt(i)<=90){
                    sb.append((char)((int)value.charAt(i)+32));
                }else{
                    sb.append(value.charAt(i));
                }
            }
        }
        sb.append(" ");
    }
}

