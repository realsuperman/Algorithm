package BaekJoon_12919.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Boolean> map = new HashMap<>();
        String from = br.readLine();
        String to = br.readLine();
        Queue<String> queue = new LinkedList<>();
        queue.add(to);
        while(!queue.isEmpty()) {
            String v = queue.remove();
            if (v.equals(from)) {
                System.out.println(1);
                return;
            }
            if (v.length() <= 0)
                break;
            if (v.charAt(v.length() - 1) == 'A') {
                StringBuilder temp = new StringBuilder(v);
                temp.deleteCharAt(temp.length() - 1);
                if (map.get(temp.toString()) == null) {
                    map.put(temp.toString(), true);
                    queue.add(temp.toString());
                }
            }

            StringBuilder temp = new StringBuilder(v);
            temp.reverse();
            if(temp.charAt(temp.length()-1)=='B'){
                temp.deleteCharAt(temp.length() - 1);
                if (map.get(temp.toString()) == null) {
                    map.put(temp.toString(), true);
                    queue.add(temp.toString());
                }
            }

        }
        System.out.println(0);
    }
}