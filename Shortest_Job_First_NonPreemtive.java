package CPU_Algorithms;

import java.util.*;

public class Shortest_Job_First_NonPreemtive {
	int length;
	int start;
	float AverageWaitingTime;
	float AverageTurnAroundTime;
	float AverageResponseTime;
	int totalBurstTime = 0;
	Vector gantt_Chart[];
	Object data[][];

	Shortest_Job_First_NonPreemtive(Object information[][]) {
		gantt_Chart = new Vector[4];
		for (int i = 0; i < 4; i++) {
			gantt_Chart[i] = new Vector();
		}

		data = information;
		length = data.length;
		Object copy_burst_times[] = new Object[length];
		sortbyColumn(data, 2);
		sortByBurstTime();
		for (int i = 0; i < length; i++) {
			copy_burst_times[i] = data[i][3];
		}

		getComletionTime();
		for (int i = 0; i < length; i++) {
			data[i][3] = copy_burst_times[i];
			if ((int) data[i][8] <= -1) {
				data[i][8] = 0;
			}
		}
		getTurnAroundTime();
		getTotalWaitingTime();
		getTotaResponseTime();

		AverageWaitingTime = getAverageTime(7);
		AverageTurnAroundTime = getAverageTime(6);
		AverageResponseTime = getAverageTime(8);

		sortbyColumn(data, 0);
	}

	public void getTotalBurstTime() {
		for (int i = 0; i < length; i++) {
			totalBurstTime = totalBurstTime + (int) data[i][3];
		}
	}

	public void getComletionTime() {
		getTotalBurstTime();
		int flag = 0;
		while (totalBurstTime > 0) {
			for (int i = 0; i < length; i++) {
				if (start >= (int) data[i][2]) {
					if ((int) data[i][3] == 0) {

					} else {
						gantt_Chart[0].add(data[i][1]);
						gantt_Chart[1].add(start);
						start = start + (int) data[i][3];
						gantt_Chart[2].add(start);
						totalBurstTime = totalBurstTime - (int) data[i][3];
						data[i][5] = start;
						data[i][3] = 0;
					}
				} else {
					flag = 1;
				}
			}
			if (flag == 0) {

			} else {
				if (totalBurstTime == 0) {

				} else {
					if (gantt_Chart[0].size() >= 1 && gantt_Chart[0].lastElement().toString().equals("Idle")) {
						start = start + 1;
						gantt_Chart[2].remove(gantt_Chart[2].lastElement());
						gantt_Chart[2].add(start);
					} else {
						gantt_Chart[0].add("Idle");
						gantt_Chart[1].add(start);
						start = start + 1;
						gantt_Chart[2].add(start);
						;
					}
				}
			}
		}

	}

	public void getTurnAroundTime() {
		for (int i = 0; i < length; i++) {
			if ((int) data[i][3] == 0) {
			} else {
				data[i][6] = (int) data[i][5] - (int) data[i][2];
			}
		}
	}

	public void getTotalWaitingTime() {
		for (int i = 0; i < length; i++) {
			if ((int) data[i][3] == 0) {
			} else {
				data[i][7] = (int) data[i][6] - (int) data[i][3];
			}
		}
	}

	public void getTotaResponseTime() {
		for (int i = 0; i < length; i++) {
			if ((int) data[i][3] == 0) {
			} else {
				data[i][8] = (int) data[i][7];
			}
		}
	}

	public float getAverageTime(int j) {
		float averageTime = 0;
		for (int i = 0; i < length; i++) {
			averageTime = averageTime + (int) data[i][j];
		}
		averageTime = averageTime / length;
		return averageTime;
	}

	public void sortbyColumn(Object arr[][], int col) {

		Arrays.sort(arr, new Comparator<Object[]>() {

			@Override
			public int compare(final Object[] entry1, final Object[] entry2) {

				if ((int) (entry1[col]) >= (int) (entry2[col]))
					return 1;
				else
					return -1;
			}
		});
	}

	public void sortByBurstTime() {
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (data[i][2] == data[j][2]) {
					if ((int) data[i][3] < (int) data[j][3]) {
						Object temp[] = data[i];
						data[i] = data[j];
						data[j] = temp;
					}
				}
			}
		}
		int counter = (int) data[0][3];
		for (int i = 1; i < length; i++) {
			int burst = 0;
			if (counter >= (int) data[i][2]) {
				int flag = 0;
				for (int m = i; m < length; m++) {
					if ((int) data[i][3] > (int) data[m][3]) {
						Object temp[] = data[i];
						data[i] = data[m];
						data[m] = temp;
						burst = (int) data[m][3];
						flag = 1;
					} else {

					}
				}
				if (flag == 1) {
					counter = counter + burst;
				} else {
					counter = counter + (int) data[i][3];
				}
			} else {
				counter++;
			}
		}

	}
}
