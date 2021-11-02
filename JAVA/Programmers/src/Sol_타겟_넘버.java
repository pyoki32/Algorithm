public class Sol_타겟_넘버 {
    static int totalCnt;

    public int solution(int[] numbers, int target) {
        int answer = 0;
        totalCnt = 0;
        dfs(numbers, target, 0, 0);
        answer = totalCnt;
        return answer;
    }

    static void dfs(int[] numbers, int target, int num, int depth) {
        if (depth > numbers.length-1) {
            if (num == target) totalCnt++;
            return;
        }
        dfs(numbers, target, num + numbers[depth], depth + 1);
        dfs(numbers, target, num - numbers[depth], depth + 1);
    }
}
