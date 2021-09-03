public class Sol_위클리챌린지_2 {
    public static void main(String[] args) {
        int[][] scores = {{100,90,98,88,65},{50,45,99,85,77},{47,88,95,80,67},{61,57,100,80,65},{24,90,94,75,65}};
        String res =solution(scores);
        System.out.println(res);
    }
    public static String solution(int[][] scores) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<scores.length;i++){
            int maxScore = Integer.MIN_VALUE;
            int minScore = Integer.MAX_VALUE;
            for(int j=0;j<scores.length;j++){
                maxScore = Math.max(maxScore,scores[j][i]);
                minScore = Math.min(minScore,scores[j][i]);
            }
            int sum =0;
            int maxCnt=0;
            int minCnt=0;
            for(int k=0;k<scores.length;k++){
                sum += scores[k][i];
                if(scores[k][i]==maxScore) maxCnt++;
                if(scores[k][i]==minScore) minCnt++;
            }
            boolean delcheck = false;
            if((scores[i][i]==maxScore && maxCnt==1)||(scores[i][i]==minScore && minCnt==1) ){
                sum -= scores[i][i];
                delcheck = true;
            }
            int avg =0;
            if(delcheck) avg = sum/(scores.length-1);
            else avg = sum/scores.length;
            char degree = getDegree(avg);
            sb.append(degree);
        }
        answer = sb.toString();
        return answer;
    }
    static Character getDegree(int avg){
        char degree ='X';
        if(avg>=90) degree = 'A';
        else if(80<=avg && avg <90) degree = 'B';
        else if(70<=avg && avg <80) degree = 'C';
        else if(50<=avg && avg <70) degree = 'D';
        else degree ='F';
        return degree;
    }
}
