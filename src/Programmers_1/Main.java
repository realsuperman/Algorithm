package Programmers_1;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution("100","2345"));
        System.out.println(solution("100","203045"));
        System.out.println(solution("100","123450"));
        System.out.println(solution("12321","42531"));
        System.out.println(solution("5525","1255"));

        System.out.println(solution("123450","100"));
    }

    public static String solution(String X,String Y){
        int[] array = new int[10];

        for (int i = 0; i < X.length(); i++) {
            array[X.charAt(i)-48]++;
        }

        int[] array2 = new int[10];
        for(int i=0;i<Y.length();i++){
            if(array[Y.charAt(i)-48]>0){
                array[Y.charAt(i)-48]--;
                array2[Y.charAt(i)-48]++;
            }
        }

        StringBuilder result = new StringBuilder();
        boolean chk = true;
        for(int i=9;i>=0;i--) if(array2[i]!=0){ chk=false; break;}
        if(chk) return "-1";

        for(int i=9;i>=0;i--){
            for(int j=array2[i];j>0;j--) result.append(i);
        }

        if(result.charAt(0)=='0') return "0";

        return result.toString();
    }
}