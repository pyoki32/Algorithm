import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Sol_1202 {
    static class Jewel{
        int weight;
        int value;
        public Jewel(int weight, int value){
            this.weight = weight;
            this.value = value;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        PriorityQueue<Jewel> pq = new PriorityQueue<>((j1, j2) -> j1.weight - j2.weight);
        PriorityQueue<Jewel> maxPq = new PriorityQueue<>((j1, j2) -> j2.value - j1.value);
        PriorityQueue<Integer>  minBag= new PriorityQueue<>((b1,b2) -> b1-b2);
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            pq.add(new Jewel(M,V));
        }


        for(int i=0 ;i< K ; i++){
            st= new StringTokenizer(br.readLine());
            int C = Integer.parseInt(st.nextToken());
            minBag.add(C);
        }

        long answer =0;
        while(!minBag.isEmpty()){
            int bagSize =minBag.poll();

            while(!pq.isEmpty()){
                Jewel jw = pq.poll();
                if(bagSize<jw.weight){
                    pq.add(jw);
                    break;
                }
                else maxPq.add(jw);
            }
            if(maxPq.isEmpty()) continue;
            Jewel maxValueJw= maxPq.poll();
            answer += maxValueJw.value;
        }
        System.out.println(answer);
    }

}
