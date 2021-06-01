import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_13335 {

	static class Truck {
		int weight;
		int length;
		int time;

		public Truck(int weight, int length, int time) {
			this.weight = weight;
			this.length = length;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int [] truck_weights = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			truck_weights[i]=Integer.parseInt(st.nextToken());
		}
		System.out.println(solution(w,L,truck_weights));
		
	}

	public static int solution(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;
		Queue<Truck> waitingTrucks = new LinkedList<>();
		for (int i = 0; i < truck_weights.length; i++) {
			waitingTrucks.add(new Truck(truck_weights[i], 0, 0));
		}

		Queue<Truck> bridge = new LinkedList<>();
		int tempWeight = 0;
		int truckCnt = 0;
		int time = 0;

		while (true) {
			time++;

			if (!waitingTrucks.isEmpty()) {
				if (tempWeight + waitingTrucks.peek().weight <= weight && truckCnt + 1 <= bridge_length) {
					Truck bwt = waitingTrucks.poll();
					tempWeight += bwt.weight;
					truckCnt++;
					bridge.add(new Truck(bwt.weight, bwt.length + 1, time));
					// System.out.println(" weg->"+bwt.weight+" len->"+(bwt.length+1)+"
					// time->"+time);
				}
			}

			while (!bridge.isEmpty()) {
				if (time == bridge.peek().time) {
					Truck wt = bridge.poll();
					if (wt.length == bridge_length) {
						tempWeight -= wt.weight;
						truckCnt--;
					} else {
						bridge.add(new Truck(wt.weight, wt.length + 1, time + 1));
					}
				} else {
					break;
				}
			}

			if (waitingTrucks.isEmpty() && bridge.isEmpty())
				break;

		}

		answer = time + 1;
		return answer;
	}

}
