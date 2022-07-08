package BaekJoon_1132;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<String> list = new ArrayList<>();
        for (int i = 0; i < N; i++) list.add(br.readLine());
        long[] array = {1L,10L,100L, 1000L, 10000L, 100000L, 1000000L, 10000000L, 100000000L, 1000000000L, 10000000000L, 100000000000L, 1000000000000L, 10000000000000L};
        long[] alphabet = new long[10];
        int[] rank = new int[10];
        String[] StringArray = {"A","B","C","D","E","F","G","H","I","J"};

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).length(); j++) {
                alphabet[list.get(i).charAt(j) - 65] += array[list.get(i).length() - j];
            }
        }

        for (int i = 0; i < 10; i++) {
            int cnt = 0;
            for (int j = 0; j < 10; j++) {
                if (alphabet[i] < alphabet[j] && i != j) cnt++;
            }
            rank[i] = 9 - cnt;
        }

        boolean check = true;
        for (int i = 0; i < 10; i++) if(alphabet[i]==0){check=false; break;}
        if(check){
            String st = "";
            for (int i = 0; i < list.size(); i++)  st=st+list.get(i).charAt(0);

            int MIN = Integer.MAX_VALUE;
            int index = -1;

            for(int i=0;i<10;i++){
                if(!st.contains(StringArray[i]) && MIN>rank[i]){
                   index = i;
                   MIN = rank[i];
                }
            }
            int start = rank[index];

            for(int i=0;i<10;i++) if(rank[i]<start) rank[i]++;
            rank[index] = 0;
            alphabet[index] = 0;
        }

        for (int i = 0; i < 10; i++) {
            int value = rank[i];
            int temp = rank[i];

            for (int j = i + 1; j < 10; j++) {
                if (temp == rank[j]) rank[j] = --value;
            }
        }

        long sum = 0;
        for (int i = 0; i < list.size(); i++) {
            String str = "";
            for (int j = 0; j < list.get(i).length(); j++) {
                str += rank[list.get(i).charAt(j) - 65];
            }
            sum += Long.parseLong(str);
        }
        System.out.println(sum);
    }
}