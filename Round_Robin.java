package CPU_Algorithms;

import java.util.*;

public class Round_Robin {
	int length;
	int start;
	int Time_Slice;
	float AverageWaitingTime;
	float AverageTurnAroundTime;
	float AverageResponseTime;
	int totalBurstTime = 0;
	Vector gantt_Chart[];
	Object data[][];
	public Stack<Integer> stack = new Stack<Integer>();

	Round_Robin(Object information[][], int Time_Slice) {
		this.Time_Slice = Time_Slice;
		gantt_Chart = new Vector[4];
		for (int i = 0; i < 4; i++) {
			gantt_Chart[i] = new Vector();

		}
		data = information;
		length = data.length;
		Object copy_burst_times[] = new Object[length];
		sortbyColumn(data, 2);
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

		checkQueue();
		while (totalBurstTime > 0) {
			if (stack.size() > 0) {
				int i = stack.elementAt(0);
				int flag = 0;
				if ((int) data[i][3] >= Time_Slice) {
					for (int k = 0; k < length; k++) {
						if ((int) data[k][3] == 0) {
						} else {
							flag++;
						}
					}
					if (stack.size() == 1 && flag == 1) {
						if ((int) data[i][8] == -1) {
							data[i][8] = start - (int) data[i][2];
						}
						if (gantt_Chart[0].size() >= 1 && gantt_Chart[0].lastElement().toString().equals(data[i][0].toString())) {
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
							checkQueue();

					} else {
						if ((int) data[i][8] == -1) {
							data[i][8] = start-(int)data[i][2];
						}
						if (gantt_Chart[0].size() >= 1 && gantt_Chart[3].lastElement().toString().equals(data[i][0].toString())) {
							gantt_Chart[2].remove(gantt_Chart[2].lastElement());
						} else {
							gantt_Chart[0].add(data[i][1]);
							gantt_Chart[1].add(start);
							gantt_Chart[3].add(data[i][0]);
						}
							start = start + Time_Slice;
							data[i][3] = (int) data[i][3] - Time_Slice;
							gantt_Chart[2].add(start);
							totalBurstTime = totalBurstTime - Time_Slice;
							data[i][5] = start;
						if ((int) data[i][3] == 0) {
							checkQueue();

						} else {
							checkQueue();
							int element = stack.elementAt(0);
							stack.remove(0);
							stack.push(element);
						}
					}

				} else {
					if ((int) data[i][8] == -1) {
						data[i][8] = start;
					}
					if (gantt_Chart[0].size() >= 1 && gantt_Chart[3].lastElement().toString().equals(data[i][0].toString())) {
						gantt_Chart[2].remove(gantt_Chart[2].lastElement());
					} else {
						gantt_Chart[0].add(data[i][1]);
						gantt_Chart[1].add(start);
						gantt_Chart[3].add(data[i][0]);
					}
					start = start + (int) data[i][3];
					totalBurstTime = totalBurstTime - (int) data[i][3];
					data[i][3] = 0;
					gantt_Chart[2].add(start);
					data[i][5] = start;
					checkQueue();

				}
			} else {
				if (gantt_Chart[0].size() >= 1 && gantt_Chart[0].lastElement().toString().equals("Idle")) {
					start = start + 1;
					gantt_Chart[2].remove(gantt_Chart[2].lastElement());
					gantt_Chart[2].add(start);
					checkQueue();
				} else {
					gantt_Chart[0].add("Idle");
					gantt_Chart[1].add(start);
					start = start + 1;
					gantt_Chart[2].add(start);
					gantt_Chart[3].add("Idle");
					checkQueue();
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

	public void checkQueue() {
		for (int i = 0; i < data.length; i++) {
			if (start >= (int) data[i][2]) {
				if (stack.search(i) == -1) {
					stack.push(i);
				}
			}
			if ((int) data[i][3] == 0) {
				Object o = i;
				stack.remove(o);
			}
		}
	}


}
