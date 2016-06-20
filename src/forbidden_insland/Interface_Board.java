package forbidden_insland;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Stack;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import rede.AtorRede;

public class Interface_Board extends JDialog {

	private static final long serialVersionUID = 7957376221587385899L;
	
	protected JLabel[] treasures;
	protected LinkedList<Square> tiles;
	protected AtorRede rede;
	protected Game logica;
	protected JLabel p1;
	protected JLabel p2;
	protected JLabel waterlevel;
	protected JLabel whoIsPlaying;
	protected JLabel t1p1;
	protected JLabel t2p1;
	protected JLabel t3p1;
	protected JLabel t4p1;
	protected JLabel t1p2;
	protected JLabel t2p2;
	protected JLabel t3p2;
	protected JLabel t4p2;
	protected int function;
	protected int pos_p1;
	protected int pos_p2;
	protected int water;
	protected boolean turno;
	
	protected static final String earthStonePath = "/res/Game/Treasures/earthStone.png";
	protected static final String statueWindPath = "/res/Game/Treasures/statueWind.png";
	protected static final String crystalFirePath = "/res/Game/Treasures/crystalFire.png";
	protected static final String oceanChalicePath = "/res/Game/Treasures/oceanChalice.png";
	protected static final String earthStoneOffPath = "/res/Game/Treasures/earthStoneOff.png";
	protected static final String statueWindOffPath = "/res/Game/Treasures/statueWindOff.png";
	protected static final String crystalFireOffPath = "/res/Game/Treasures/crystalFireOff.png";
	protected static final String oceanChaliceOffPath = "/res/Game/Treasures/oceanChaliceOff.png";
	protected static final String strTitle = "Forbidden Island - Playing";
	protected static final String backPath = "/res/Game/background.jpg";
	protected static final String movePlayerPath = "/res/Game/Buttons/moveCharacter.png";
	protected static final String getTreasurePath = "/res/Game/Buttons/getTreasure.png";
	protected static final String recoverTerrainPath = "/res/Game/Buttons/recoverTerrain.png";
	protected static final String finishTurnPath = "/res/Game/Buttons/finishTurn.png";
	protected static final String endGamePath = "/res/Game/Buttons/endGame.png";
	protected static final String firstPlayerPath = "/res/Game/Characters/First.png";
	protected static final String secondPlayerPath = "/res/Game/Characters/Second.png";
	protected static final String tileBackWaterPath = "/res/Game/Tiles/tileBackWater.jpg";
	protected static final String tileBackPath = "/res/Game/Tiles/tileBack.jpg";
	protected static final String tileEscapePath = "/res/Game/Tiles/tileEscape.png";
	protected static final String treasureTablePath = "/res/Game/TreasureTable.png";
	
	protected static final String strInputName = "Escolha o nome do jogador:";
	protected static final String strWaterLevel = "Água: 1";
	protected static final String strWaterLevel2 = "Água: ";
	protected static final String strWhoIsPlaying = "<html>Player 1<br>Jogando</html>";
	protected static final String strPlayer2Playing = "<html>Player 2<br>Jogando</html>";
	protected static final String strZero = "0";
	protected static final String strDontCapture = "Não foi possível capturar o tesouro!";
	protected static final String strDontEscape = "Não foi possível escapar!";
	protected static final String strVitoria = "VITÓRIA!";
	protected static final String strDerrota = "DERROTA!";
	protected static final String strDontMove = "Não foi possível mover!";
	protected static final String strDontRestore = "Não foi possível restaurar o terreno!";
	protected static final String strSelectAction = "Selecione alguma ação ao lado!";
	
	public Interface_Board(String serverAddress) {
		super();
		
		String nome = JOptionPane.showInputDialog(strInputName);
		
		rede = new AtorRede(this);
		
		conectar(nome, serverAddress);
	}
	
	public void conectar(String nome, String serverAddress) {
		if (!rede.conectar(nome, serverAddress)) {
			System.exit(0);
		}

		rede.iniciarPartidaRede();
	}
	
