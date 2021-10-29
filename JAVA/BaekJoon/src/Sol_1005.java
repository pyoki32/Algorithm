import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_1005 {
        static int N,K,W;
        static int [] D;
        static ArrayList<ArrayList<Integer>> adjList;
        static int [] inDegree;
        static int [] times;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int tc =0 ; tc<T;tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            D = new int[N+1];
            inDegree = new int[N+1];
            times = new int[N+1];
            adjList = new ArrayList<>();
            adjList.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            for(int i=1;i<=N;i++){
                D[i]=Integer.parseInt(st.nextToken());
                adjList.add(new ArrayList<>());
            }
            for(int i =0; i< K; i++){
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                adjList.get(X).add(Y);
                inDegree[Y]++;
            }
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            topologySort();
            sb.append(times[W]).append('\n');
        }
        System.out.println(sb);

    }
    static void topologySort(){
        Queue<Integer> q = new LinkedList<>();
        for(int i=1;i<=N;i++){
            if(inDegree[i]==0)q.add(i);
            times[i]=D[i];
        }

        while(!q.isEmpty()){
               int num = q.poll();
               ArrayList<Integer> nextNums = adjList.get(num);
               for(int nextNum : nextNums){
                   inDegree[nextNum]--;
                   times[nextNum]=Math.max(times[nextNum],times[num]+D[nextNum]);
                   if(inDegree[nextNum]==0)q.add(nextNum);
               }
        }
    }
}
