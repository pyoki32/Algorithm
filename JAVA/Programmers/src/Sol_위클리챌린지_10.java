import java.util.ArrayList;

public class Sol_위클리챌린지_10 {

    public static void main(String[] args) {
        int[][] line = {{2, -1, 4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}};
        String[] answer = solution(line);
        for (String str : answer) {
            System.out.println(str);
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static ArrayList<Point> points;
    static int maxX;
    static int minX;
    static int maxY;
    static int minY;

    public static String[] solution(int[][] line) {
        points = new ArrayList<>();
        maxX = Integer.MIN_VALUE;
        minX = Integer.MAX_VALUE;
        maxY = Integer.MIN_VALUE;
        minY = Integer.MAX_VALUE;
        for (int i = 0; i < line.length - 1; i++) {
            for (int j = i + 1; j < line.length; j++) {
                if (i != j) {
                    long A = line[i][0];
                    long B = line[i][1];
                    long E = line[i][2];
                    long C = line[j][0];
                    long D = line[j][1];
                    long F = line[j][2];
                    long denominator = A * D - B * C;
                    long xNumerator = B * F - E * D;
                    long yNumerator = E * C - A * F;
                    if (denominator == 0) continue;
                    if ((xNumerator % denominator) != 0 || (yNumerator % denominator) != 0) continue;
                    int x = (int) (xNumerator / denominator);
                    int y = (int) (yNumerator / denominator);
                    maxX = Math.max(maxX,x);
                    minX = Math.min(minX,x);
                    maxY = Math.max(maxY,y);
                    minY = Math.min(minY,y);
                    points.add(new Point(x, y));
                }
            }
        }
        String[] answer = printStart();
        return answer;
    }

    static String[] printStart() {

        int row = Math.abs(maxY - minY) + 1;
        int col = Math.abs(maxX - minX) + 1;
        boolean[][] starCheck = new boolean[row][col];
            for(Point p : points) {
                int x = p.x;
                int y = p.y;
                starCheck[maxY - y][x - minX] = true;
            }
        String[] map = new String[row];
        for (int i = 0; i < row; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < col; j++) {
                if (starCheck[i][j]) sb.append('*');
                else sb.append('.');
            }
            map[i] = sb.toString();
        }
        return map;
    }
}
