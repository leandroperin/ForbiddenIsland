package forbidden_insland;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AboutUs extends JDialog {
	
	private static final long serialVersionUID = 5616992051699461433L;
	
	protected static final String strTitle = "Forbidden Island - About Us";
	protected static final String strCreatedBy = "©2015 - Modelated, developed, tested and released by:";
	protected static final String strDevOne = "Gustavo Garcia Gava";
	protected static final String strDevTwo = "Leandro Perin de Oliveira";
	protected static final String strProjectInfo = "<html>This game was developed as a Software Engineering project"
			+ " <br>at the Federal University Of Santa Catarina (UFSC)</html>";
	protected static final String strBackgroundColor = "0xeedfcc";
	protected static final String strLogoPath = "/res/About/logoUFSC.png";
	
	protected JPanel frmAboutUs;

	public AboutUs() {
		super();
		
		frmAboutUs = new JPanel();
		frmAboutUs.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmAboutUs.setLayout(null);
		frmAboutUs.setBackground(Color.decode(strBackgroundColor));

		setResizable(false);
		setType(Type.UTILITY);
		setTitle(strTitle);
		setBounds(100,100,500,350);
		setContentPane(frmAboutUs);
		setVisible(true);
		
		initComponents();
	}
	
	public void initComponents() {
		JLabel lblIcon = new JLabel();
		lblIcon.setIcon(new ImageIcon(Main.class.getResource(strLogoPath)));
		lblIcon.setBounds(200, 10, 400, 150);
		frmAboutUs.add(lblIcon);
		
		JLabel lblCreatedBy = new JLabel(strCreatedBy);
		lblCreatedBy.setBounds(20, 130, 500, 100);
		frmAboutUs.add(lblCreatedBy);
		
		JLabel lblDevOne = new JLabel(strDevOne);
		lblDevOne.setBounds(20, 150, 500, 100);
		frmAboutUs.add(lblDevOne);
		
		JLabel lblDevTwo = new JLabel(strDevTwo);
		lblDevTwo.setBounds(20, 170, 500, 100);
		frmAboutUs.add(lblDevTwo);
		
		JLabel lblProjectInfo = new JLabel(strProjectInfo);
		lblProjectInfo.setBounds(20, 230, 500, 100);
		frmAboutUs.add(lblProjectInfo);
	}
	
}
