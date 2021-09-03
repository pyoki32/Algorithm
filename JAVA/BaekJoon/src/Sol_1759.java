import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_1759 {
    static int L,C;
    static char [] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[C];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<arr.length;i++){
            arr[i]= st.nextToken().charAt(0);
        }
        Arrays.sort(arr);
        int [] pick = new int[L];
        comb(pick,0,0);
        System.out.println(sb);
    }
    static void comb(int [] pick,int start , int depth){
        if(depth>L-1){
            if(passwordCheck(pick)){
                for(int k: pick){
                    sb.append(arr[k]);
                }
                sb.append('\n');
            }
            return;
        }
        for(int i = start ; i<C;i++){
            pick[depth]= i;
            comb(pick,i+1,depth+1);
        }
    }
    static boolean passwordCheck(int [] pick){
        int vowel =0;
        int consonant=0;
        for(int i=0;i<pick.length;i++){
            if(arr[pick[i]]=='a' || arr[pick[i]]=='e'  || arr[pick[i]]=='i' || arr[pick[i]]=='o'||arr[pick[i]]=='u'){
                vowel++;
            }else consonant++;
        }
        if(vowel>=1 && consonant>=2) return true;
        return false;
    }
}
