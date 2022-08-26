package Programmers_17679;

class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        char[][] array = new char[m][n];
        for(int i=0;i<board.length;i++){
            String[] str = board[i].split("");
            for(int j=0;j<str.length;j++){
                array[i][j]=str[j].charAt(0);
            }
        }

        char[][] temp = new char[m][n];
        for(int i=0;i<m;i++) for(int j=0;j<n;j++) temp[i][j]=array[i][j];
        while(true){
            answer+=simul(m,n,array);
            if(comp(temp,array,m,n)) break;
            for(int i=0;i<m;i++) for(int j=0;j<n;j++) temp[i][j]=array[i][j];
        }
        simul(m,n,array);
        return answer;
    }

    public int simul(int n, int m,char[][] board){
        boolean[][] check = new boolean[n][m];
        int[][] array = {
                {-1,-1,-1,0,0,-1},
                {-1,0,0,1,-1,1},
                {1,0,0,1,1,1}
        };

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                char ch = board[i][j];
                if(ch==' ') continue;
                for(int k=0;k<3;k++){
                    int x1 = array[k][0]+i;
                    int y1 = array[k][1]+j;
                    if(x1>=n || x1<0 || y1>=m || y1<0) continue;

                    int x2 = array[k][2]+i;
                    int y2 = array[k][3]+j;
                    if(x2>=n || x2<0 || y2>=m || y2<0) continue;

                    int x3 = array[k][4]+i;
                    int y3 = array[k][5]+j;
                    if(x3>=n || x3<0 || y3>=m || y3<0) continue;
                    if(board[x1][y1]==ch && board[x2][y2]==ch && board[x3][y3]==ch){
                        check[i][j]=true;
                        check[x1][y1]=true;
                        check[x2][y2]=true;
                        check[x3][y3]=true;
                    }
                }

            }
        }

        int blockCnt = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(check[i][j]){
                    blockCnt++;
                    board[i][j]=' ';
                }
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(board[i][j]==' '){
                    for(int k=i;k>0;k--){
                        board[k][j]=board[k-1][j];
                    }
                    board[0][j]=' ';
                }
            }
        }
        return blockCnt;
    }

    public boolean comp(char[][] original,char[][] target,int n,int m){
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(original[i][j]!=target[i][j]) return false;
            }
        }
        return true;
    }
}