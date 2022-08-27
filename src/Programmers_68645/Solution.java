package Programmers_68645;

class Solution {
    public int[] solution(int n) {
        int sum = 0;
        int[][] array = new int[n][1];
        for(int i=1;i<=n;i++){
            int[] temp = new int[i];
            array[i-1] = temp;
            sum+=i;
        }

        int v = 1;
        while(testMethod(array)){
            int[] temp = firstIndex(array);
            int x = temp[0];
            int y = temp[1];
            array[x][y]=v++;

            x++;

            while(x<n){
                if(array[x][y]>0) break;
                array[x++][y]=v++;
            }
            x--;
            y++;

            while(y<array[x].length){
                if(array[x][y]>0) break;
                array[x][y++]=v++;
            }
            y=y-2;
            x--;
            while(x>=0 && y>=0){
                if(array[x][y]>0) break;
                array[x--][y--]=v++;
            }
        }

        int[] answer = new int[sum];
        int index = 0;
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[i].length;j++){

                answer[index++]=array[i][j];
            }
        }
        return answer;
    }
    public boolean testMethod(int[][] array){
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[i].length;j++){
                if(array[i][j]==0) return true;
            }
        }
        return false;
    }
    public int[] firstIndex(int[][] array){
        int[] temp = new int[2];
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[i].length;j++){
                if(array[i][j]==0) {temp[0]=i; temp[1]=j; return temp;}
            }
        }
        return temp;
    }
}