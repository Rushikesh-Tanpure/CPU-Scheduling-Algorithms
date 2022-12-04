package CPU_Algorithms;

import java.util.*;

public class Priority_Scheduling_Preemtive {
	int length;
	int start;
	float AverageWaitingTime;
	float AverageTurnAroundTime;
	float AverageResponseTime;
	int totalBurstTime = 0;
	Vector gantt_Chart[];
	Object data[][];

	Priority_Scheduling_Preemtive(Object information[][]) {
		gantt_Chart = new Vector[4];
		for (int i = 0; i < 4; i++) {
			gantt_Chart[i] = new Vector();

		}
		data = information;
		length = data.length;
		Object copy_burst_times[] = new Object[length];
		for (int i = 0; i < length; i++) {
			copy_burst_times[i] = data[i][3];
		}
		sortbyColumn(data, 2);
		getComletionTime();
		sortbyColumn(data, 0);
		for (int i = 0; i < length; i++) {
			data[i][3] = copy_burst_times[i];
			if ((int) data[i][8] <= -1) {
				data[i][8] = 0;
			}
		}
		getTurnAroundTime();
		getTotalWaitingTime();
		AverageWaitingTime = getAverageTime(7);
		AverageTurnAroundTime = getAverageTime(6);
		AverageResponseTime = getAverageTime(8);
	}

	public void getTotalBurstTime() {
		for (int i = 0; i < length; i++) {
			totalBurstTime = totalBurstTime + (int) data[i][3];
		}
	}

	public void getComletionTime() {
		getTotalBurstTime();
		sortByPriorityTime();
		while (totalBurstTime > 0) {
			int count = 0;
			for (int i = 0; i < length; i++) {
				if ((int) data[i][2] <= start) {

				} else {
					count = 1;
					break;
				}
			}
			if ((int) data[0][3] == 0) {
				for (int i = 0; i < length; i++) {
					Object temp[] = data[0];
					data[0] = data[i];
					data[0] = temp;
				}
			}

			for (int i = 1; i < length; i++) {
				if (start >= (int) data[i][2] && ((int) data[0][4] > (int) data[i][4]) || (int) data[0][3] == 0) {
					Object temp[] = data[0];
					data[0] = data[i];
					data[i] = temp;
				}
			}
			if ((int) data[0][3] != 0) {
				if (count == 1) {
					if (start >= (int) data[0][2]) {
						int i = 0;
						int flag = 0;
						for (int k = 0; k < length; k++) {
							if ((int) data[k][3] == 0) {
							} else {
								flag++;
							}
						}
						if (flag == 1) {
							if ((int) data[i][8] == -1) {
								data[i][8] = start - (int) data[i][2];
							}
							if (gantt_Chart[0].size() >= 1
									&& gantt_Chart[3].lastElement().toString().equals(data[i][0].toString())) {
								gantt_Chart[2].remove(gantt_Chart[2].lastElement());
							} else {
								gantt_Chart[0].add(data[i][1]);
								gantt_Chart[1].add(start);
								gantt_Chart[3].add(data[i][0]);
							}
							totalBurstTime = totalBurstTime - (int) data[i][3];
							start = start + (int) data[i][3];
							data[i][3] = 0;
							gantt_Chart[2].add(start);
							data[i][5] = start;
						} else {
							if ((int) data[i][8] == -1) {
								data[i][8] = start - (int) data[i][2];
							}
							if (gantt_Chart[0].size() >= 1
									&& gantt_Chart[3].lastElement().toString().equals(data[i][0].toString())) {
								gantt_Chart[2].remove(gantt_Chart[2].lastElement());
							} else {
								gantt_Chart[0].add(data[i][1]);
								gantt_Chart[1].add(start);
								gantt_Chart[3].add(data[i][0]);
							}
							start = start + 1;
							data[i][3] = (int) data[i][3] - 1;
							gantt_Chart[2].add(start);
							totalBurstTime = totalBurstTime - 1;
							data[i][5] = start;
							gantt_Chart[3].add(data[i][0]);
						}
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
							gantt_Chart[3].add("Idle");
						}
					}
				} else {
					if (start >= (int) data[0][2]) {
						int i = 0;
						int flag = 0;
						for (int k = 0; k < length; k++) {
							if ((int) data[k][3] == 0) {
							} else {
								flag++;
							}
						}
						if (flag == 1) {
							if ((int) data[i][8] == -1) {
								data[i][8] = start - (int) data[i][2];
							}
							if (gantt_Chart[0].size() >= 1
									&& gantt_Chart[3].lastElement().toString().equals(data[i][0].toString())) {
								gantt_Chart[2].remove(gantt_Chart[2].lastElement());
							} else {
								gantt_Chart[0].add(data[i][1]);
								gantt_Chart[1].add(start);
								gantt_Chart[3].add(data[i][0]);
							}
							totalBurstTime = totalBurstTime - (int) data[i][3];
							start = start + (int) data[i][3];
							data[i][3] = 0;
							gantt_Chart[2].add(start);
							data[i][5] = start;
						} else {
							if ((int) data[i][8] == -1) {
								data[i][8] = start - (int) data[i][2];
							}
							if (gantt_Chart[0].size() >= 1
									&& gantt_Chart[3].lastElement().toString().equals(data[i][0].toString())) {
								gantt_Chart[2].remove(gantt_Chart[2].lastElement());
							} else {
								gantt_Chart[0].add(data[i][1]);
								gantt_Chart[1].add(start);
								gantt_Chart[3].add(data[i][0]);
							}
							start = start +(int) data[i][3];
							gantt_Chart[2].add(start);
							totalBurstTime = totalBurstTime - (int) data[i][3];
							data[i][5] = start;
							data[i][3] = 0;
							gantt_Chart[3].add(data[i][0]);
						}
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
							gantt_Chart[3].add("Idle");
						}
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

	public void sortByPriorityTime() {
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (data[i][2] == data[j][2]) {
					if ((int) data[i][4] < (int) data[j][4]) {
						Object temp[] = data[i];
						data[i] = data[j];
						data[j] = temp;
					}
				}
			}
		}

	}


}
