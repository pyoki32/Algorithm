
public class Sol_기지국_설치 {

	public static void main(String[] args) {
		int n = 16;
		int[] stations = {9};
		int w = 2;
		System.out.println(solution(n, stations, w));
	}

	public static int solution(int n, int[] stations, int w) {
		int answer = 0;
		int start =0;
		int total =0;
		int waveRange=2*w+1;
		for(int i=0;i<stations.length;i++) {	
			int range = stations[i]-w-start-1;
			int cnt =0;
			if(range>0)cnt = calCnt(range,waveRange);
			total+=cnt;
	    	start = stations[i]+w;
		}
		if(start<=n-1) {
			int range = n-start;
			int cnt = calCnt(range,waveRange);
			total+=cnt;
		}
		answer = total;
		return answer;
	}
	static int calCnt(int range, int waveRange) {
		int res=0;
		int a = range/waveRange;
    	int b = range%waveRange;
    	res = a;
    	if(b!=0)res++;
    	return res;
	}
}
