package forbidden_insland;

import javax.swing.JLabel;

public class Square extends JLabel {

	private static final long serialVersionUID = 6312163462506528530L;

	protected int id;
	
	public Square() {
		super();
		
		id = -1;
	}
	
	public void setId(int newId) {
		id = newId;
	}
	
	public int getId() {
		return id;
	}
	
}
