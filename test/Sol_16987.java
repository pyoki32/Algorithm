import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static boolean[] hitCheck;
    static boolean[] brokenCheck;
    static int[] durability;
    static int[] weight;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        hitCheck = new boolean[N];
        brokenCheck = new boolean[N];
        durability = new int[N];
        weight = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            durability[i] = Integer.parseInt(st.nextToken());
            weight[i] = Integer.parseInt(st.nextToken());
        }
        answer =Integer.MIN_VALUE;
        breakEgg(0,0);
        System.out.println(answer);
    }
    static void breakEgg(int start,int totalBrokenEggCnt){
        if(start>=N){
            answer = Math.max(answer, totalBrokenEggCnt);
            return;
        }
        if(!brokenCheck[start]) {
            for (int i = 0; i < N; i++) {
                if (!brokenCheck[i] && start!=i) {
                    int brokenEggCnt= 0;
                    boolean pickBreakCheck = false;
                    boolean targetBreakCheck =false;
                    int pickEggDurability = durability[start];
                    int pickEggWeight = weight[start];
                    int targetEggDurability = durability[i];
                    int targetEggWeight = weight[i];

                    if ((pickEggDurability - targetEggWeight) <= 0) {
                        brokenCheck[start] = true;
                        pickBreakCheck = true;
                        brokenEggCnt++;
                    }
                    if ((targetEggDurability - pickEggWeight) <= 0) {
                        brokenCheck[i] = true;
                        targetBreakCheck =true;
                        brokenEggCnt++;
                    }
                    durability[start] = pickEggDurability - targetEggWeight;
                    durability[i] = targetEggDurability - pickEggWeight;
                    breakEgg(start+1,totalBrokenEggCnt+brokenEggCnt);
                    durability[start] = pickEggDurability;
                    durability[i] = targetEggDurability;
                    if(pickBreakCheck)brokenCheck[start]=false;
                    if(targetBreakCheck)brokenCheck[i]=false;
                }
            }
        }
        breakEgg(start+1,totalBrokenEggCnt);
    }
}

