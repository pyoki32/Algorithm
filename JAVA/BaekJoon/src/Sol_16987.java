import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_16987 {
    static int N;
    static boolean[] brokenCheck;
    static int[] durability;
    static int[] weight;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        brokenCheck = new boolean[N];
        durability = new int[N];
        weight = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            durability[i] = Integer.parseInt(st.nextToken());
            weight[i] = Integer.parseInt(st.nextToken());
        }
        answer = Integer.MIN_VALUE;
        breakEgg(0, 0);
        System.out.println(answer);

    }

    static void breakEgg(int start, int totalBrokenEggCnt) {
        if (start >= N) {
            answer = Math.max(answer, totalBrokenEggCnt);
            return;
        }

        if (!brokenCheck[start]) {
             boolean eggBroken =false;
            for (int i = 0; i < brokenCheck.length; i++) {
                if (!brokenCheck[i] && start != i) {
                    eggBroken=true;
                    int brokenEggCnt = 0;
                    int pickEggDurability = durability[start];
                    int pickEggWeight = weight[start];
                    int targetEggDurability = durability[i];
                    int targetEggWeight = weight[i];
                    boolean pickCBrokenCheck = false;
                    boolean targetBrokenCheck = false;
                    if ((pickEggDurability - targetEggWeight) <= 0) {
                        brokenCheck[start] = true;
                        pickCBrokenCheck = true;
                        brokenEggCnt++;
                    }
                    if ((targetEggDurability - pickEggWeight) <= 0) {
                        brokenCheck[i] = true;
                        targetBrokenCheck = true;
                        brokenEggCnt++;
                    }
                    durability[start] = pickEggDurability - targetEggWeight;
                    durability[i] = targetEggDurability - pickEggWeight;
                    breakEgg(start + 1, totalBrokenEggCnt + brokenEggCnt);
                    if (targetBrokenCheck) brokenCheck[i] = false;
                    if (pickCBrokenCheck) brokenCheck[start] = false;
                    durability[start] = pickEggDurability;
                    durability[i] = targetEggDurability;
                }
            }
            if(!eggBroken)breakEgg(start + 1, totalBrokenEggCnt);
        }
        else breakEgg(start + 1, totalBrokenEggCnt);
    }
}
