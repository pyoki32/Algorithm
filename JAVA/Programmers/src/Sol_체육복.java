import java.util.Arrays;

public class Sol_체육복 {
    public static void main(String [] args) {
        int n = 7;
        int [] lost ={2,3,4};
        int [] reserve ={1,2,3,6 };
        System.out.println(solution(n,lost,reserve));
    }
    public static int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        Arrays.sort(lost);
        Arrays.sort(reserve);
        boolean [] extraClothes = new boolean[n+1];
        for(int i=0 ; i< reserve.length;i++){
             extraClothes[reserve[i]] =true;
        }
        answer = n-lost.length;
        boolean [] clothes = new boolean[lost.length];
        for(int i=0 ; i< lost.length;i++){
            if(extraClothes[lost[i]]){
                answer++;
                extraClothes[lost[i]]=false;
                clothes[i]=true;
            }
        }
        for(int i=0;i<lost.length;i++) {
            if (!clothes[i]) {
                if (lost[i] - 1 > 0) {
                    if (extraClothes[lost[i] - 1]) {
                        extraClothes[lost[i] - 1] = false;
                        answer++;
                        continue;
                    }
                }
                if (lost[i] + 1 <= n) {
                    if (extraClothes[lost[i] + 1]) {
                        extraClothes[lost[i] + 1] = false;
                        answer++;
                        continue;
                    }
                }
            }
        }
        return answer;
    }
}
