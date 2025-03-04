import javax.swing.JFrame;
import javax.swing.JLabel;

public class ViewSimple extends JFrame implements ModelListener {
	private Bank m;
	private ControllerSimple c;
	private JLabel label;
	
	//Add a label
	public ViewSimple(Bank m, ControllerSimple c) {
		this.m = m;
		this.c = c;
		label = new JLabel("There are "+Integer.toString(m.totalMoney())+" in the bank!");
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(label);
		this.setVisible(true);
		
	}

	@Override
	public void update() {
		label.update(null);
		label.setText("There are "+Integer.toString(m.totalMoney())+" in the bank!");
	}
}