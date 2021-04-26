

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_17825 {
	static class Horse{
		int row;
		int col;
		boolean stop;
		public Horse(int row, int col ,boolean stop) {
			this.row=row;
			this.col=col;
			this.stop=stop;
		}
	}
		
		static int[] diceNum ;
		static int max;
		static int[][] gameBoard = { 	
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
				{ 4, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 6, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 8, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 10, 13, 16, 19, 25, 30, 35, 40, 0, 0 },
				{ 12, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 14, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 16, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 18, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 20, 22, 24, 25, 30, 35, 40, 0, 0, 0 },
				{ 22, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 24, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 26, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 28, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 30, 28, 27, 26, 25, 30, 35, 40, 0, 0 },
				{ 32, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 34, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 36, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 38, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 40, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		diceNum = new int[10];
		max = Integer.MIN_VALUE;
		for (int i = 0; i < 10; i++) {
			diceNum[i] = Integer.parseInt(st.nextToken());
		}
		
		int [] pick = new int[10];
		pickOrder(pick,0);
		System.out.println(max);
	}
	static void pickOrder(int [] pick,int depth) {
		 if(depth>9) {
			max = Math.max(max,move(pick));
			
			return;
		 }
		
		for(int i=0;i<4;i++) {
			pick[depth] = i;
			pickOrder(pick,depth+1);
			
		}
	}
	static int move(int [] pick) {
			int point =0;
			boolean [][]horseCheck = new boolean [22][10];
			boolean [] commonPath = new boolean[4];

			Horse [] horses = new Horse[4];
			for(int i =0 ;i<4;i++) {
				horses[i]= new Horse(0,0,false);	
			}
			
			for(int k=0;k<10;k++) {
				int moveCnt =diceNum[k];
				int horseNum = pick[k];
				Horse h= horses[horseNum];
				int row = h.row;
				int col = h.col;
				boolean stop = h.stop;
				int nrow = row;
				int ncol = col;
				//System.out.println(k+"---->["+horseNum+"]"+row+" "+col);
			if (!stop) {
				
				if (row == 5 || row == 10 || row == 15) {
					ncol = col + moveCnt;
				} else {
					nrow = row + moveCnt;
				}
				if (isRange(nrow, ncol)) {
					if (gameBoard[nrow][ncol] == 0) {
						horseCheck[row][col]=false;
						horses[horseNum].row=nrow;
						horses[horseNum].col=ncol;
						horses[horseNum].stop=true;
						if(gameBoard[row][col]==25 ||(gameBoard[row][col]==30 && row !=0) || gameBoard[row][col]==35
								 || gameBoard[row][col]==40) {
						 	 int beforeNode = (gameBoard[row][col]/5)-5;
						 	 commonPath[beforeNode]=false;
				 		}
					}
								
					 if (!horseCheck[nrow][ncol] && gameBoard[nrow][ncol] != 0) {
						 if(gameBoard[nrow][ncol]==25 || (gameBoard[nrow][ncol]==30 && ncol !=0) || gameBoard[nrow][ncol]==35
								 || gameBoard[nrow][ncol]==40) {
							 	 int node = (gameBoard[nrow][ncol]/5)-5;
							 	
							 	 if(!commonPath[node]) {
							 		horseCheck[row][col]=false;
							 		commonPath[node]=true;
							 		horses[horseNum].row=nrow;
									horses[horseNum].col=ncol;
									point+=gameBoard[nrow][ncol];
							 		if(gameBoard[row][col]==25 ||(gameBoard[row][col]==30 && col !=0) || gameBoard[row][col]==35
											 || gameBoard[row][col]==40) {
									 	 int beforeNode = (gameBoard[row][col]/5)-5;
									 	 commonPath[beforeNode]=false;
							 		}
							 	 }else {
							 		 return -1;
							 	 }
							 	
						 }						 
						 else {
								 horseCheck[row][col]=false;
								 horseCheck[nrow][ncol]=true;
								 horses[horseNum].row=nrow;
								 horses[horseNum].col=ncol;
								 point+=gameBoard[nrow][ncol];
						 }
					}
					
					else {
						return -1;
					}
		
				}
				//범위가 벗어난건 도착지점을 넘어갔다는 의미
				else {
					horseCheck[row][col]=false;
					horses[horseNum].stop=true;
					if(gameBoard[row][col]==25 ||(gameBoard[row][col]==30 && row !=0) || gameBoard[row][col]==35
							 || gameBoard[row][col]==40) {
					 	 int beforeNode = (gameBoard[row][col]/5)-5;
					 	 commonPath[beforeNode]=false;
			 		}
				}
				
				
			}
			//stop 주사위를 선택했다는것은  조합이 잘못됬다는 이야기
			else {
				return -1;
					
			}
		}
			
		return point;
	}
	static boolean isRange(int nrow ,int ncol) {
		if(0<=nrow && nrow <22 && 0<=ncol && ncol<10)return true;
		return false;
	}
}
