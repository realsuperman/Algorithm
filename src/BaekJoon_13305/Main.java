package BaekJoon_13305;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());
        Gas[] gas = new Gas[cnt];
        String str[] = br.readLine().split(" ");
        String str2[] = br.readLine().split(" ");
        int a,b;
        Long value = Long.valueOf(0);

        for(int i=0;i<cnt;i++){
            try{
                a = Integer.parseInt(str[i]);
            }catch(Exception e){
                a = 0;
            }
            b = Integer.parseInt(str2[i]);

            gas[i] = new Gas(a,b);
        }

        int index=0;
        while(true){
            long gasMoney=gas[index].getGasValue();
            int i;
            boolean sw = false;

            for(i=index+1;i<cnt-1;i++){
                if(gasMoney > gas[i].getGasValue()){
                    sw = true;
                    break;
                }
            }
            if(sw){
                long sum = 0;
                for(int j=index+1;j<=i;j++){
                    sum+=gas[j-1].getDistance();
                }
                index=i;
                value+=sum*gasMoney;
            }else{
                int sum = 0;
                for(int j=index;j<=cnt-1;j++){
                    sum+=gas[j].getDistance();
                }
                value+=sum*gasMoney;
                break;
            }
        }
        System.out.print(value);
    }
}

class Gas{
    private int distance;
    private int gasValue;

    public Gas(int distance,int gasValue){
        this.distance = distance;
        this.gasValue = gasValue;
    }

    public int getDistance() {
        return distance;
    }

    public int getGasValue() {
        return gasValue;
    }
}