package BaekJoon_1181;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Set<String> set = new HashSet<>();
        ArrayList<Word> array = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<n;i++) set.add(br.readLine());
        for(String str : set) array.add(new Word(str));

        array = sort(array);
        for(Word location : array){
            sb.append(location.getStr()+"\n");
        }
        System.out.print(sb);
    }
    public static ArrayList<Word> sort(ArrayList<Word> stdList){
        Collections.sort(stdList, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                if(o1.getLength()>o2.getLength()){
                    return 1;
                }else if(o1.getLength() == o2.getLength()) {
                    int num = -1;
                    for(int i=0;i<o1.getLength();i++){
                        if(o1.getStr().charAt(i) == o2.getStr().charAt(i)){
                            continue;
                        }else{
                            num = Integer.compare(o1.getStr().charAt(i), o2.getStr().charAt(i));
                            break;
                        }
                    }
                    return num;
                }else {
                    return -1;
                }
            }
        });
        return stdList;
    }
}

class Word{
    String str;

    public Word(String str){
        this.str = str;
    }

    public String getStr() {
        return str;
    }
    public int getLength(){
        return str.length();
    }

}