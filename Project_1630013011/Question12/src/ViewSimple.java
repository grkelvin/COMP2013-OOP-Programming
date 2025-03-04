import javax.swing.JLabel;

public class ViewSimple extends View implements ModelListener {
	private ControllerSimple c;
	private JLabel label;
	
	//Add a label to indicate the current total money of the bank
	public ViewSimple(Bank m, ControllerSimple c) {
		super(m);
		this.c = c;
		label = new JLabel(Integer.toString(m.totalMoney()));
		this.setSize(400, 300);
		this.add(label);
		this.setVisible(true);
	}

	//Update the label
	@Override
	public void update() {
		label.setText(Integer.toString(m.totalMoney()));
	}
}