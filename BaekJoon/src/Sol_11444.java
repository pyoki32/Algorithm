import java.util.Scanner;

public class Sol_11444 {
	static long [][] matrix = {{1,1},{1,0}};
	static long [][] unitMatrix = {{1,0},{0,1}};
	static long MOD = 1000000007;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		 
		//n-1 * (1,0)
		long [][] answer = calcPow(matrix,n-1);
		
			System.out.println(answer[0][0]);
		
	
	}
	public static long[][] calcPow(long [][] m, long n) {
		
		if(n == 0) {
			return unitMatrix;	
		}else {	
			long[][] temp = calcPow(m,n/2);
			if(n%2==0)return matrixMultiply(temp,temp);
			else return matrixMultiply(matrixMultiply(temp,temp),m);		
		}
	}
	
	public static long[][] matrixMultiply(long [][] m1, long [][]m2){
		
		long [][] res = new long[2][2];		
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				for(int k=0;k<2;k++) {
					res[i][j] += m1[i][k] * m2[k][j];
				}
				res[i][j] %= MOD;
			}
		}
		return res; 
		
	}

}
