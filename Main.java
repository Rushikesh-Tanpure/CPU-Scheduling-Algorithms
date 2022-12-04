package CPU_Algorithms;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class Main extends JFrame {
	public JFrame frame;
	public JComboBox algorithm_combobox;
	public JPanel main_panel, main_panel_Information, main_panel_getInformation_scrollpanel, result_panel, gantt_chart;
	public JTextField no_of_processes_textfield;
	public CardLayout card;
	public JScrollPane scrollPane;
	public int no_of_processes;
	public int algorithm;
	public int error_count;
	public int Time_Slice;
	public GetDataPanel main_panel_getInformation[];
	public JTable result_table;
	public JPanel result_table_panel;
	public JTextField txtWaiting;
	public JTextField txtTurn;
	public JTextField txtResponse;
	public JTextField txtAlgorithm;
	public GanttChart gantt_chart_info[];
	public JTextField time_slice_textfield;
	public JLabel time_slice_label;
	public JLabel error_1;
	public JLabel time_slice_result_label;
	public JComboBox process_type_combobox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {

		frame = this;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 19, 1185, 707);
		this.setResizable(false);
		this.setMaximizedBounds(getBounds());
		card = new CardLayout();

		getContentPane().setLayout(card);

		// main panel
		main_panel = new JPanel();
		main_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		main_panel.setLayout(null);
		getContentPane().add(main_panel, "1");

		JLabel heading_label = new JLabel("CPU Scheduling Algorithms");
		heading_label.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 40));
		heading_label.setBounds(310, 0, 583, 78);
		main_panel.add(heading_label);

		JLabel algorithms_panel = new JLabel("Algorithms:");
		algorithms_panel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 34));
		algorithms_panel.setBounds(210, 205, 209, 40);
		main_panel.add(algorithms_panel);

		algorithm_combobox = new JComboBox();
		algorithm_combobox.setModel(new DefaultComboBoxModel(
				new String[] { "First Come First Serve", "Shortest Job First", "Round Robin", "Priority Scheduling" }));
		algorithm_combobox.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
		algorithm_combobox.setBounds(618, 211, 390, 34);
		main_panel.add(algorithm_combobox);

		JLabel no_of_processes_label = new JLabel("No. of Processes:");
		no_of_processes_label.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 34));
		no_of_processes_label.setBounds(210, 476, 295, 40);
		main_panel.add(no_of_processes_label);

		no_of_processes_textfield = new JTextField();
		no_of_processes_textfield.setHorizontalAlignment(SwingConstants.CENTER);
		no_of_processes_textfield.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
		no_of_processes_textfield.setBounds(625, 485, 155, 27);
		main_panel.add(no_of_processes_textfield);
		no_of_processes_textfield.setColumns(10);

		JButton exit_button = new JButton("EXIT");
		exit_button.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		exit_button.setBounds(232, 581, 89, 34);
		main_panel.add(exit_button);

		JButton clear_button = new JButton("CLEAR");
		clear_button.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		clear_button.setBounds(524, 581, 104, 34);
		main_panel.add(clear_button);

		JButton next_button = new JButton("NEXT");
		next_button.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		next_button.setBounds(814, 581, 89, 34);
		main_panel.add(next_button);

		JLabel error = new JLabel("");
		error.setFont(new Font("Tahoma", Font.PLAIN, 12));
		error.setForeground(Color.RED);
		error.setBounds(635, 523, 251, 15);
		main_panel.add(error);

		JLabel process_type_label = new JLabel("Process Type:");
		process_type_label.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 34));
		process_type_label.setBounds(210, 332, 295, 40);
		main_panel.add(process_type_label);

		process_type_combobox = new JComboBox();
		process_type_combobox.setModel(new DefaultComboBoxModel(new String[] { "Preemtive", "Non_Preemtive" }));
		process_type_combobox.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
		process_type_combobox.setSelectedIndex(1);
		process_type_combobox.setEnabled(false);
		process_type_combobox.setEditable(false);
		process_type_combobox.setBounds(618, 332, 301, 34);
		main_panel.add(process_type_combobox);

		time_slice_label = new JLabel("Time Slice:");
		time_slice_label.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 34));
		time_slice_label.setBounds(210, 406, 295, 40);

		time_slice_textfield = new JTextField();
		time_slice_textfield.setHorizontalAlignment(SwingConstants.CENTER);
		time_slice_textfield.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
		time_slice_textfield.setColumns(10);
		time_slice_textfield.setBounds(625, 415, 155, 27);

		error_1 = new JLabel("");
		error_1.setForeground(Color.RED);
		error_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		error_1.setBounds(625, 446, 220, 15);

		// getInformation panel
		main_panel_Information = new JPanel();
		main_panel_Information.setBorder(new EmptyBorder(5, 5, 5, 5));
		main_panel_Information.setBounds(main_panel.getBounds());
		main_panel_Information.setLayout(null);
		getContentPane().add(main_panel_Information, "2");

		JLabel heading_label_1 = new JLabel("CPU Scheduling Algorithms");
		heading_label_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 40));
		heading_label_1.setBounds(325, 0, 583, 78);
		main_panel_Information.add(heading_label_1);

		JButton next_button_1 = new JButton("NEXT");
		next_button_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		next_button_1.setBounds(next_button.getBounds());
		main_panel_Information.add(next_button_1);

		JButton back_button = new JButton("BACK");
		back_button.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		back_button.setBounds(new Rectangle(231, 581, 97, 34));
		main_panel_Information.add(back_button);

		JButton clear_button_1 = new JButton("CLEAR");
		clear_button_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		clear_button_1.setBounds(clear_button.getBounds());
		main_panel_Information.add(clear_button_1);

		// getContentPane().remove(main_panel);

		main_panel_getInformation_scrollpanel = new JPanel();

		JLabel lblNo = new JLabel("No.");
		lblNo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblNo.setBounds(109, 100, 39, 19);
		main_panel_Information.add(lblNo);

		JLabel lblProcessId = new JLabel("Process ID");
		lblProcessId.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblProcessId.setBounds(204, 100, 124, 19);
		main_panel_Information.add(lblProcessId);

		JLabel lblArrivalTime = new JLabel("Arrival Time");
		lblArrivalTime.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblArrivalTime.setBounds(424, 100, 124, 19);
		main_panel_Information.add(lblArrivalTime);

		JLabel lblBurstTime = new JLabel("Burst Time");
		lblBurstTime.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblBurstTime.setBounds(677, 100, 124, 19);
		main_panel_Information.add(lblBurstTime);

		JLabel lblPriority = new JLabel("Priority");
		lblPriority.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblPriority.setBounds(927, 100, 124, 19);
		main_panel_Information.add(lblPriority);

		scrollPane = new JScrollPane(main_panel_getInformation_scrollpanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(1000, 416));
		scrollPane.setBounds(85, 126, 1000, 416);
		main_panel_Information.add(scrollPane);

		// result panel
		result_panel = new JPanel();
		result_panel.setLayout(null);
		getContentPane().add(result_panel, "3");

		result_table_panel = new JPanel();
		result_table_panel.setSize(930, 312);
		result_table_panel.setLocation(117, 75);
		result_table_panel.setLayout(null);
		result_panel.add(result_table_panel);

		JLabel heading_label_1_1 = new JLabel("CPU Scheduling Algorithms");
		heading_label_1_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 40));
		heading_label_1_1.setBounds(301, 11, 534, 47);
		result_panel.add(heading_label_1_1);

		JLabel lblNewLabel = new JLabel("Average Turn Around Time:  ");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel.setBounds(117, 532, 286, 22);
		result_panel.add(lblNewLabel);

		JLabel lblAverageWaitingTime = new JLabel("Average Waiting Time:  ");
		lblAverageWaitingTime.setFont(new Font("Arial", Font.BOLD, 18));
		lblAverageWaitingTime.setBounds(117, 499, 286, 22);
		result_panel.add(lblAverageWaitingTime);

		JLabel lblAverageTurnResponse = new JLabel("Average Response Time:  ");
		lblAverageTurnResponse.setFont(new Font("Arial", Font.BOLD, 18));
		lblAverageTurnResponse.setBounds(117, 565, 286, 22);
		result_panel.add(lblAverageTurnResponse);

		txtWaiting = new JTextField();
		txtWaiting.setEditable(false);
		txtWaiting.setHorizontalAlignment(SwingConstants.CENTER);
		txtWaiting.setText("waiting");
		txtWaiting.setFont(new Font("Arial", Font.BOLD, 16));
		txtWaiting.setBounds(428, 500, 86, 21);
		result_panel.add(txtWaiting);
		txtWaiting.setColumns(10);

		txtTurn = new JTextField();
		txtTurn.setText("turn");
		txtTurn.setHorizontalAlignment(SwingConstants.CENTER);
		txtTurn.setFont(new Font("Arial", Font.BOLD, 16));
		txtTurn.setEditable(false);
		txtTurn.setColumns(10);
		txtTurn.setBounds(428, 536, 86, 20);
		result_panel.add(txtTurn);

		txtResponse = new JTextField();
		txtResponse.setText("res");
		txtResponse.setHorizontalAlignment(SwingConstants.CENTER);
		txtResponse.setFont(new Font("Arial", Font.BOLD, 16));
		txtResponse.setEditable(false);
		txtResponse.setColumns(10);
		txtResponse.setBounds(428, 569, 86, 20);
		result_panel.add(txtResponse);

		JButton back_button_1 = new JButton("BACK");
		back_button_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		back_button_1.setBounds(new Rectangle(231, 581, 97, 34));
		back_button_1.setBounds(117, 623, 97, 34);
		result_panel.add(back_button_1);

		JButton exit_button_1 = new JButton("EXIT");
		exit_button_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		exit_button_1.setBounds(958, 623, 89, 34);
		result_panel.add(exit_button_1);

		JButton share = new JButton("SHARE");
		share.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		share.setBounds(541, 623, 112, 34);
		// result_panel.add(share);

		gantt_chart = new JPanel();
		gantt_chart.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		JScrollPane scrollPane_1 = new JScrollPane(gantt_chart, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_1.setBounds(117, 414, 940, 65);
		result_panel.add(scrollPane_1);

		JLabel lblGanttChart = new JLabel("Gantt Chart:");
		lblGanttChart.setFont(new Font("Arial", Font.BOLD, 18));
		lblGanttChart.setBounds(117, 390, 286, 22);
		result_panel.add(lblGanttChart);

		txtAlgorithm = new JTextField();
		txtAlgorithm.setHorizontalAlignment(SwingConstants.CENTER);
		txtAlgorithm.setFont(new Font("Arial", Font.BOLD, 18));
		txtAlgorithm.setEnabled(false);
		txtAlgorithm.setBounds(890, 34, 269, 20);
		result_panel.add(txtAlgorithm);
		txtAlgorithm.setColumns(10);

		time_slice_result_label = new JLabel("Time Slice: ");
		time_slice_result_label.setFont(new Font("Arial", Font.BOLD, 18));
		time_slice_result_label.setBounds(10, 37, 204, 27);

		// event handling

		algorithm_combobox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (algorithm_combobox.getSelectedIndex() == 0) {
					process_type_combobox.setSelectedIndex(1);
					process_type_combobox.setEnabled(false);
					process_type_combobox.setEditable(false);
					main_panel.remove(time_slice_textfield);
					main_panel.remove(time_slice_label);
					main_panel.remove(error_1);
					dynamicChanges();
				} else if (algorithm_combobox.getSelectedIndex() == 2) {
					process_type_combobox.setSelectedIndex(0);
					process_type_combobox.setEnabled(false);
					process_type_combobox.setEditable(false);
					main_panel.add(time_slice_textfield);
					main_panel.add(time_slice_label);
					main_panel.add(error_1);
					dynamicChanges();

				} else {
					process_type_combobox.setEnabled(true);
					process_type_combobox.setEditable(false);
					main_panel.remove(time_slice_textfield);
					main_panel.remove(time_slice_label);
					main_panel.remove(error_1);
					dynamicChanges();
				}

			}

		});

		no_of_processes_textfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {

				String value = no_of_processes_textfield.getText();
				int l = value.length();

				if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE
						|| ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_UP
						|| ke.getKeyCode() == KeyEvent.VK_DOWN || ke.getKeyCode() == KeyEvent.VK_LEFT) {
					no_of_processes_textfield.setEditable(true);
					error.setText("");
				} else {
					no_of_processes_textfield.setEditable(false);
					error.setText("Enter only numeric digits");
				}
			}
		});

		time_slice_textfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {

				String value = time_slice_textfield.getText();
				int l = value.length();

				if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE
						|| ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_UP
						|| ke.getKeyCode() == KeyEvent.VK_DOWN || ke.getKeyCode() == KeyEvent.VK_LEFT) {
					time_slice_textfield.setEditable(true);
					error_1.setText("");
				} else {
					time_slice_textfield.setEditable(false);
					error_1.setText("Enter only numeric digits");
				}
			}
		});

		next_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (no_of_processes_textfield.getText().trim().length() > 0) {
					String regex = "\\d+";
					if (no_of_processes_textfield.getText().matches(regex)) {
						if (Integer.parseInt(no_of_processes_textfield.getText()) > 0) {
							no_of_processes = Integer.parseInt(no_of_processes_textfield.getText().trim());
							algorithm = algorithm_combobox.getSelectedIndex();
							if (algorithm == 2) {
								if (time_slice_textfield.getText().trim().length() > 0) {
									if (time_slice_textfield.getText().matches(regex)) {
										if (Integer.parseInt(time_slice_textfield.getText()) > 0) {
											Time_Slice = Integer.parseInt(time_slice_textfield.getText().trim());
											getInputData();
											card.next(getContentPane());
										} else {
											error_1.setText("Time slice should be more than 0");
										}
									} else {
										error_1.setText("Enter only numeric digits");
									}
								}
							} else {
								getInputData();
								card.next(getContentPane());
							}
						} else {
							error.setText("Processes should be more than 0");
						}
					} else {
						error.setText("Enter only numeric digits");
					}
				} else {
					error.setText("Enter No. of Processes");
				}

			}

		});

		exit_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();

			}

		});

		clear_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				algorithm_combobox.setSelectedIndex(0);
				no_of_processes_textfield.setText("");
				time_slice_textfield.setText("");
				;
				error.setText("");
				error_1.setText("");

				main_panel.remove(time_slice_textfield);
				main_panel.remove(time_slice_label);
				main_panel.remove(error_1);
				dynamicChanges();

			}

		});

		clear_button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getInputData();
			}

		});

		back_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				card.previous(getContentPane());
			}

		});

		next_button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int arrival_errors_count = 0;
				int process_id_errors_count = 0;
				int priority_errors_count = 0;
				int burst_errors_count = 0;

				for (int i = 0; i < no_of_processes; i++) {

					if (main_panel_getInformation[i].txtId.getText().trim().length() == 0) {
						main_panel_getInformation[i].process_id_error.setText("Enter Process Id");
						process_id_errors_count = process_id_errors_count + 1;
					} else {
						process_id_errors_count = process_id_errors_count - 1;
					}

					if (main_panel_getInformation[i].txtArrival.getText().trim().length() == 0) {
						main_panel_getInformation[i].arrival_error.setText("Enter Arrival Time");
						arrival_errors_count = arrival_errors_count + 1;
					} else {
						arrival_errors_count = arrival_errors_count - 1;
					}

					if (main_panel_getInformation[i].txtBurst.getText().trim().length() == 0) {
						main_panel_getInformation[i].burst_error.setText("Enter Burst Time");
						burst_errors_count = burst_errors_count + 1;
					} else {
						burst_errors_count = burst_errors_count - 1;
					}

					if (main_panel_getInformation[i].txtPriority.getText().trim().length() == 0) {
						main_panel_getInformation[i].priority_error.setText("Enter Priority");
						priority_errors_count = priority_errors_count + 1;
					} else {
						priority_errors_count = priority_errors_count - 1;
					}
				}

				error_count = arrival_errors_count + burst_errors_count + priority_errors_count
						+ process_id_errors_count;
				int no = -(4 * no_of_processes);
				if (error_count == no) {
					card.next(getContentPane());
					result_table_panel.removeAll();
					insertIntoTable();
				} else {

				}
				dynamicChanges();
			}

		});

		exit_button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();

			}

		});

		back_button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				card.previous(getContentPane());

			}

		});

		;

	}

	public void dynamicChanges() {
		this.repaint();
		this.revalidate();
	}

	public void getInputData() {
		int j = 0;
		main_panel_getInformation_scrollpanel.removeAll();
		main_panel_getInformation = new GetDataPanel[no_of_processes];
		FlowLayout flowLayout = (FlowLayout) main_panel_getInformation_scrollpanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setAlignOnBaseline(true);
		for (int i = 0; i < no_of_processes; i++) {
			j = j + 55;
			main_panel_getInformation[i] = new GetDataPanel(algorithm, i);
			main_panel_getInformation_scrollpanel.add(main_panel_getInformation[i]);
			main_panel_getInformation_scrollpanel.setPreferredSize(new Dimension(980, j));
		}
		dynamicChanges();

	}

	public void insertIntoTable() {
		Vector gantt_chart_data[] = new Vector[4];
		Object columns[] = { "No", "Process Id", "Arrival Time", "Burst Time", "Priority", "Completion Time",
				"Turn Around Time", "Waiting Time", "Response Time" };
		Object data[][] = new Object[no_of_processes][9];
		gantt_chart.removeAll();

		for (int i = 0; i < no_of_processes; i++) {
			int countString = i + 1;
			int j = 0;
			data[i][j] = countString;
			j++;
			data[i][j] = main_panel_getInformation[i].txtId.getText().trim();
			j++;
			data[i][j] = (Object) Integer.valueOf(main_panel_getInformation[i].txtArrival.getText().trim());
			j++;
			data[i][j] = (Object) Integer.parseInt(main_panel_getInformation[i].txtBurst.getText().trim());
			j++;
			data[i][j] = (Object) Integer.parseInt(main_panel_getInformation[i].txtPriority.getText().trim());
			j++;
			data[i][j] = 0;
			j++;
			data[i][j] = 0;
			j++;
			data[i][j] = 0;
			j++;
			data[i][j] = -1;
			j++;
		}

		if (algorithm == 0) {
			First_Come_First_Serve FCFS = new First_Come_First_Serve(data);
			data = FCFS.data;
			txtWaiting.setText("" + FCFS.AverageWaitingTime);
			txtResponse.setText("" + FCFS.AverageResponseTime);
			txtTurn.setText("" + FCFS.AverageTurnAroundTime);
			gantt_chart_data = FCFS.gantt_Chart;

		} else if (algorithm == 1) {
			if (process_type_combobox.getSelectedIndex() == 0) {
				Shortest_Job_First_Preemtive SJBP = new Shortest_Job_First_Preemtive(data);
				data = SJBP.data;
				txtWaiting.setText("" + SJBP.AverageWaitingTime);
				txtResponse.setText("" + SJBP.AverageResponseTime);
				txtTurn.setText("" + SJBP.AverageTurnAroundTime);
				gantt_chart_data = SJBP.gantt_Chart;
			} else if (process_type_combobox.getSelectedIndex() == 1) {
				Shortest_Job_First_NonPreemtive SJFNP = new Shortest_Job_First_NonPreemtive(data);
				data = SJFNP.data;
				txtWaiting.setText("" + SJFNP.AverageWaitingTime);
				txtResponse.setText("" + SJFNP.AverageResponseTime);
				txtTurn.setText("" + SJFNP.AverageTurnAroundTime);
				gantt_chart_data = SJFNP.gantt_Chart;
			}
		} else if (algorithm == 2) {

			Round_Robin RR = new Round_Robin(data, Time_Slice);
			data = RR.data;
			txtWaiting.setText("" + RR.AverageWaitingTime);
			txtResponse.setText("" + RR.AverageResponseTime);
			txtTurn.setText("" + RR.AverageTurnAroundTime);
			gantt_chart_data = RR.gantt_Chart;
			time_slice_result_label.setText("Time Slice: " + Time_Slice);
			result_panel.add(time_slice_result_label);
		} else if (algorithm == 3) {
			if (process_type_combobox.getSelectedIndex() == 0) {
				Priority_Scheduling_Preemtive PSP = new Priority_Scheduling_Preemtive(data);
				data = PSP.data;
				txtWaiting.setText("" + PSP.AverageWaitingTime);
				txtResponse.setText("" + PSP.AverageResponseTime);
				txtTurn.setText("" + PSP.AverageTurnAroundTime);
				gantt_chart_data = PSP.gantt_Chart;

			} else if (process_type_combobox.getSelectedIndex() == 1) {
				Priority_Scheduling_Non_Preemtive PSNP = new Priority_Scheduling_Non_Preemtive(data);
				data = PSNP.data;
				txtWaiting.setText("" + PSNP.AverageWaitingTime);
				txtResponse.setText("" + PSNP.AverageResponseTime);
				txtTurn.setText("" + PSNP.AverageTurnAroundTime);
				gantt_chart_data = PSNP.gantt_Chart;
			}
		}
		Object[] processes = gantt_chart_data[0].toArray();
		Object[] start = gantt_chart_data[1].toArray();
		Object[] end = gantt_chart_data[2].toArray();
		gantt_chart_info = new GanttChart[processes.length];
		for (int m = 0; m < processes.length; m++) {
			gantt_chart_info[m] = new GanttChart(processes[m], start[m], end[m]);
			if (m == processes.length - 1) {
				gantt_chart_info[m].add(gantt_chart_info[m].end_label, gantt_chart_info[m].gbc_end_label);
			}
			gantt_chart.add(gantt_chart_info[m]);
		}

		result_table = new JTable(data, columns);
		result_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		result_table.setEnabled(false);
		result_table.getColumnModel().getColumn(0).setPreferredWidth(30);
		result_table.setRowHeight(30);
		result_table.setIntercellSpacing(new Dimension(10, 10));
		result_table.setCellSelectionEnabled(true);
		result_table.setColumnSelectionAllowed(true);
		result_table.setFillsViewportHeight(true);
		result_table.setFont(new Font("Arial", Font.PLAIN, 14));
		result_table.setRowSelectionAllowed(true);
		result_table.setBounds(1020, 391, -872, -114);

		JScrollPane scrollPane_1 = new JScrollPane(result_table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_1.setSize(930, 312);
		scrollPane_1.setLocation(0, 0);
		result_table_panel.add(scrollPane_1);
		txtAlgorithm.setText(algorithm_combobox.getSelectedItem().toString());
		dynamicChanges();
	}
}
