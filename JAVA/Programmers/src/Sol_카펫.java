
public class Sol_카펫 {

	public static void main(String[] args) {
		int [] ans = new int[2];
		ans = solution(10,2);
		System.out.println(ans[0]+"--"+ans[1]);
	
	}
	
	public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2]; 
        int total = brown+yellow;
         for(int i =1;i<=total;i++) {
        	 if(total%i ==0) {
        		 int A= total/i;
        		 int B = i;
        		 int col =0;
        		 int row =0;
        		 if(A>=B) {
        			 col=A;
        			 row=B;
        		 }
        		 else {
        			 col=B;
        			 row=A;
        		 }
        		
        		int borderCnt = (2*row)+(2*col)-4;
        		if(brown==borderCnt) {
        		answer[0]=col;
        		answer[1]=row;
        		}
        	 }
         }
        return answer;
    }

}
