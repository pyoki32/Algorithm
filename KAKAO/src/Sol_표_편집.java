import java.util.Stack;

public class Sol_표_편집 {

	public static void main(String[] args) {
		int n =8;
		int k =2;
		String [] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
		System.out.print(solution(n,k,cmd));
	}

	public static String solution(int n, int k, String[] cmd) {
		String answer = "";
		boolean []delCheck = new boolean[n];
		int selectedIdx = k;
		Stack<Integer> delIdx = new Stack<>();
		for(int i=0;i<cmd.length;i++) {
			char cmdKey = cmd[i].charAt(0);
			String [] cmdStr=cmd[i].split(" ");
			switch(cmdKey) {
			case 'U':
				int up =Integer.parseInt(cmdStr[1]);
				if(selectedIdx-1>=0) {
				for(int j=selectedIdx-1;j>=0;j--) {
					if(!delCheck[j]) {
						up--;
						selectedIdx=j;
					}
					if(up==0) break;
				}
				}				
				break;
			
			case 'D':
				int down =Integer.parseInt(cmdStr[1]);
				if(selectedIdx+1<n) {
				for(int j=selectedIdx+1;j<n;j++) {
					if(!delCheck[j]) {
						down--;
						selectedIdx=j;
					}
					if(down==0) break;
				}
				}
				break;		
			case 'C':
				delCheck[selectedIdx]=true;
				delIdx.push(selectedIdx);
				boolean downCheck=false;
				if(selectedIdx+1<n) {
					for(int j=selectedIdx+1;j<n;j++) {
						if(!delCheck[j]) {
							selectedIdx=j;
							downCheck =true;
							break;
						}
					}
				}
				if(!downCheck) {
						if(selectedIdx-1>=0) {
							for(int j=selectedIdx-1;j>=0;j--) {
								if(!delCheck[j]) {
									selectedIdx=j;
									break;
								}
							}
						}
					}
				break;
				
			case 'Z':
				int zIdx=delIdx.pop();
				delCheck[zIdx]=false;
				break;		
			}		
		}
		for(int i=0;i<n;i++) {
			if(!delCheck[i]) {
				answer+="O";
			}else {
				answer+="X";
			}
		}
		
		return answer;
	}
}
