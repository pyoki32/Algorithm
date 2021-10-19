import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Sol_17479 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        HashMap<String , Long> normal = new HashMap<>();
        HashMap<String , Long> special = new HashMap<>();
        HashSet<String> service = new HashSet<>();

        for(int i=0;i<A;i++){
            st = new StringTokenizer(br.readLine());
            String normalMenu = st.nextToken();
            long price = Integer.parseInt(st.nextToken());
            normal.put(normalMenu,price);
        }
        for(int i=0;i<B;i++){
            st = new StringTokenizer(br.readLine());
            String specialMenu = st.nextToken();
            long price = Integer.parseInt(st.nextToken());
            special.put(specialMenu,price);
        }
        for(int i=0;i<C;i++){
            st = new StringTokenizer(br.readLine());
            String serviceMenu = st.nextToken();
            service.add(serviceMenu);
        }
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long normalCharge =0;
        long specialCharge =0;
        int serviceCnt=0;
        for(int i=0;i<N;i++){
            String order = br.readLine();
            if(normal.containsKey(order)){
                normalCharge += normal.get(order);
                continue;
            }
            if(special.containsKey(order)){
                specialCharge += special.get(order);
                continue;
            }
            if(service.contains(order)){
                serviceCnt++;
            }
        }
        boolean orderCheck = true;
        if(normalCharge<20000 && specialCharge >0){
            orderCheck = false;
        }
        if(normalCharge+specialCharge<50000 && serviceCnt>0){
            orderCheck = false;
        }
        if(serviceCnt>1){
            orderCheck=false;
        }

        if(orderCheck){
            System.out.println("Okay");
        }else{
            System.out.println("No");
        }
    }
}
