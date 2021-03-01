package BaekJoon_11000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int i=0;
        String[] str;
        ArrayList<Master> master = new ArrayList<>();
        PriorityQueue<Integer> last = new PriorityQueue<>();
        int cnt = 0;

        while(i<n){
            str = br.readLine().split(" ");
            master.add(new Master(Integer.parseInt(str[0]),Integer.parseInt(str[1])));
            i++;
        }
        master = sort(master);
        last.offer(master.get(0).getTo());
        cnt++;

        for(i=1;i<master.size();i++){
            if(master.get(i).getFrom()>=last.peek()){
                last.poll();
            }else{
                cnt++;
            }
            last.offer(master.get(i).getTo());
        }

        System.out.print(cnt);
    }
    public static ArrayList<Master> sort(ArrayList<Master> stdList){
        Collections.sort(stdList, new Comparator<Master>() {
            @Override
            public int compare(Master o1, Master o2) {
                if(o1.getFrom()>o2.getFrom()){
                    return 1;
                }else if(o1.getFrom() == o2.getFrom()){
                    return Integer.compare(o1.getTo(), o2.getTo());
                }else {
                    return -1;
                }
            }
        });
        return stdList;
    }
}

class Master{
    private int from;
    private int to;

    public Master(int from,int to){
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }
}