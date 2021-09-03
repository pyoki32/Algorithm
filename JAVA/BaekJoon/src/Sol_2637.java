import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_2637 {
	static class Part{
		int num;
		int cnt;
		public Part(int num, int cnt) {
			this.num=num;
			this.cnt=cnt;
		}
	}
	static ArrayList<ArrayList<Part>> adjList ;
	static int [][]partCnt;
	static int [] indegree;
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N= Integer.parseInt(st.nextToken());
		adjList = new ArrayList<>();
		for(int i=0;i<=N;i++) {
			adjList.add(new ArrayList<>());
		}
		indegree = new int[N+1];
		st = new StringTokenizer(br.readLine());
		int M= Integer.parseInt(st.nextToken());
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			adjList.get(Y).add(new Part(X,K));
			indegree[X]++;
		}
		Queue<Integer> q =new LinkedList<>();
		partCnt = new int[N+1][N+1];
		
		for(int i=1;i<=N;i++)
			if(indegree[i]==0) {
				q.add(i);
				partCnt[i][i]=1;
			}
		
		while(!q.isEmpty()) {
			int num =q.poll();
			ArrayList<Part> temp = adjList.get(num);
			for(Part nextP : temp) {
				int nextNum = nextP.num;
				int nextCnt = nextP.cnt;
				for(int i=1;i<=N;i++) {
					partCnt[nextNum][i] += partCnt[num][i]*nextCnt;
				}
				indegree[nextNum]--;
				if(indegree[nextNum]==0)q.add(nextNum);
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=N;i++) {
			if(partCnt[N][i]!=0)sb.append(i).append(' ').append(partCnt[N][i]).append('\n');
		}
		
		System.out.println(sb);
	}
	
}
