import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol_1509 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        boolean[][] palindrome = new boolean[str.length()][str.length()];
        int[] dp = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                boolean palindromeCheck = true;

                int start = i;
                int end = j;
                while (start <= end) {
                    if (str.charAt(start) != str.charAt(end)) {

                        palindromeCheck = false;
                        break;
                    }
                    start++;
                    end--;
                }
                if (palindromeCheck) palindrome[i][j] = true;
            }
        }

        for (int i = 0; i < str.length(); i++) dp[i] = Integer.MAX_VALUE;

        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < i + 1; j++) {
                if (palindrome[j][i]) {
                    if (j == 0) {
                        dp[i] = Math.min(dp[i], 1);
                    } else {
                        dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                    }
                }
            }
        }

        System.out.println(dp[str.length() - 1]);
    }
}
