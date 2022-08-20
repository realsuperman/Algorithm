package Programmers_equalQueue;

import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long sum = 0;
        List<Integer> list1 = new LinkedList<>();
        List<Integer> list2 = new LinkedList<>();
        long left = 0; // 왼쪽 리스트 합계
        long right = 0; // 오른쪽 리스트 합계
        long[] temp = new long[2];

        for(int v : queue1){
            list1.add(v);
            left+=v;
        }

        for(int v : queue2){
            list2.add(v);
            right+=v;
        }

        sum = left+right;
        if(sum%2==1) return -1;
        temp[0] = left; // 왼쪽 리스트 합계 저장
        temp[1] = right; // 오른쪽 리스트 합계 저장
        int size1 = list1.size();
        int size2 = list2.size();
        sum/=2;

        int cnt = 0;
        while(left!=sum && right!=sum){
            if(cnt > size1 + size2+2) return -1;

            if(left>sum){ // 왼쪽 리스트에서 덜어서 오른쪽에 넣어줌
                int v = list1.remove(0);
                left-=v;
                right+=v;
                list2.add(v);
            }else{ // 오른쪽 리스트에서 덜어서 왼쪽에 넣어줌
                int v = list2.remove(0);
                right-=v;
                left+=v;
                list1.add(v);
            }
            cnt++;
        }
        return cnt;
    }
}