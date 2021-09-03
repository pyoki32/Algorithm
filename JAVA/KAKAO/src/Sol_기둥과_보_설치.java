import java.util.ArrayList;

public class Sol_기둥과_보_설치 {
    static boolean[][][] pillar;
    static boolean[][][] paper;

    static class Frame {
        int x;
        int y;
        int a;

        public Frame(int x, int y, int a) {
            this.x = x;
            this.y = y;
            this.a = a;
        }

    }

    public static void main(String[] args) {

        int n = 5;
        int[][] build_frame = {{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {2, 1, 1, 1}, {3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}};

        int[][] answer = solution(n, build_frame);
        System.out.println(answer);


    }

    public static int[][] solution(int n, int[][] build_frame) {
        pillar = new boolean[n + 1][n + 1][2];
        paper = new boolean[n + 1][n + 1][2];
        for (int i = 0; i < build_frame.length; i++) {
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            int a = build_frame[i][2];
            int b = build_frame[i][3];

            if (b == 0) delPillarPaper(n, x, y, a);
            else setPillarPaper(n, x, y, a);
        }
        ArrayList<Frame> res = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (pillar[j][i][1]) {
                    res.add(new Frame(i, j, 0));
                }
                if (paper[j][i][1]) {
                    res.add(new Frame(i, j, 1));
                }
            }
        }

        int[][] answer = new int[res.size()][3];
        for (int i = 0; i < answer.length; i++) {
            Frame fr = res.get(i);
            answer[i][0] = fr.x;
            answer[i][1] = fr.y;
            answer[i][2] = fr.a;
            System.out.println(answer[i][0] + " " + answer[i][1] + " " + answer[i][2]);
        }
        return answer;
    }

    static void setPillarPaper(int n, int x, int y, int a) {

        switch (a) {
            case 0:
                if (isRange(n, x, (y + 1))) {
                    if (y == 0 || paper[y][x][0] || paper[y][x][1]
                            || pillar[y][x][0] || pillar[y][x][1]) {
                        pillar[y][x][1] = true;
                        pillar[y + 1][x][0] = true;
                    }
                }
                break;
            case 1:
                if (isRange(n, (x + 1), y)) {
                    if (pillar[y][x][0] || pillar[y][x + 1][0]
                            || (paper[y][x][0] && paper[y][x + 1][1])) {
                        paper[y][x][1] = true;
                        paper[y][x + 1][0] = true;
                    }
                }
                break;
        }

    }

    static void delPillarPaper(int n, int x, int y, int a) {
        boolean delPossible = true;
        switch (a) {
            case 0:
                if (isRange(n, (x + 1), (y + 1))) {
                    if (paper[y + 1][x][1] && !pillar[x + 1][y + 1][0]) {
                        delPossible = false;
                    }
                }
                if (isRange(n, (x - 1), (y + 1))) {
                    if (paper[y + 1][x][0] && !pillar[x - 1][y + 1][0]) {
                        delPossible = false;
                    }
                }
                if(paper[y+1][x][0]&&paper[y+1][x][1]) delPossible =true;
                if (delPossible) {
                    pillar[y][x][1] = false;
                    pillar[y + 1][x][0] = false;
                }
                break;
            case 1:
                if (isRange(n, (x + 1), y)) {
                    if (pillar[y][x][1] || pillar[y][x + 1][1]) {
                        delPossible = false;
                    }
                }
                if (isRange(n, (x + 2), y)) {
                    if (paper[y][x + 1][1] && !pillar[y][x + 1][0] && !pillar[y][x + 2][0]) {

                        delPossible = false;
                    }
                }
                if (isRange(n, (x - 1), y)) {
                    if (paper[y][x][0] && !pillar[y][x][0] && !pillar[y][x - 1][0]) {

                        delPossible = false;
                    }
                }
                if (delPossible) {
                    paper[y][x][1] = false;
                    paper[y][x + 1][0] = false;
                }
                break;
        }
    }

    static boolean isRange(int n, int x, int y) {
        if (0 <= y && y <= n && 0 <= x && x <= n) return true;
        return false;
    }

    static void print(int n, boolean[][][] map) {
        System.out.println("00000000000000000000000000000000000000");
        for (int i = 0; i <=        n; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(map[i][j][0] + " ");
            }
            System.out.println();
        }

        System.out.println("11111111111111111111111111111111111111111111");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(map[i][j][1] + " ");
            }
            System.out.println();
        }
    }
}
