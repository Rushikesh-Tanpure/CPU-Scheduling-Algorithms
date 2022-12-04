package CPU_Algorithms;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

public class GetDataPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public String txtCompletion;
	public String txtResponse;
	public String txtWaiting;
	public String txtTAT;
	public JTextField txtId;
	public JTextField txtArrival;
	public JTextField txtBurst;
	public JTextField txtPriority;
	public JTextField txtNo;
	public JLabel process_id_error;
	public JLabel priority_error;
	public JLabel arrival_error;
	public JLabel burst_error;
	public JSeparator separator;
	public JScrollPane scrollPane;
	
	public JPanel main_panel_getInformation;
	
	public GetDataPanel(int algorithm,int count) {
		main_panel_getInformation =  new JPanel();
		add(main_panel_getInformation);
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[]{92, 219, 0, 0, 0, 0};
		gbl.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, 0.0};
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;
		
		txtCompletion=" ";
		txtResponse=" ";
		txtWaiting=" ";
		txtTAT=" ";
		
		main_panel_getInformation.setLayout(gbl);
		setSize(1000,55);
		
		String countString=Integer.toString(++count);
		txtNo = new JTextField(countString);
		txtNo.setEnabled(false);
		txtNo.setEditable(false);
		txtNo.setHorizontalAlignment(SwingConstants.CENTER);
		txtNo.setFont(new Font("Arial", Font.PLAIN, 18));
		txtNo.setColumns(3);
		txtNo.setBounds(10, 10, 56, 20);
		GridBagConstraints gbc_txtNo = new GridBagConstraints();
		gbc_txtNo.insets = new Insets(0, -10, 5, 40);
		gbc_txtNo.weighty = 1.0;
		gbc_txtNo.weightx = 1.0;
		gbc_txtNo.fill = GridBagConstraints.BOTH;
		gbc_txtNo.gridy = 0;
		gbc_txtNo.gridx = 0;
		main_panel_getInformation.add(txtNo, gbc_txtNo);
		
		
		txtId = new JTextField();
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setText("P"+countString);
		txtId.setFont(new Font("Arial", Font.PLAIN, 18));
		txtId.setBounds(114, 10, 86, 20);
		GridBagConstraints gbc_txtId = new GridBagConstraints();
		gbc_txtId.anchor = GridBagConstraints.WEST;
		gbc_txtId.insets = new Insets(0, 0, 5, 75);
		gbc_txtId.gridx = 1;
		gbc_txtId.gridy = 0;
		main_panel_getInformation.add(txtId, gbc_txtId);
		txtId.setColumns(10);
		
		process_id_error = new JLabel("");
		GridBagConstraints gbc_process_id_error = new GridBagConstraints();
		gbc_process_id_error.anchor = GridBagConstraints.NORTH;
		gbc_process_id_error.fill = GridBagConstraints.BOTH;
		gbc_process_id_error.insets = new Insets(0, 0, 0, 5);
		gbc_process_id_error.gridx = 1;
		gbc_process_id_error.gridy = 1;
		main_panel_getInformation.add(process_id_error, gbc_process_id_error);
		process_id_error.setHorizontalAlignment(SwingConstants.LEFT);
		process_id_error.setForeground(Color.RED);
		process_id_error.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		
		txtArrival = new JTextField();
		txtArrival.setText("0");
		txtArrival.setHorizontalAlignment(SwingConstants.CENTER);
		txtArrival.setFont(new Font("Arial", Font.PLAIN, 18));
		txtArrival.setColumns(10);
		txtArrival.setBounds(334, 10, 86, 20);
		GridBagConstraints gbc_txtArrival = new GridBagConstraints();
		gbc_txtArrival.fill = GridBagConstraints.BOTH;
		gbc_txtArrival.insets = new Insets(0, 0, 5, 100);
		gbc_txtArrival.gridx = 2;
		gbc_txtArrival.gridy = 0;
		main_panel_getInformation.add(txtArrival, gbc_txtArrival);
		
		arrival_error = new JLabel("");
		GridBagConstraints gbc_arrival_error = new GridBagConstraints();
		gbc_arrival_error.anchor = GridBagConstraints.NORTH;
		gbc_arrival_error.fill = GridBagConstraints.BOTH;
		gbc_arrival_error.insets = new Insets(0, 0, 0, 5);
		gbc_arrival_error.gridx = 2;
		gbc_arrival_error.gridy = 1;
		main_panel_getInformation.add(arrival_error, gbc_arrival_error);
		arrival_error.setHorizontalAlignment(SwingConstants.LEFT);
		arrival_error.setForeground(Color.RED);
		arrival_error.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtArrival.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				
				String value = txtArrival.getText();
				int l = value.length();
				
				if((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyCode()==KeyEvent.VK_BACK_SPACE || ke.getKeyCode()==KeyEvent.VK_RIGHT || ke.getKeyCode()==KeyEvent.VK_UP || ke.getKeyCode()==KeyEvent.VK_DOWN || ke.getKeyCode()==KeyEvent.VK_LEFT) {
					txtArrival.setEditable(true);
					arrival_error.setText("");
				}
				else {
					txtArrival.setEditable(false);
					arrival_error.setText("Enter only numeric digits");
				}
			}
		});
		
	
		txtBurst = new JTextField();
		txtBurst.setText("0");
		txtBurst.setHorizontalAlignment(SwingConstants.CENTER);
		txtBurst.setFont(new Font("Arial", Font.PLAIN, 18));
		txtBurst.setColumns(10);
		GridBagConstraints gbc_txtBurst = new GridBagConstraints();
		gbc_txtBurst.insets = new Insets(0, 0, 5, 90);
		gbc_txtBurst.gridx = 3;
		gbc_txtBurst.gridy = 0;
		main_panel_getInformation.add(txtBurst, gbc_txtBurst);
	
		burst_error = new JLabel("");
		burst_error.setHorizontalAlignment(SwingConstants.LEFT);
		burst_error.setForeground(Color.RED);
		burst_error.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_burst_error = new GridBagConstraints();
		gbc_burst_error.fill = GridBagConstraints.BOTH;
		gbc_burst_error.insets = new Insets(0, 0, 0, 5);
		gbc_burst_error.gridx = 3;
		gbc_burst_error.gridy = 1;
		main_panel_getInformation.add(burst_error, gbc_burst_error);

		txtBurst.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				
				String value = txtBurst.getText();
				int l = value.length();
				
				if((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyCode()==KeyEvent.VK_BACK_SPACE || ke.getKeyCode()==KeyEvent.VK_RIGHT || ke.getKeyCode()==KeyEvent.VK_UP || ke.getKeyCode()==KeyEvent.VK_DOWN || ke.getKeyCode()==KeyEvent.VK_LEFT) {
					txtBurst.setEditable(true);
					burst_error.setText("");
				}
				else {
					txtBurst.setEditable(false);
					burst_error.setText("Enter only numeric digits");
				}
			}
		});
		
		txtPriority = new JTextField();
		txtPriority.setText("0");
		txtPriority.setHorizontalAlignment(SwingConstants.CENTER);
		txtPriority.setFont(new Font("Arial", Font.PLAIN, 18));
		txtPriority.setColumns(10);
		GridBagConstraints gbc_txtPriority = new GridBagConstraints();
		gbc_txtPriority.insets = new Insets(0, 0, 5, 5);
		gbc_txtPriority.gridx = 4;
		gbc_txtPriority.gridy = 0;
		main_panel_getInformation.add(txtPriority, gbc_txtPriority);
		if(algorithm!=3) {
			txtPriority.setEditable(false);
			txtPriority.setEnabled(false);
		}
		else {
			txtPriority.setEditable(true);
			txtPriority.setEnabled(true);
			priority_error = new JLabel("");
			priority_error.setHorizontalAlignment(SwingConstants.LEFT);
			priority_error.setForeground(Color.RED);
			priority_error.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_priority_error = new GridBagConstraints();
			gbc_priority_error.fill = GridBagConstraints.BOTH;
			gbc_priority_error.gridy = 1;
			gbc_priority_error.gridx = 4;
			main_panel_getInformation.add(priority_error, gbc_priority_error);
			
			txtPriority.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent ke) {
					
					String value = txtPriority.getText();
					int l = value.length();
					
					if((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyCode()==KeyEvent.VK_BACK_SPACE || ke.getKeyCode()==KeyEvent.VK_RIGHT || ke.getKeyCode()==KeyEvent.VK_UP || ke.getKeyCode()==KeyEvent.VK_DOWN || ke.getKeyCode()==KeyEvent.VK_LEFT) {
						txtPriority.setEditable(true);
						priority_error.setText("");
					}
					else {
						txtPriority.setEditable(false);
						priority_error.setText("Enter only numeric digits");
					}
				}
			});
		}
		
		separator = new JSeparator();
		separator.setBounds(0, 60, 1010, 2);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 5;
		gbc_separator.gridy = 0;
		main_panel_getInformation.add(separator, gbc_separator);
		
		
		
		
	}

}
