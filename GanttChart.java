package CPU_Algorithms;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GanttChart extends JPanel {
	public  JTextField txtProcess;
	public JLabel start_label;
	public JLabel end_label;
	public GridBagConstraints gbc_end_label;
	public GridBagConstraints gbc_start_label;
	public GridBagLayout gridBagLayout;
	public GridBagConstraints gbc_txtProcess ;
	
	
	public GanttChart(Object process_id,Object start,Object end) {
		setSize(120, 50);
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{35, 56, 29, 0};
		gridBagLayout.rowHeights = new int[]{13, 20, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		start_label = new JLabel(""+start);
		gbc_start_label = new GridBagConstraints();
		gbc_start_label.fill = GridBagConstraints.BOTH;
		gbc_start_label.insets = new Insets(0, 0, 5, 5);
		gbc_start_label.gridx = 0;
		gbc_start_label.gridy = 0;
		add(start_label, gbc_start_label);
		
		end_label = new JLabel(""+end);
		end_label.setHorizontalAlignment(SwingConstants.RIGHT);
		gbc_end_label = new GridBagConstraints();
		gbc_end_label.fill = GridBagConstraints.BOTH;
		gbc_end_label.insets = new Insets(0, 0, 5, 0);
		gbc_end_label.gridx = 2;
		gbc_end_label.gridy = 0;
		add(end_label, gbc_end_label);
		remove(end_label);
		
		txtProcess = new JTextField();
		txtProcess.setEditable(false);
		txtProcess.setFont(new Font("Arial", Font.BOLD, 14));
		txtProcess.setHorizontalAlignment(SwingConstants.CENTER);
		txtProcess.setText(""+process_id);
		gbc_txtProcess = new GridBagConstraints();
		gbc_txtProcess.fill = GridBagConstraints.BOTH;
		gbc_txtProcess.gridwidth = 3;
		gbc_txtProcess.gridx = 0;
		gbc_txtProcess.gridy = 1;
		add(txtProcess, gbc_txtProcess);
		txtProcess.setColumns(10);
	}
}
