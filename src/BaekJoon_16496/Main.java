package BaekJoon_16496;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<String> list = new ArrayList<>();
        String[] str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) list.add(str[i]);
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2+o1).compareTo(o1+o2);
            }
        });
        String result = "";
        for(String value : list) result+=value;
        if(result.charAt(0)=='0') {System.out.println(0); return; }
        System.out.println(result);
    }
}