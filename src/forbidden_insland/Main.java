package forbidden_insland;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
	
	protected JFrame frmForbiddenIsland;
	
	protected static final String strTitle = "Forbidden Island - The Game";
	
	protected static final String strServerAddress = "localhost";
	protected static final String strBackgroundColor = "0x754500";
	
	protected static final String strLogoPath = "/res/Main/forbiddenIslandLogo.png";
	protected static final String strBeginPath = "/res/Main/startButton.png";
	protected static final String strAboutPath = "/res/Main/aboutUsButton.png";
	protected static final String strExitPath = "/res/Main/exitButton.png";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmForbiddenIsland.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Main() {
		initialize();
	}
	
	private void initialize() {
		frmForbiddenIsland = new JFrame();
		frmForbiddenIsland.setTitle(strTitle);
		frmForbiddenIsland.setResizable(false);
		frmForbiddenIsland.getContentPane().setBackground(Color.decode(strBackgroundColor));;
		frmForbiddenIsland.setBounds(100, 100, 1000, 600);
		frmForbiddenIsland.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmForbiddenIsland.getContentPane().setLayout(null);
		
		initComponents();
	}
	
	private void initComponents() {
		JLabel lblIcon = new JLabel();
		lblIcon.setIcon(new ImageIcon(Main.class.getResource(strLogoPath)));
		lblIcon.setBounds(300, 10, 400, 150);
		frmForbiddenIsland.getContentPane().add(lblIcon);
		
		JLabel btnBegin = new JLabel();
		btnBegin.setIcon(new ImageIcon(Main.class.getResource(strBeginPath)));
		btnBegin.setBounds(360, 250, 300, 100);
		btnBegin.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new Interface_Board(strServerAddress);
			}
		});
		frmForbiddenIsland.getContentPane().add(btnBegin);
		
		JLabel btnAboutUs = new JLabel();
		btnAboutUs.setIcon(new ImageIcon(Main.class.getResource(strAboutPath)));
		btnAboutUs.setBounds(20, 450, 300, 100);
		btnAboutUs.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new AboutUs();
			}
		});
		frmForbiddenIsland.getContentPane().add(btnAboutUs);
		
		JLabel btnExit = new JLabel();
		btnExit.setIcon(new ImageIcon(Main.class.getResource(strExitPath)));
		btnExit.setBounds(850, 450, 300, 100);
		btnExit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		frmForbiddenIsland.getContentPane().add(btnExit);
	}

}
