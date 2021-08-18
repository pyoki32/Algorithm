import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_17609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < T; i++) {
            String str = br.readLine();
            int leftIdx = 0;
            int rightIdx = str.length()-1;
            int pseudo =0;
            while(leftIdx<=rightIdx){
                char lChar = str.charAt(leftIdx);
                char rChar = str.charAt(rightIdx);
                if(lChar == rChar){
                    leftIdx++;
                    rightIdx--;
                }
                else{
                    if(lChar == str.charAt(rightIdx-1) && rChar != str.charAt(leftIdx+1)){
                        rightIdx--;
                        leftIdx++;
                        rightIdx--;
                        pseudo++;
                    }
                    else if(rChar == str.charAt(leftIdx+1) && lChar != str.charAt(rightIdx-1) ){
                        leftIdx++;
                        leftIdx++;
                        rightIdx--;
                        pseudo++;
                    }else if(rChar == str.charAt(leftIdx+1) && lChar == str.charAt(rightIdx-1) ){
                            if(reCheck(str,leftIdx, rightIdx-1) || reCheck(str,leftIdx+1,rightIdx) ) pseudo ++;
                            else pseudo=2;
                        break;
                    }
                    else{
                        pseudo = 2;
                        break;
                    }
                }
            }
            if(pseudo>1)sb.append(2).append('\n');
            else sb.append(pseudo).append('\n');
        }
        System.out.println(sb);
    }
    public static  boolean reCheck( String str ,int n_leftIdx, int n_rightIdx){
        boolean res = true;
        int leftIdx = n_leftIdx;
        int rightIdx = n_rightIdx;

        while(leftIdx<=rightIdx){
            char lChar = str.charAt(leftIdx);
            char rChar = str.charAt(rightIdx);
            if(lChar == rChar){
                leftIdx++;
                rightIdx--;
            }
            else{
                return false;
            }
        }
        return res;
    }
}
