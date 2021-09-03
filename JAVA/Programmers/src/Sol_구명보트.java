import java.util.Arrays;

public class Sol_구명보트 {
    public static void main(String[] args) {
        int[] people = {70, 80, 50};
        int limit = 100;
        System.out.println(solution(people, limit));
    }
    public static int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int leftIdx = 0;
        int rightIdx = people.length-1;
        int cnt =0;
        int weight = people[rightIdx];
        while(leftIdx<=rightIdx){
           weight += people[leftIdx];
           if(weight>limit){
               cnt++;
               rightIdx--;
               weight =0;
               if(leftIdx<rightIdx)weight = people[rightIdx];
           }else leftIdx++;
        }
        answer =cnt;
        if(weight>0) answer++;
        return answer;
    }
}
