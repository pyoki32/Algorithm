import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Sol_11652 {
    static class Card{
        long num;
        int cnt;
        public Card(long num, int cnt){
            this.num =num;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        HashMap<Long, Integer> hm = new HashMap<>();
        for(int i=0;i<N;i++){
            String str = br.readLine();
            Long num = Long.parseLong(str);
            if(hm.containsKey(num)){
                hm.put(num,hm.get(num)+1);
            }else{
                hm.put(num,1);
            }
        }
        PriorityQueue<Card> pq =new PriorityQueue<>(new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                if(c1.cnt==c2.cnt){
                    if(c1.num<c2.num) return -1;
                    else return 1;
                }
                return -(c1.cnt-c2.cnt);
            }
        });
        for( Long key : hm.keySet() ){
            Integer value = hm.get(key);
            pq.add(new Card(key, value));
        }
        Card answer = pq.poll();
        System.out.println(answer.num);
    }
}
