import java.util.*;

public class Sol_외벽_점검 {
    static class Section{
        int start;
        int end;
        int dist;
        int cnt;
        int dir;
        public Section(int start, int end,int dist,int cnt ,int dir){
            this.start=start;
            this.end =end;
            this.dist =dist;
            this.cnt = cnt;
            this.dir = dir;
        }
    }
    static PriorityQueue<Section> scq = new PriorityQueue<>(new Comparator<Section>() {
        @Override
        public int compare(Section s1, Section s2) {
            if(s1.cnt==s2.cnt)return -(s1.dist - s2.dist);
            return -(s1.cnt-s2.cnt);
        }
    });
    public static void main(String[] args) {
       int n = 50;
       int [] weak = {1};
       int [] dist = {6};
        System.out.println(solution(n,weak,dist));


    }
    public static int solution(int n, int[] weak, int[] dist) {
        int answer = 0;
        int [] pick = new int [2];
        for(int i=0;i<weak.length;i++){
            scq.add(new Section(i,i,1,1,1));
        }
        pickIdx(pick,weak,0,0,n);

        int distIdx= dist.length-1;
        int repairCnt= 0;
        int personCnt=0;

        boolean [] visited = new boolean[weak.length];
        while(!scq.isEmpty()){
            Section sc = scq.poll();
            if(sc.dist<=dist[distIdx]){
                if(rangeCheck(visited,sc)){
                    repairCnt+= sc.cnt;
                    personCnt++;
                    distIdx--;
                }
            }
            if(repairCnt == weak.length) break;
            if(distIdx==-1)break;
        }

        if(repairCnt<weak.length) answer =-1;
        else answer = personCnt;
        return answer;
    }
    static void pickIdx(int [] pick ,int [] weak,int start,int depth ,int n){
        if(depth ==2){
            int rightDist =weak[pick[1]]-weak[pick[0]]+1;
            int rightCnt =pick[1]-pick[0]+1;
            int leftDist = n-rightDist +1;
            int leftCnt = (pick[0]+1)+(weak.length-pick[1]);
            scq.add(new Section(pick[0],pick[1],rightDist,rightCnt,1));
            scq.add(new Section(pick[0],pick[1],leftDist,leftCnt,-1));
            return;
        }
        for(int i=start;i<weak.length;i++ ){
            pick[depth]=i;
            pickIdx(pick,weak,(i+1),(depth+1),n);
        }
    }
    static boolean rangeCheck(boolean [] visited ,Section inputSection){
        int start = inputSection.start;
        int end = inputSection.end;
        int dir = inputSection.dir;

        if(dir == 1){
            for(int i=start ;i<=end;i++){
                if(visited[i]) return false;
            }
            for(int i=start ;i<=end;i++) {
                visited[i] = true;
            }
        }else{
            for(int i=0;i<=start;i++){
                if(visited[i]) return false;
            }
            for(int i=end;i<visited.length;i++){
                if(visited[i]) return false;
            }

            for(int i=0;i<=start;i++){
                visited[i] = true;
            }
            for(int i=end;i<visited.length;i++){
                visited[i] = true;
            }
        }

        return true;
    }
}
