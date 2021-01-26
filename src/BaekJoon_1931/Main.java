package BaekJoon_1931;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Value> array = new ArrayList<>();
        int cnt = 0;
        int end = -1;

        String[] str;
        for(int i=0;i<n;i++){
            str = br.readLine().split(" ",2);
            array.add(new Value(Integer.parseInt(str[0]),Integer.parseInt(str[1])));
        }
        array = sort(array);
        for(int i=0; i<array.size(); i++) {
            if(array.get(i).getStart() >= end) {
                end = array.get(i).getEnd();
                cnt++;
            }
        }
        System.out.println(cnt);
    }
    public static ArrayList<Value> sort(ArrayList<Value> stdList){
        Collections.sort(stdList, new Comparator<Value>() {
            @Override
            public int compare(Value o1, Value o2) {
                if(o1.getEnd()>o2.getEnd()){
                    return 1;
                }else if(o1.getEnd() == o2.getEnd()) {
                    return Integer.compare(o1.getStart(), o2.getStart());
                }else {
                    return -1;
                }
            }
        });
        return stdList;
    }
}

class Value{
    private int start;
    private int end;
    private int difference;

    public Value(int start,int end){
        this.start = start;
        this.end = end;
        difference = end-start;
    }

    public int getDifference() {
        return difference;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}