class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        for(int i=left;i<=right;i++){
            if(i==1){
                answer-=1;
            }else{
                if(isPrimeNumber(i)) answer+=i;
                else answer-=i;
            }
        }
        return answer;
    }

    public boolean isPrimeNumber(int number){
        int cnt = 2;
        for(int i=2;i<=number/2;i++){
            if(number%i==0){
                cnt++;
            }
        }
        if(cnt%2==0) return true;
        else return false;
    }
}