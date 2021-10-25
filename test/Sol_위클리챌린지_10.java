import java.util.PriorityQueue;

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

    static PriorityQueue<Point> maxXpq = new PriorityQueue<>((Point p1, Point p2) -> p1.x < p2.x ? 1 : -1);
    static PriorityQueue<Point> maxYpq = new PriorityQueue<>((Point p1, Point p2) -> p1.y < p2.y ? 1 : -1);
    static PriorityQueue<Point> minXpq = new PriorityQueue<>((Point p1, Point p2) -> p1.x > p2.x ? 1 : -1);
    static PriorityQueue<Point> minYpq = new PriorityQueue<>((Point p1, Point p2) -> p1.y > p2.y ? 1 : -1);

    public static String[] solution(int[][] line) {
        for (int i = 0; i < line.length - 1; i++) {
            for (int j = i + 1; j < line.length; j++) {
                if (i != j) {
                    long A = line[i][0];
                    long B = line[i][1];
                    long E = line[i][2];
                    long C = line[j][0];
                    long D = line[j][1];
                    long F = line[j][2];
                    long denominator= A * D - B * C;
                    long xNumerator = B * F - E * D;
                    long yNumerator = E * C - A * F;
                    if(denominator == 0) continue;
                    if((xNumerator%denominator) !=0 ||(yNumerator%denominator) !=0) continue;
                        int x = (int)(xNumerator / denominator);
                        int y = (int)(yNumerator / denominator);
                        maxXpq.add(new Point(x, y));
                        maxYpq.add(new Point(x, y));
                        minXpq.add(new Point(x, y));
                        minYpq.add(new Point(x, y));
                }
            }
        }
        String[] answer = printStart();
        return answer;
    }

    static String[] printStart() {

        int maxX = maxXpq.peek().x;
        int minX = minXpq.peek().x;
        int maxY = maxYpq.peek().y;
        int minY = minYpq.peek().y;
        int row = Math.abs(maxY - minY) + 1;
        int col = Math.abs(maxX - minX) + 1;

        boolean[][] starCheck = new boolean[row][col];

        while (!maxXpq.isEmpty()) {
            Point p = maxXpq.poll();
            int x = p.x;
            int y = p.y;
            if (maxX == x) starCheck[maxY - y][x - minX] = true;
            else break;
        }
        while (!minXpq.isEmpty()) {
            Point p = minXpq.poll();
            int x = p.x;
            int y = p.y;
            if (minX == x) starCheck[maxY - y][x - minX] = true;
            else break;
        }
        while (!maxYpq.isEmpty()) {
            Point p = maxYpq.poll();
            int x = p.x;
            int y = p.y;
            if (maxY == y) starCheck[maxY - y][x - minX] = true;
            else break;
        }
        while (!minYpq.isEmpty()) {
            Point p = minYpq.poll();
            int x = p.x;
            int y = p.y;
            if (minY == y) starCheck[maxY - y][x - minX] = true;
            else break;
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
