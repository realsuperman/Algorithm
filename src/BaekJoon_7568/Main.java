package BaekJoon_7568;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Human[] humans = new Human[n];
        for(int i=0;i<n;i++){
            String[] str = br.readLine().split(" ");
            humans[i] = new Human(Integer.parseInt(str[1]),Integer.parseInt(str[0]),1);
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==j) continue;
                if(humans[i].getHeight()<humans[j].getHeight() && humans[i].getWeight()<humans[j].getWeight()) humans[i].addRank();
            }
        }
        for(Human human : humans){
            System.out.print(human.getRank()+" ");
        }

    }
}

class Human{
    private int height;
    private int weight;
    private int rank;

    public Human(int height,int weight,int rank){
        this.height = height;
        this.weight = weight;
        this.rank = rank;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getRank() {
        return rank;
    }

    public void addRank(){
        this.rank++;
    }
}