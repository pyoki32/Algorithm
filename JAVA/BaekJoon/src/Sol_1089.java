import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sol_1089 {
    static char[][][] num = {
            {       {'#', '#', '#'},
                    {'#', '.', '#'},
                    {'#', '.', '#'},
                    {'#', '.', '#'},
                    {'#', '#', '#'},
            },
            {       {'.', '.', '#'},
                    {'.', '.', '#'},
                    {'.', '.', '#'},
                    {'.', '.', '#'},
                    {'.', '.', '#'},
            },
            {       {'#', '#', '#'},
                    {'.', '.', '#'},
                    {'#', '#', '#'},
                    {'#', '.', '.'},
                    {'#', '#', '#'},
            },
            {       {'#', '#', '#'},
                    {'.', '.', '#'},
                    {'#', '#', '#'},
                    {'.', '.', '#'},
                    {'#', '#', '#'},
            },
            {       {'#', '.', '#'},
                    {'#', '.', '#'},
                    {'#', '#', '#'},
                    {'.', '.', '#'},
                    {'.', '.', '#'},
            },
            {       {'#', '#', '#'},
                    {'#', '.', '.'},
                    {'#', '#', '#'},
                    {'.', '.', '#'},
                    {'#', '#', '#'},
            },
            {       {'#', '#', '#'},
                    {'#', '.', '.'},
                    {'#', '#', '#'},
                    {'#', '.', '#'},
                    {'#', '#', '#'},
            },
            {       {'#', '#', '#'},
                    {'.', '.', '#'},
                    {'.', '.', '#'},
                    {'.', '.', '#'},
                    {'.', '.', '#'},
            },
            {       {'#', '#', '#'},
                    {'#', '.', '#'},
                    {'#', '#', '#'},
                    {'#', '.', '#'},
                    {'#', '#', '#'},
            },
            {       {'#', '#', '#'},
                    {'#', '.', '#'},
                    {'#', '#', '#'},
                    {'.', '.', '#'},
                    {'#', '#', '#'},
            }

    };
    static char[][][] boardNum;
    static ArrayList<ArrayList<Integer>> possibleNums;
    static double totalCnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        boardNum = new char[N][5][3];
        possibleNums = new ArrayList<>();
        for (int r = 0; r < 5; r++) {
            String str = br.readLine();
            for (int n = 0; n < N; n++) {
                possibleNums.add(new ArrayList<>());
                for (int c = 0; c < 3; c++) {
                    boardNum[n][r][c] = str.charAt(4 * n + c);
                }
            }
        }
        totalCnt =1;
        for(int i=0;i<N;i++){
            findNum(i);
        }

        double answer =solveAnswer(N);
        if(answer == -1) System.out.println(-1);
        else System.out.printf("%f",answer);
    }
    static void findNum(int target){
        for(int n=0;n<10;n++){
            boolean numCheck =true;
        loop :for(int r=0;r<5;r++) {
            for (int c = 0; c < 3; c++) {
                if (boardNum[target][r][c] == '#' && num[n][r][c]=='.'){
                    numCheck=false;
                    break loop;
                }
            }
        }
        if(numCheck) possibleNums.get(target).add(n);
        }
        totalCnt *= possibleNums.get(target).size();
    }
    static double solveAnswer(int N){
        double res =0;

        for(int n=0;n< N;n++){
            ArrayList<Integer> nums = possibleNums.get(n);
            if(nums.size()==0) return -1;
                for(int num : nums){
                    if(num!=0)  res += num*Math.pow(10,(N-1)-n)*(totalCnt/(double)possibleNums.get(n).size());
                }
        }
        res /=totalCnt;
        return res;
    }
}

