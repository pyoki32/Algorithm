
public class Sol_광고_삽입 {

	static int play_time_s;
	static int[] hit_time;

	public static void main(String[] args) {

		String play_time = "02:03:55";
		String adv_time = "00:14:15";
		String[] logs = { "01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29",
				"01:37:44-02:02:30" };
		String answer = solution(play_time, adv_time, logs);
		System.out.println(answer);

	}

	public static String solution(String play_time, String adv_time, String[] logs) {
		String answer = "";
		if (play_time.equals(adv_time)) {
			return "00:00:00";
		}
		play_time_s = calcul_seconds(play_time);
		hit_time = new int[play_time_s + 1];

		for (int i = 0; i < logs.length; i++) {
			String[] logTime = logs[i].split("-");
			int start = calcul_seconds(logTime[0]);
			int end = calcul_seconds(logTime[1]);

			for (int j = start; j < end; j++) {
				hit_time[j]++;
			}
		}

		int adv_time_s = calcul_seconds(adv_time);
		int answerStart = 0;
		int hit_cnt = 0;
		int front = 0;
		int back = adv_time_s - 1;
		int MAX = Integer.MIN_VALUE;
		for (int i = 0; i < adv_time_s; i++) {
			hit_cnt += hit_time[i];
		}
		if (MAX < hit_cnt) {
			MAX=hit_cnt;
			answerStart = front;

		}

		while (back < play_time_s) {
			hit_cnt -= hit_time[front];
			front++;
			back++;
			hit_cnt += hit_time[back];

			if (MAX < hit_cnt) {
				MAX = hit_cnt;
				answerStart = front;

			}

		}

		int hour = answerStart / (60 * 60);
		int minute = answerStart / 60 - (hour * 60);
		int second = answerStart % 60;
		String hourStr = Integer.toString(hour);
		if (hourStr.length() == 1)
			hourStr = "0" + hourStr;
		String minuteStr = Integer.toString(minute);
		if (minuteStr.length() == 1)
			minuteStr = "0" + minuteStr;
		String secondStr = Integer.toString(second);
		if (secondStr.length() == 1)
			secondStr = "0" + secondStr;

		answer = hourStr + ":" + minuteStr + ":" + secondStr;
		return answer;
	}

	public static int calcul_seconds(String time) {

		String[] seconds = time.split(":");
		int cal_hour = Integer.parseInt(seconds[0]);
		int cal_minute = Integer.parseInt(seconds[1]);
		int cal_seconds = Integer.parseInt(seconds[2]);

		int res = cal_hour * 3600 + cal_minute * 60 + cal_seconds;

		return res;
	}
}
