package handsOn4;

import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class AgentGui extends JFrame {	
	private MyAgent myAgent;
	
	private JTextField statement;
	
	AgentGui(MyAgent a) {
		super(a.getLocalName());
		
		myAgent = a;
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4, 1));
		p.add(new JLabel("Fact example: (book (surname \"Fred Jones\") (name \"ones\"))"));
		p.add(new JLabel("Rule example: (defrule find_title ?book<-(book (name Deb)) => (printout t ?book crlf))"));
		p.add(new JLabel("statement: "));
		statement = new JTextField(15);
		p.add(statement);
		getContentPane().add(p, BorderLayout.CENTER);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String textField = statement.getText().trim();
					myAgent.addRuleFact(textField);
					statement.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(AgentGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		p = new JPanel();
		p.add(addButton);
		getContentPane().add(p, BorderLayout.SOUTH);
		
		// Make the agent terminate when the user closes 
		// the GUI using the button on the upper right corner	
		addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				myAgent.doDelete();
			}
		} );
		
		setResizable(false);
	}
	
	public void showGui() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		super.setVisible(true);
	}	
}