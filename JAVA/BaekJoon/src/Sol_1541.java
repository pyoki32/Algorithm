import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Sol_1541 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int sum = 0;
        String[] splitMinus = input.split("-");

        for (int i = 0; i < splitMinus.length; i++) {
            String[] splitPlus = splitMinus[i].split("\\+");

            int plusSum = 0;
            for (String plus : splitPlus) {
                plusSum += Integer.parseInt(plus);
            }
            if (i == 0) sum += plusSum;
            else sum -= plusSum;
        }
        System.out.println(sum);
    }
}
