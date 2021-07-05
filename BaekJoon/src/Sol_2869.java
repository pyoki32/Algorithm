import java.util.Scanner;

public class Sol_2869 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long A = sc.nextInt();
		long B = sc.nextInt();
		long V = sc.nextInt();
		
		
		int ans=1;
		long go = A-B;
		long goal = V-A;
		long day = goal/go;
		long etc = goal%go;
		
		if(etc<=A) {
			ans +=day;
		}else {
			ans+= day+1;
		}

		System.out.println(ans);
	}

}
