import java.util.ArrayList;

public class Sol_삼각_달팽이 {
    public static void main(String[] args) {
        int n=6;
        int [] ans =solution(n);
        for(int k : ans){
            System.out.println(k);
        }
    }
    static int [][] arr;
    public static int[] solution(int n) {
        arr= new int[n+1][n+1];
        fillNum(1,1,n,1);
        ArrayList<Integer> res = new ArrayList<>();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(arr[i][j]!=0)res.add(arr[i][j]);
            }
        }
        int[] answer = new int[res.size()];
        for(int i=0;i<answer.length;i++){
            answer[i]=res.get(i);
        }
        return answer;
    }
    static void fillNum(int startR,int startC,int n,int num){
         if(startR>arr.length || startC>arr.length)return;
        for(int i=startR;i<startR+n;i++){
            arr[i][startC]=num;
            num++;
        }
        for(int i=startC+1;i<startC+n;i++){
            arr[startR+n-1][i]=num;
            num++;
        }
        for(int i=1;i<n-1;i++){
            arr[startR+n-1-i][startC+n-1-i]=num;
            num++;
        }
      fillNum(startR+2,startC+1,(n-3),num);
    }
}
