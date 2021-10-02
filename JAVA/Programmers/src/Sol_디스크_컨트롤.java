import java.util.*;

class Solution {
    static class Job{
        int request;
        int progress;
        public Job(int request,int progress){
            this.request=request;
            this.progress=progress;
        }
    }
    public int solution(int[][] jobs) {
        
        
        PriorityQueue<Job> pqRequest = new PriorityQueue<>(new Comparator<Job>(){
           @Override
            public int compare(Job j1 , Job j2){
                return j1.request - j2.request;
            }
        });
        
        for(int i=0;i<jobs.length;i++){
            pqRequest.add(new Job(jobs[i][0],jobs[i][1]));
        }
        
        
        PriorityQueue<Job> pqProgress = new PriorityQueue<>(new Comparator<Job>(){
           @Override
            public int compare(Job j1 , Job j2){
                return j1.progress - j2.progress;
            }
        });

        int time=0;
        int totalTime =0;
        
        while(true){
            if(pqRequest.isEmpty() && pqProgress.isEmpty() ) break;
            while(!pqRequest.isEmpty()){
            if(pqRequest.peek().request<=time)pqProgress.add(pqRequest.poll());
            else break;
            }
            
            if(!pqProgress.isEmpty()){
                Job job = pqProgress.poll();
                time += job.progress;
                totalTime +=  time-job.request;    
            }else time++;     
        }
     
        int answer = (totalTime)/jobs.length;
        return answer;
    }
}