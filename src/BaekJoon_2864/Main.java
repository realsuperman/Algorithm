package BaekJoon_2864;


import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();
        String temp,temp2 = null;

        temp = a;
        temp2 = b;
        int max = Integer.parseInt(a.replaceAll("5","6"));;
        int max2 = Integer.parseInt(b.replaceAll("5","6"));

        a = temp;
        b = temp2;
        int min = Integer.parseInt(a.replaceAll("6","5"));
        int min2 = Integer.parseInt(b.replaceAll("6","5"));

        System.out.println((min+min2)+" "+(max+max2));
    }

}