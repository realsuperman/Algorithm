package BaekJoon_17136;

public class Main {
    static boolean[][] array = new boolean[10][10];
    static int[] paperCount = { 5, 5, 5, 5, 5 }; // 1x1, 2x2, 3x3, 4x4, 5x5
    static int MIN_COUNT = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            String[] str = br.readLine().split(" ");
            for (int j = 0; j < str.length; j++) {
                if (str[j].equals("1")) array[i][j] = true;
                else array[i][j] = false;
            }
        }

        int num = 0;
        for(int i=0;i<100;i++){
            int x = 0,y=0;
            if(i!=0) {
                x = i / 10;
                y = i % 10;
            }
            if(array[x][y]){num=i; break;}
        }

        solution(0,num);
        System.out.println(MIN_COUNT == Integer.MAX_VALUE ? -1 : MIN_COUNT);
    }

    private static void print(){
        System.out.println();
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void solution(int count,int num) {
        if (isFinish()) {
            MIN_COUNT = MIN_COUNT > count ? count : MIN_COUNT;
            return;
        }

        int x=0,y=0;
        if(num>0){ x=num/10; y=num%10;}

        if (array[x][y]) {
            for (int k = 0; k < 5; k++) { // 1x1, 2x2, 3x3, 4x4, 5x5(블록)
                List<V> list = new ArrayList<>();
                if (isCover(k, x, y,list)) {
                    int v = findOne(num + 1);
                    if(v!=-1) solution(count + 1, v);
                    else if(v==-1 && isFinish()) MIN_COUNT = MIN_COUNT > count+1 ? count+1 : MIN_COUNT;
                    postProcess(k, x, y,list);
                }
            }
        }
    }

    private static int findOne(int num){
        for(int i=num;i<100;i++){
            int x = 0,y=0;
            if(num!=0) {
                x = i / 10;
                y = i % 10;
            }
            if(array[x][y]) return i;
        }
        return -1;
    }

    private static void postProcess(int index, int x, int y, List<V> list) { // index는 종이 종류, type은 덮을 방향
        paperCount[index]++;
        if (index == 0) {
            array[x][y] = true;
        } else{
            for(V v : list) array[v.x][v.y]=true;
        }
    }

    private static boolean isRangeAndFalse(int x, int y){
        return (x<0||x>=10)||(y<0||y>=10)||(!array[x][y]);
    }

    private static boolean isCover(int index, int x, int y, List<V> list) { // index는 종이 종류, type은 덮을 방향
        boolean returnValue = false;

        if (paperCount[index] >= 1) {
            if (index == 0) { // 1x1
                if(!array[x][y]) return false;
                array[x][y] = false;
            }else {
                if (!settingLocation(x, y, index,list)) return false;
            }
            returnValue = true;
            paperCount[index]--;
        }

        return returnValue;
    }

    private static boolean settingLocation(int x,int y,int size, List<V> list){
        List<V> locations = new ArrayList<>();
        locations.add(new V(x,y));
        locations.add(new V(x,y+1));
        locations.add(new V(x+1,y));
        locations.add(new V(x+1,y+1));

        if(size>=2){ // 3x3
            locations.add(new V(x,y+2));
            locations.add(new V(x+1,y+2));
            locations.add(new V(x+2,y));
            locations.add(new V(x+2,y+1));
            locations.add(new V(x+2,y+2));
        }
        if(size>=3){ // 4x4
            locations.add(new V(x,y+3));
            locations.add(new V(x+1,y+3));
            locations.add(new V(x+2,y+3));
            locations.add(new V(x+3,y));
            locations.add(new V(x+3,y+1));
            locations.add(new V(x+3,y+2));
            locations.add(new V(x+3,y+3));
        }
        if(size>=4){ // 5x5
            locations.add(new V(x,y+4));
            locations.add(new V(x+1,y+4));
            locations.add(new V(x+2,y+4));
            locations.add(new V(x+3,y+4));
            locations.add(new V(x+4,y));
            locations.add(new V(x+4,y+1));
            locations.add(new V(x+4,y+2));
            locations.add(new V(x+4,y+3));
            locations.add(new V(x+4,y+4));
        }


        for(V v : locations) if(isRangeAndFalse(v.x,v.y)) return false;
        for(V v : locations) list.add(v);

        for(V v : list){
            array[v.x][v.y]=false;
        }
        return true;
    }

    private static boolean isFinish() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (array[i][j]) return false;
            }
        }
        return true;
    }
}

class V{
    int x;
    int y;
    public V(int x,int y){
        this.x=x;
        this.y=y;
    }
}