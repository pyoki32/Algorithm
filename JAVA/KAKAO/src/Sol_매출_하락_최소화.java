import java.util.ArrayList;



public class Sol_매출_하락_최소화 {

	static ArrayList<ArrayList<Integer>> tree;
	static int MIN;
	static int [][] dp;

	public static void main(String[] args) {

		int[] sales = {10, 10, 1, 1};
		int[][] links = { {3,2}, {4,3}, {1,4}};

		int answer = solution(sales, links);
		System.out.println(answer);
		int [] pick = new int[3];
		pickIdx(pick,0,0,2);
	}

	public static int solution(int[] sales, int[][] links) {
		int answer = 0;
		int nodeCnt = sales.length;
		tree = new ArrayList<>();
		for (int i = 0; i <= nodeCnt; i++) {
			tree.add(new ArrayList<>());
		}
		for (int i = 0; i < links.length; i++) {
			tree.get(links[i][0]).add(links[i][1]);
		}
		dp = new int[nodeCnt+1][2];
		treeDp(1,sales);
		answer=Math.min(dp[1][0],dp[1][1]);
		
		return answer;
	}
	static void treeDp(int node,int[] sales) {
		 
		 dp[node][0]=0;
		 dp[node][1]=sales[node-1];
		 
		 if(tree.get(node).isEmpty()) return;
		 
		 ArrayList<Integer> childs = tree.get(node);
		 int minPersonnel= Integer.MAX_VALUE;
		 for(int child : childs) {
			 treeDp(child,sales);	
			 if(dp[child][0]<dp[child][1]) {
				 dp[node][0] += dp[child][0];
				 dp[node][1] += dp[child][0];
				 minPersonnel= Math.min(minPersonnel, dp[child][1]-dp[child][0]);
				 
			 }else {
				 dp[node][0] += dp[child][1];
				 dp[node][1] += dp[child][1];
				 minPersonnel=0;
			 }
		 }
		 dp[node][0]+=minPersonnel;
	}
	static void pickIdx(int [] pick ,int start ,int depth, int cnt){
		
		if(depth>cnt){
			for(int k:pick){
				System.out.print(k+" ");
			}
			System.out.println();
			return;
		}
	for(int k=start;k<pick.length;k++){
					
					pick[depth]=k;
					pickIdx(pick,k+1,depth+1,cnt);			
		}
}	
}
