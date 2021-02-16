package BaekJoon_1062;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static int max;
    public static boolean[] check;
    public static String[] str;
    public static int value = 0;
    public static Set<Character> list;
    public static ArrayList<Character> list2 = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine().split(" ",2);
        list = new HashSet<>();

        int n = Integer.parseInt(str[0]);
        max = Integer.parseInt(str[1]);
        int cnt = 0;
        boolean sw;

        str = new String[n];
        for(int i=0;i<n;i++){
            Set<Character> set = new HashSet<>();
            StringBuilder sb = new StringBuilder();
            String s = br.readLine();
            s = s.replaceAll("anta","").replaceAll("tica","");
            for(int j=0;j<s.length();j++){
                if(s.charAt(j)!='a' && s.charAt(j)!='n' && s.charAt(j)!='t' &&s.charAt(j)!='i' && s.charAt(j)!='c'){
                    list.add(s.charAt(j));
                    set.add(s.charAt(j));
                }
            }
            for(Character c : set){
                sb.append(c);
            }
            str[i]=sb.toString();
        }
        list2.addAll(list);
        list = null;
        check = new boolean[list2.size()];

        if(max-5>list2.size()){ System.out.print(n); return;}
        if(max<5){ System.out.print(0); return;}
        if(max == 5){ // 문자를 못배움
            for(int i=0;i<str.length;i++){
                sw = false;
                if(str[i].equals("")){
                    cnt++;
                    continue;
                }
                for(int j=0;j<str[i].length();j++){
                    if(sw) break;
                    if(str[i].charAt(j)!='a' && str[i].charAt(j)!='n' && str[i].charAt(j)!='t'
                            &&str[i].charAt(j)!='i' && str[i].charAt(j) !='c') sw = true;
                }
                if(!sw) cnt++;
            }
            System.out.print(cnt);
        }else{
            backTracking(0,5);
            System.out.print(value);
        }
    }
    public static void backTracking(int start,int depth){
        if(depth == max){
            int mat = 0;
            ArrayList<Character> array = new ArrayList<>();
            //array.add('a');array.add('n');array.add('t');array.add('i');array.add('c');

            for(int i=0;i<check.length;i++){
                if(check[i]){
                    array.add(list2.get(i));
                }
            }

            for(String s : str){
                int size = 0;
                for(Character c : array){
                    if(s.contains(Character.toString(c))){
                        size++;
                    }
                    if(size == s.length()){
                        mat++;
                        break;
                    }
                }
            }

            if(mat>value) value = mat;
            return;
        }

        for(int i=start;i<check.length;i++){
            if(check[i]) continue;
            check[i] = true;
            backTracking(i+1,depth+1);
            check[i] = false;
        }
    }

}