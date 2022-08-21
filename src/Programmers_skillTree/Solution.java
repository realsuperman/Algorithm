package Programmers_skillTree;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for(String str : skill_trees){
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<str.length();i++){
                String t = str.charAt(i)+"";
                if(skill.contains(t)){
                    sb.append(str.charAt(i));
                }
            }

            boolean chk = false;
            for(int i=0;i<sb.toString().length();i++){
                if(sb.charAt(i)!=skill.charAt(i)) {chk=true; break;}
            }
            if(!chk) answer++;
        }

        return answer;
    }
}