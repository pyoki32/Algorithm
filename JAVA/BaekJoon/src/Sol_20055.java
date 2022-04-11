    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.Deque;
    import java.util.LinkedList;
    import java.util.StringTokenizer;

    public class Sol_20055 {
        static class Belt{
            int durability;
            boolean robot;
            public Belt(int durability,boolean robot){
                this.durability=durability;
                this.robot = robot;
            }
        }

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int [] upBelt = new int [N];
            boolean [] robots = new boolean[N];
            Deque<Belt> dq = new LinkedList<>();

            st = new StringTokenizer(br.readLine());
            for(int i=0;i<2*N;i++){
                int a =  Integer.parseInt(st.nextToken());
                //init
                if(0<=i && i<N) upBelt[i] = a;
                dq.addLast(new Belt(a,false));
            }
            int time = 1;
            while(K>0){
                //벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
                dq.addFirst(dq.pollLast());
                for(int i=0;i<N;i++){
                    Belt belt = dq.pollFirst();
                    int  durability = belt.durability;
                    boolean robot = belt.robot;
                    upBelt[i] = durability;
                    robots[i] = robot;
                    //언제든지 로봇이 내리는 위치에 도달하면 그 즉시 내린다.
                    if(i == N-1 ) robots[i] = false;
                }
                for(int i = N-2; i >0 ; i-- ){
                        if( upBelt[i+1] > 0 && robots[i] && !robots[i+1] ){
                            upBelt[i+1]--;
                            robots[i]  = false;
                            robots[i+1] = true;
                        }
                }
                //언제든지 로봇이 내리는 위치에 도달하면 그 즉시 내린다.
                robots[N-1]=false;

                //올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린
                if(upBelt[0] > 0) {
                    upBelt[0]--;
                    robots[0] = true;
                }

                int zeroCnt =0;
                for(int i=0;i<N;i++){
                    Belt belt = dq.pollFirst();
                    if(belt.durability == 0) zeroCnt++;
                    dq.addLast(belt);
                }
                for(int i = N-1; i >= 0 ; i-- ){
                    if(upBelt[i]==0) zeroCnt++;
                    dq.addFirst(new Belt(upBelt[i],robots[i]));
                }
                if(zeroCnt >= K) break;
                time++;
            }
            System.out.println(time);
        }
    }
