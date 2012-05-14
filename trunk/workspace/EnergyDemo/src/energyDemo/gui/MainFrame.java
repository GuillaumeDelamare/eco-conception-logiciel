package energyDemo.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import energyDemo.threads.SleepThread;
import energyDemo.threads.cpu.CalculeThead;
import energyDemo.threads.cpu.FactorielThread;
import energyDemo.threads.cpu.QuickSortThread;
import energyDemo.threads.ram.StringThread;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 7214913835484410337L;

	private String[] comboBoxItem = {"SleepThread", "ComputeThread", "FactorialThread", "QuickSortThread", "StringThread"};
	
	private JButton jbStart, jbClear;
	private JComboBox jcChoice;
	
	public MainFrame() {
		setTitle("EnergyDemo");
		
		createComponent();
		placeComponent();
		createController();
	}

	private void createComponent() {
		jbClear = new JButton("Quit");
		jbStart = new JButton("Start");
		
		jcChoice = new JComboBox(comboBoxItem);
	}
	private void placeComponent() {
		JPanel p = new JPanel(new BorderLayout());{
			JPanel p1 = new JPanel(new FlowLayout());{
				p1.add(jcChoice);
			}
			p.add(p1, BorderLayout.NORTH);
			
			JPanel p2 = new JPanel(new FlowLayout());{
				p2.add(jbStart);
				p2.add(jbClear);
			}
			p.add(p2, BorderLayout.CENTER);
		}
		this.add(p);
	}
	private void createController() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jbStart.addActionListener(new ActionListener() {
			private Thread t = null;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(t == null) {
					Object o = jcChoice.getSelectedItem();
					if (o instanceof String) {
						String s = (String) o;
						
						if(s == "SleepThread") {
							t = new SleepThread();
						}
						else if(s == "ComputeThread"){
							t = new CalculeThead();
						}
						else if(s == "FactorialThread") {
							t = new FactorielThread(36);
						}
						else if(s == "QuickSortThread") {
							t = new QuickSortThread(10000000);
						}
						else if(s == "StringThread") {
							t = new StringThread(99999999);
						}
						else{
							System.err.println("Choix inconnu !!!");
						}
						
						if(t != null){
							t.start();
							jbStart.setText("Stop");
						}
					}
				}
				else {
					t.interrupt();
					t=null;
					jbStart.setText("Start");
				}
			}
		});
		jbClear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);	
			}
		});
	}
	
	public void display(){
		pack();
		setVisible(true);
	}
}
