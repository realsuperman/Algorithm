package BaekJoon_11650;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Location> array = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        String[] str;
        for(int i=0;i<n;i++){
            str = br.readLine().split(" ",2);
            array.add(new Location(Integer.parseInt(str[0]),Integer.parseInt(str[1])));
        }
        array = sort(array);
        for(Location location : array){
            sb.append(location.getX()+" "+location.getY()+"\n");
        }
        System.out.print(sb);
    }
    public static ArrayList<Location> sort(ArrayList<Location> stdList){
        Collections.sort(stdList, new Comparator<Location>() {
            @Override
            public int compare(Location o1, Location o2) {
                if(o1.getY()>o2.getY()){
                    return 1;
                }else if(o1.getY() == o2.getY()) {
                    return Integer.compare(o1.getX(), o2.getX());
                }else {
                    return -1;
                }
            }
        });
        return stdList;
    }
}

class Location{
    private int x;
    private int y;

    public Location(int x,int y){
        this.x = x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}