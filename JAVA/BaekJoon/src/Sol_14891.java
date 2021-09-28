import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_14891 {
    static int[][] gear;
    static int[] gearTopIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        gear = new int[4][8];
        for (int i = 0; i < 4; i++) {
            String Str = br.readLine();
            for (int j = 0; j < 8; j++) {
                gear[i][j] = Str.charAt(j) - '0';
            }
        }
        gearTopIdx = new int[4];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int gearNum = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            rotateGear(gearNum - 1, dir);
        }
        int point = 0;
        for (int i = 0; i < 4; i++) {
            if (gear[i][gearTopIdx[i]] == 1) point += Math.pow(2, i);
        }
        System.out.println(point);
    }

    static void rotateGear(int gearNum, int dir) {

        boolean[] rotability = new boolean[3];
        for (int i = 0; i < 3; i++) {
            int leftGearIdx = setIdx(gearTopIdx[i] + 2);
            int rightGearIdx = setIdx(gearTopIdx[i + 1] + 6);
            if (gear[i][leftGearIdx] != gear[i + 1][rightGearIdx]) {
                rotability[i] = true;
            }
        }
        gearTopIdx[gearNum] = setIdx(gearTopIdx[gearNum] - dir);

        int nDir = dir;
        switch (gearNum) {
            case 0:
                for (int i = 0; i < 3; i++) {
                    nDir = -nDir;
                    if (rotability[i]) {
                        gearTopIdx[i + 1] = setIdx(gearTopIdx[i + 1] - nDir);
                    } else break;
                }
                break;
            case 1:
                if (rotability[0]) {
                    gearTopIdx[0] = setIdx(gearTopIdx[0] + dir);
                }
                for (int i = 1; i < 3; i++) {
                    nDir = -nDir;
                    if (rotability[i]) {
                        gearTopIdx[i + 1] = setIdx(gearTopIdx[i + 1] - nDir);
                    } else break;
                }
                break;
            case 2:
                if (rotability[2]) {
                    gearTopIdx[3] = setIdx(gearTopIdx[3] + dir);
                }
                for (int i = 1; i >= 0; i--) {
                    nDir = -nDir;
                    if (rotability[i]) {
                        gearTopIdx[i] = setIdx(gearTopIdx[i] - nDir);
                    } else break;
                }
                break;
            case 3:
                for (int i = 2; i >= 0; i--) {
                    nDir = -nDir;
                    if (rotability[i]) {
                        gearTopIdx[i] = setIdx(gearTopIdx[i] - nDir);
                    } else break;
                }
                break;
        }

    }

    static int setIdx(int topIdx) {
        int idx = topIdx;
        if (idx > 7) idx -= 8;
        if (idx < 0) idx += 8;
        return idx;
    }
}
