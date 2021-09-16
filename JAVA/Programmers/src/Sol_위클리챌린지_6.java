import java.util.Comparator;
import java.util.PriorityQueue;

public class Sol_위클리챌린지_6 {
    public static void main(String[] args) {
        int[] weights = {50, 82, 75, 120};
        String[] head2head = {"NLWL", "WNLL", "LWNW", "WWLN"};
        int [] answer = solution(weights, head2head);
        for(int k : answer) System.out.println(k);
    }

    static class Boxer {
        int num;
        float winRate;
        int overWeightWinCnt;
        int weight;

        public Boxer(int num, float winRate, int overWeightWinCnt, int weight) {
            this.num = num;
            this.winRate = winRate;
            this.overWeightWinCnt = overWeightWinCnt;
            this.weight = weight;
        }
    }

    public static int[] solution(int[] weights, String[] head2head) {

        PriorityQueue<Boxer> pq = new PriorityQueue<>(new Comparator<Boxer>() {
            @Override
            public int compare(Boxer b1, Boxer b2) {
                if ( Float.compare(b1.winRate, b2.winRate)==0) {
                    if (b1.overWeightWinCnt == b2.overWeightWinCnt) {
                        if (b1.weight == b2.weight) {
                            return b1.num - b2.num;
                        }
                        return -(b1.weight - b2.weight);
                    }
                    return -(b1.overWeightWinCnt - b2.overWeightWinCnt);
                }

                return -Float.compare(b1.winRate, b2.winRate);
            }
        });
        for (int i = 0; i < weights.length; i++) {
            int winCnt = 0;
            int overWeightWinCnt = 0;
            int matchCnt = 0;
            for (int j = 0; j < head2head[i].length(); j++) {
                if (i != j) {
                    if (head2head[i].charAt(j) == 'W') {
                        winCnt++;
                        if (weights[i] < weights[j]) {
                            overWeightWinCnt++;
                        }
                    }
                    if (head2head[i].charAt(j) != 'N') matchCnt++;
                }
            }
            float winRate = 0;
            if (matchCnt != 0) winRate =  ((float)winCnt / (float)matchCnt)*100;
            pq.add(new Boxer(i + 1, winRate, overWeightWinCnt, weights[i]));
        }
        int[] answer = new int[weights.length];
        int idx =0;
        while(!pq.isEmpty()){
            answer[idx]= pq.poll().num;
            idx++;
        }
        return answer;
    }
}
