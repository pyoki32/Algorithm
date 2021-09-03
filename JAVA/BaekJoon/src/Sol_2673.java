import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sol_2673 {
	static class Part{
		int num;
		int cnt;
		public Part(int num, int cnt) {
			this.num=num;
			this.cnt=cnt;
		}
	}
	static int N,M;
	static ArrayList<ArrayList<Part>> adjList;
	static ArrayList<Integer> target ;
	static int []basicParts;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M=Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList<>();
		
		for(int i=0;i<=N;i++) {
			adjList.add(new ArrayList<Part>());
		}
		
		basicParts = new int[N+1];
		boolean [] basicCheck = new boolean[N+1]; 
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			adjList.get(X).add(new Part(Y,K));
			basicCheck[X]=true;
		}
		target = new ArrayList<>();
		for(int i=1;i<=N;i++) {
			if(!basicCheck[i])
			target.add(i);
			}
		
				solve(N);
			
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=N;i++) {
			if(!basicCheck[i])
			sb.append(i+" "+basicParts[i]).append('\n');
		}
		System.out.println(sb);
		
	}
	static void solve(int start) {
			
			ArrayList<Part> temp = adjList.get(start);
			
			for(Part p : temp) {
				int num =p.num;
				int cnt = p.cnt; 
				for(int t : target) {
					if(t==num) {
						basicParts[t] +=cnt;
					}
				}
				
				for(int k=0;k<cnt;k++)solve(num);
					
			}
	}

}
