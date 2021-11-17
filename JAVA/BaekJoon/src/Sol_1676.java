import java.util.Scanner;

public class Sol_1676 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int zeroCnt = 0;
        while (N >= 5) {
            zeroCnt += N / 5;
            N /= 5;
        }
        System.out.println(zeroCnt);
    }
}