	private void createInterface_Board() {
		JLabel contentPane = new JLabel();
		contentPane.setIcon(new ImageIcon(Main.class.getResource(backPath)));
		contentPane.setLayout(new BorderLayout());
		setResizable(false);
		setType(Type.UTILITY);
		setTitle(strTitle);
		setBounds(100,100,1000,600);
		setContentPane(contentPane);
		setVisible(true);
		
		function = -1;

		createTiles();
		addTreasures();
		
		initComponents();
		
		logica = new Game();
	}
	
	private void initComponents() {
		p1 = new JLabel();
		p1.setIcon(new ImageIcon(Main.class.getResource(firstPlayerPath)));
		p1.setBounds(tiles.get(8).getBounds());
		add(p1);
		pos_p1 = 8;
		
		p2 = new JLabel();
		p2.setIcon(new ImageIcon(Main.class.getResource(secondPlayerPath)));
		p2.setBounds(tiles.get(15).getBounds());
		add(p2);
		pos_p2 = 15;
		
		turno = true;
		
		JLabel earthStoneTile1 = new JLabel();
		earthStoneTile1.setIcon(new ImageIcon(Main.class.getResource(earthStonePath)));
		earthStoneTile1.setBounds(245 + 85*4, 20 + 85*1, 75, 75);
		add(earthStoneTile1);
		
		JLabel earthStoneTile2 = new JLabel();
		earthStoneTile2.setIcon(new ImageIcon(Main.class.getResource(earthStonePath)));
		earthStoneTile2.setBounds(245 + 85*0, 20 + 85*3, 75, 75);
		add(earthStoneTile2);
		
		JLabel statueWindTile1 = new JLabel();
		statueWindTile1.setIcon(new ImageIcon(Main.class.getResource(statueWindPath)));
		statueWindTile1.setBounds(245 + 85*5, 20 + 85*3, 75, 75);
		add(statueWindTile1);
		
		JLabel statueWindTile2 = new JLabel();
		statueWindTile2.setIcon(new ImageIcon(Main.class.getResource(statueWindPath)));
		statueWindTile2.setBounds(245 + 85*2, 20 + 85*5, 75, 75);
		add(statueWindTile2);
		
		JLabel crystalFireTile1 = new JLabel();
		crystalFireTile1.setIcon(new ImageIcon(Main.class.getResource(crystalFirePath)));
		crystalFireTile1.setBounds(245 + 85*1, 20 + 85*1, 75, 75);
		add(crystalFireTile1);
		
		JLabel crystalFireTile2 = new JLabel();
		crystalFireTile2.setIcon(new ImageIcon(Main.class.getResource(crystalFirePath)));
		crystalFireTile2.setBounds(245 + 85*4, 20 + 85*4, 75, 75);
		add(crystalFireTile2);
		
		JLabel oceanChaliceTile1 = new JLabel();
		oceanChaliceTile1.setIcon(new ImageIcon(Main.class.getResource(oceanChalicePath)));
		oceanChaliceTile1.setBounds(245 + 85*3, 20 + 85*2, 75, 75);
		add(oceanChaliceTile1);
		
		JLabel oceanChaliceTile2 = new JLabel();
		oceanChaliceTile2.setIcon(new ImageIcon(Main.class.getResource(oceanChalicePath)));
		oceanChaliceTile2.setBounds(245 + 85*1, 20 + 85*4, 75, 75);
		add(oceanChaliceTile2);
		
		JLabel escapeTile = new JLabel();
		escapeTile.setIcon(new ImageIcon(Main.class.getResource(tileEscapePath)));
		escapeTile.setBounds(245 + 85*2, 20 + 85*0, 75, 75);
		add(escapeTile);
		
		int i = 0;
		for (; i < 24; i++) {
			add(getTile(i));
		}
		
		i = 0;
		for (; i < 4; i++) {
			add(getTreasure(i));
		}
		
		waterlevel = new JLabel();
		waterlevel.setText(strWaterLevel);
		waterlevel.setFont(waterlevel.getFont().deriveFont(30.0f));
		waterlevel.setBounds(50, 40, 200, 100);
		add(waterlevel);
		
		whoIsPlaying = new JLabel();
		whoIsPlaying.setText(strWhoIsPlaying);
		whoIsPlaying.setFont(whoIsPlaying.getFont().deriveFont(40.0f));
		whoIsPlaying.setBounds(30, 140, 200, 110);
		add(whoIsPlaying);
		
		JLabel treasuresTable = new JLabel();
		treasuresTable.setIcon(new ImageIcon(Main.class.getResource(treasureTablePath)));
		treasuresTable.setBounds(15, 250, 300, 300);
		add(treasuresTable);
		
		t1p1 = new JLabel(strZero);
		t1p1.setFont(t1p1.getFont().deriveFont(30.0f));
		t1p1.setBounds(95, 310, 50, 50);
		add(t1p1);
		
		t2p1 = new JLabel(strZero);
		t2p1.setFont(t2p1.getFont().deriveFont(30.0f));
		t2p1.setBounds(95, 365, 50, 50);
		add(t2p1);
		
		t3p1 = new JLabel(strZero);
		t3p1.setFont(t3p1.getFont().deriveFont(30.0f));
		t3p1.setBounds(95, 420, 50, 50);
		add(t3p1);
		
		t4p1 = new JLabel(strZero);
		t4p1.setFont(t4p1.getFont().deriveFont(30.0f));
		t4p1.setBounds(95, 475, 50, 50);
		add(t4p1);
		
		t1p2 = new JLabel(strZero);
		t1p2.setFont(t1p2.getFont().deriveFont(30.0f));
		t1p2.setBounds(175, 310, 50, 50);
		add(t1p2);
		
		t2p2 = new JLabel(strZero);
		t2p2.setFont(t2p2.getFont().deriveFont(30.0f));
		t2p2.setBounds(175, 365, 50, 50);
		add(t2p2);
		
		t3p2 = new JLabel(strZero);
		t3p2.setFont(t3p2.getFont().deriveFont(30.0f));
		t3p2.setBounds(175, 420, 50, 50);
		add(t3p2);
		
		t4p2 = new JLabel(strZero);
		t4p2.setFont(t4p2.getFont().deriveFont(30.0f));
		t4p2.setBounds(175, 475, 50, 50);
		add(t4p2);
		
		water = 1;
		
		JLabel btnMovePlayer = new JLabel();
		btnMovePlayer.setIcon(new ImageIcon(Main.class.getResource(movePlayerPath)));
		btnMovePlayer.setBounds(790, 50, 180, 90);
		btnMovePlayer.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (rede.minhaVez())
					function = 1;
			}
		});
		add(btnMovePlayer);
		
		JLabel btnGetTreasure = new JLabel();
		btnGetTreasure.setIcon(new ImageIcon(Main.class.getResource(getTreasurePath)));
		btnGetTreasure.setBounds(790, 150, 180, 90);
		btnGetTreasure.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (rede.minhaVez()) {
					switch (logica.pegarTesouro()) {
					case 0:
						treasures[1].setIcon(new ImageIcon(Main.class.getResource(statueWindOffPath)));
						int temp = logica.getCardQuantity(turno, 2);
						if (turno) {
							t2p1.setText(Integer.toString(temp));
						} else {
							t2p2.setText(Integer.toString(temp));
						}
						break;
					case 1:
						treasures[0].setIcon(new ImageIcon(Main.class.getResource(earthStoneOffPath)));
						int temp2 = logica.getCardQuantity(turno, 1);
						if (turno) {
							t1p1.setText(Integer.toString(temp2));
						} else {
							t1p2.setText(Integer.toString(temp2));
						}
						break;
					case 2:
						treasures[2].setIcon(new ImageIcon(Main.class.getResource(crystalFireOffPath)));
						int temp3 = logica.getCardQuantity(turno, 3);
						if (turno) {
							t3p1.setText(Integer.toString(temp3));
						} else {
							t3p2.setText(Integer.toString(temp3));
						}
						break;
					case 3:
						treasures[3].setIcon(new ImageIcon(Main.class.getResource(oceanChaliceOffPath)));
						int temp4 = logica.getCardQuantity(turno, 4);
						if (turno) {
							t4p1.setText(Integer.toString(temp4));
						} else {
							t4p2.setText(Integer.toString(temp4));
						}
						break;
					case -1:
						JOptionPane.showMessageDialog(null, strDontCapture);
						break;
					}
				}
			}
		});
		add(btnGetTreasure);
		
		JLabel btnRecoverTerrain = new JLabel();
		btnRecoverTerrain.setIcon(new ImageIcon(Main.class.getResource(recoverTerrainPath)));
		btnRecoverTerrain.setBounds(790, 250, 180, 90);
		btnRecoverTerrain.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (rede.minhaVez())
					function = 3;
			}
		});
		add(btnRecoverTerrain);
		
		JLabel btnFinishTurn = new JLabel();
		btnFinishTurn.setIcon(new ImageIcon(Main.class.getResource(finishTurnPath)));
		btnFinishTurn.setBounds(790, 350, 180, 90);
		btnFinishTurn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (rede.minhaVez()) {
					boolean _endTurn = logica.endTurn();
					
					rede.enviarJogada(logica);
					
					if (_endTurn) {
						water++;
						waterlevel.setText(strWaterLevel2 + Integer.toString(water));
					}
				
					int cards;
					if (turno) {
						cards = logica.getCardQuantity(turno, 1);
						t1p1.setText(Integer.toString(cards));
						
						cards = logica.getCardQuantity(turno, 2);
						t2p1.setText(Integer.toString(cards));
						
						cards = logica.getCardQuantity(turno, 3);
						t3p1.setText(Integer.toString(cards));
						
						cards = logica.getCardQuantity(turno, 4);
						t4p1.setText(Integer.toString(cards));
					} else {
						cards = logica.getCardQuantity(turno, 1);
						t1p2.setText(Integer.toString(cards));
						
						cards = logica.getCardQuantity(turno, 2);
						t2p2.setText(Integer.toString(cards));
						
						cards = logica.getCardQuantity(turno, 3);
						t3p2.setText(Integer.toString(cards));
						
						cards = logica.getCardQuantity(turno, 4);
						t4p2.setText(Integer.toString(cards));
					}
				
					Stack<Integer> temp = logica.getPendentesAlagar();
					while (temp.size() > 0) {
						tiles.get(temp.pop()).setIcon(new ImageIcon(Main.class.getResource(tileBackWaterPath)));
					}
					
					temp = logica.getPendentesRestaurar();
					while (temp.size() > 0) {
						tiles.get(temp.pop()).setIcon(new ImageIcon(Main.class.getResource(tileBackPath)));
					}
					
					temp = logica.getPendentesDestruir();
					while (temp.size() > 0) {
						tiles.get(temp.pop()).setEnabled(false);
					}
				
					turno = !turno;
					
					if (turno) {
						whoIsPlaying.setText(strWhoIsPlaying);
					} else {
						whoIsPlaying.setText(strPlayer2Playing);
					}
					
					add(new JLabel());
					
					if (!logica.isRunning()) {
						JOptionPane.showMessageDialog(null, strDerrota);
						System.exit(0);
					}
				}
			}
		});
		add(btnFinishTurn);
		
		JLabel btnEndGame = new JLabel();
		btnEndGame.setIcon(new ImageIcon(Main.class.getResource(endGamePath)));
		btnEndGame.setBounds(790, 450, 180, 90);
		btnEndGame.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (rede.minhaVez()) {
					if (!logica.tentarEscapar()) {
						JOptionPane.showMessageDialog(null, strDontEscape);
					} else {
						JOptionPane.showMessageDialog(null, strVitoria);
						System.exit(0);
					}
				}
			}
		});
		add(btnEndGame);	
	}
	
	private void addTreasures() {
		treasures = new JLabel[4];
		
		JLabel earthStone = new JLabel();
		earthStone.setIcon(new ImageIcon(Main.class.getResource(earthStonePath)));
		earthStone.setBounds(630, 445, 75, 75);
		treasures[0] = earthStone;
		
		JLabel statueWind = new JLabel();
		statueWind.setIcon(new ImageIcon(Main.class.getResource(statueWindPath)));
		statueWind.setBounds(285, 445, 75, 75);
		treasures[1] = statueWind;
		
		JLabel crystalFire = new JLabel();
		crystalFire.setIcon(new ImageIcon(Main.class.getResource(crystalFirePath)));
		crystalFire.setBounds(630, 20, 75, 75);
		treasures[2] = crystalFire;
		
		JLabel oceanChalice = new JLabel();
		oceanChalice.setIcon(new ImageIcon(Main.class.getResource(oceanChalicePath)));
		oceanChalice.setBounds(285, 20, 75, 75);
		treasures[3] = oceanChalice;
	}
	
	private void setSquareBounds() {
		tiles.get(0).setBounds(245 + 85*2, 20 + 85*0, 75, 75);
		tiles.get(1).setBounds(245 + 85*3, 20 + 85*0, 75, 75);
		tiles.get(2).setBounds(245 + 85*1, 20 + 85*1, 75, 75);
		tiles.get(3).setBounds(245 + 85*2, 20 + 85*1, 75, 75);
		tiles.get(4).setBounds(245 + 85*3, 20 + 85*1, 75, 75);
		tiles.get(5).setBounds(245 + 85*4, 20 + 85*1, 75, 75);
		tiles.get(6).setBounds(245 + 85*0, 20 + 85*2, 75, 75);
		tiles.get(7).setBounds(245 + 85*1, 20 + 85*2, 75, 75);
		tiles.get(8).setBounds(245 + 85*2, 20 + 85*2, 75, 75);
		tiles.get(9).setBounds(245 + 85*3, 20 + 85*2, 75, 75);
		tiles.get(10).setBounds(245 + 85*4, 20 + 85*2, 75, 75);
		tiles.get(11).setBounds(245 + 85*5, 20 + 85*2, 75, 75);
		tiles.get(12).setBounds(245 + 85*0, 20 + 85*3, 75, 75);
		tiles.get(13).setBounds(245 + 85*1, 20 + 85*3, 75, 75);
		tiles.get(14).setBounds(245 + 85*2, 20 + 85*3, 75, 75);
		tiles.get(15).setBounds(245 + 85*3, 20 + 85*3, 75, 75);
		tiles.get(16).setBounds(245 + 85*4, 20 + 85*3, 75, 75);
		tiles.get(17).setBounds(245 + 85*5, 20 + 85*3, 75, 75);
		tiles.get(18).setBounds(245 + 85*1, 20 + 85*4, 75, 75);
		tiles.get(19).setBounds(245 + 85*2, 20 + 85*4, 75, 75);
		tiles.get(20).setBounds(245 + 85*3, 20 + 85*4, 75, 75);
		tiles.get(21).setBounds(245 + 85*4, 20 + 85*4, 75, 75);
		tiles.get(22).setBounds(245 + 85*2, 20 + 85*5, 75, 75);
		tiles.get(23).setBounds(245 + 85*3, 20 + 85*5, 75, 75);
	}
	
	public void refreshPositions() {
		int temp = logica.getPlayerPosition(1);
		p1.setBounds(tiles.get(temp).getBounds());
		
		temp = logica.getPlayerPosition(2);
		p2.setBounds(tiles.get(temp).getBounds());
	}
	
	private void setSquareActions(int index) {
		tiles.get(index).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				switch (function) {
				case -1:
					JOptionPane.showMessageDialog(null, strSelectAction);
					break;
				case 1:
					if (!logica.fazerMovimento(tiles.get(index).getId())) {
						JOptionPane.showMessageDialog(null, strDontMove);
					} else {
						if (turno) {
							pos_p1 = index;
							p1.setBounds(tiles.get(index).getBounds());
						} else {
							pos_p2 = index;
							p2.setBounds(tiles.get(index).getBounds());
						}
					}
					function = -1;
					break;
				case 3:
					if (!logica.restaurarTerreno(tiles.get(index).getId())) {
						JOptionPane.showMessageDialog(null, strDontRestore);
					} else {
						tiles.get(index).setIcon(new ImageIcon(Main.class.getResource(tileBackPath)));
					}
					function = -1;
					break;
				}
			}
		});
	}
	
	private void createTiles() {			
		tiles = new LinkedList<Square>();
		
		int i = 0;
		for (; i < 24; i++) {
			Square square = new Square();
			square.setIcon(new ImageIcon(Main.class.getResource(tileBackPath)));
			square.setId(i);
			tiles.add(square);
			setSquareActions(i);
		}
		
		setSquareBounds();
	}
	
	private Square getTile(int pos) {
		return tiles.get(pos);
	}
	
	private JLabel getTreasure(int pos) {
		return treasures[pos];
	}
	
	public void iniciarPartida() {
		rede.iniciarPartidaRede();
	}
	
	public void refresh() {
		
		if (logica.getAlagar()) {
			water++;
			waterlevel.setText(strWaterLevel2 + Integer.toString(water));
		}
	
		int cards;
		if (turno) {
			cards = logica.getCardQuantity(turno, 1);
			t1p1.setText(Integer.toString(cards));
			
			cards = logica.getCardQuantity(turno, 2);
			t2p1.setText(Integer.toString(cards));
			
			cards = logica.getCardQuantity(turno, 3);
			t3p1.setText(Integer.toString(cards));
			
			cards = logica.getCardQuantity(turno, 4);
			t4p1.setText(Integer.toString(cards));
		} else {
			cards = logica.getCardQuantity(turno, 1);
			t1p2.setText(Integer.toString(cards));
			
			cards = logica.getCardQuantity(turno, 2);
			t2p2.setText(Integer.toString(cards));
			
			cards = logica.getCardQuantity(turno, 3);
			t3p2.setText(Integer.toString(cards));
			
			cards = logica.getCardQuantity(turno, 4);
			t4p2.setText(Integer.toString(cards));
		}
	
		Stack<Integer> temp = logica.getPendentesAlagar();
		while (temp.size() > 0) {
			tiles.get(temp.pop()).setIcon(new ImageIcon(Main.class.getResource(tileBackWaterPath)));
		}
		
		temp = logica.getPendentesRestaurar();
		while (temp.size() > 0) {
			tiles.get(temp.pop()).setIcon(new ImageIcon(Main.class.getResource(tileBackPath)));
		}
		
		temp = logica.getPendentesDestruir();
		while (temp.size() > 0) {
			tiles.get(temp.pop()).setEnabled(false);
		}
	
		turno = !turno;
		
		if (turno) {
			whoIsPlaying.setText(strWhoIsPlaying);
		} else {
			whoIsPlaying.setText(strPlayer2Playing);
		}
		
		if (!logica.isRunning()) {
			JOptionPane.showMessageDialog(null, strDerrota);
			System.exit(0);
		}
		
		if (!logica.verificarTesouros(1)) {
			treasures[0].setIcon(new ImageIcon(Main.class.getResource(earthStoneOffPath)));
		}
		if (!logica.verificarTesouros(2)) {
			treasures[1].setIcon(new ImageIcon(Main.class.getResource(statueWindOffPath)));
		}
		if (!logica.verificarTesouros(3)) {
			treasures[2].setIcon(new ImageIcon(Main.class.getResource(crystalFireOffPath)));
		}
		if (!logica.verificarTesouros(4)) {
			treasures[3].setIcon(new ImageIcon(Main.class.getResource(oceanChaliceOffPath)));
		}
		
		refreshPositions();
		
		add(new JLabel());
	}
	
	public void iniciarPartidaRede() {
		createInterface_Board();
	}

	public void receberMensagemRede(Game mensagem) {
		logica = mensagem;
		add(new JLabel());
		refresh();
	}

}

