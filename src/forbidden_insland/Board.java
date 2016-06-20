package forbidden_insland;

import java.util.Random;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Board implements Jogada {

	private static final long serialVersionUID = 7597501043349484495L;

	protected Tile[] tiles;	
	protected Stack<Treasure_Card> treasureDeck;
	protected Stack<Flood_Card> floodDeck;
	protected Stack<Flood_Card> descarteFlood; 
	protected Stack<Treasure_Card> descarteTreasure;
	protected Stack<Integer> pendentesRestaurar;
	protected Stack<Integer> pendentesAlagar;
	protected Stack<Integer> pendentesDestruir;
	protected int[] tileCodes;
	protected int[] treasureCodes;
	protected int[][] matriz;
	protected boolean tesouro1;
	protected boolean tesouro2;
	protected boolean tesouro3;
	protected boolean tesouro4;
	protected boolean t1;
	protected boolean t2;
	protected boolean t3;
	protected boolean t4;
	protected int nivelAgua;
	
	public Board() {
		initComponents();
		
		createTiles();
		createFloodDeck();
		createTreasureDeck();

		this.buildMatriz();
		
		descarteFlood = new Stack<Flood_Card>();
		descarteTreasure = new Stack<Treasure_Card>();
		pendentesRestaurar = new Stack<Integer>();
		pendentesAlagar = new Stack<Integer>();
		pendentesDestruir = new Stack<Integer>();
	}
	
	public void initComponents() {
		tesouro1 = true;
		tesouro2 = true;
		tesouro3 = true;
		tesouro4 = true;
		t1 = false;
		t2 = false;
		t3 = false;
		t4 = false;
		nivelAgua = 1;
	}
	
	public void createTiles() {
		tiles = new Tile[24];
		tileCodes = new int[24];
		
		int i = 0;
		for (; i < 24; i++) {
			tiles[i] = new Tile(i);
			tileCodes[i] = i;
		}
	}
	
	public void createFloodDeck() {		
		floodDeck = new Stack<Flood_Card>();
		
		shuffleArray(tileCodes);
		
		int i = 0;
		for (; i < 24; i++) {
			Flood_Card card = new Flood_Card(tileCodes[i]);
			floodDeck.push(card);
		}
	}
	
	public void createTreasureDeck() {
		treasureDeck = new Stack<Treasure_Card>();
		treasureCodes = new int[36];
		
		int i = 0;
		for (; i < 8; i++) {
			treasureCodes[i] = 1;
		}
		for (; i < 16; i++) {
			treasureCodes[i] = 2;
		}
		for (; i < 24; i++) {
			treasureCodes[i] = 3;
		}
		for (; i < 32; i++) {
			treasureCodes[i] = 4;
		}
		for (; i < 36; i++) {
			treasureCodes[i] = 0;
		}
		
		shuffleArray(treasureCodes);
		
		i = 0;
		for (; i < 36; i++) {
			Treasure_Card card = new Treasure_Card(treasureCodes[i]);
			treasureDeck.push(card);
		}
	}
	
	public void buildMatriz() {
		matriz = new int[6][6];
		
		matriz[0][0] = -1;
		matriz[0][1] = -1;
		matriz[0][2] = 0;
		matriz[0][3] = 1;
		matriz[0][4] = -1;
		matriz[0][5] = -1;
		
		matriz[1][0] = -1;
		matriz[1][1] = 2;
		matriz[1][2] = 3;
		matriz[1][3] = 4;
		matriz[1][4] = 5;
		matriz[1][5] = -1;
		
		matriz[2][0] = 6;
		matriz[2][1] = 7;
		matriz[2][2] = 8;
		matriz[2][3] = 9;
		matriz[2][4] = 10;
		matriz[2][5] = 11;
		
		matriz[3][0] = 12;
		matriz[3][1] = 13;
		matriz[3][2] = 14;
		matriz[3][3] = 15;
		matriz[3][4] = 16;
		matriz[3][5] = 17;
		
		matriz[4][0] = -1;
		matriz[4][1] = 18;
		matriz[4][2] = 19;
		matriz[4][3] = 20;
		matriz[4][4] = 21;
		matriz[4][5] = -1;
		
		matriz[5][0] = -1;
		matriz[5][1] = -1;
		matriz[5][2] = 22;
		matriz[5][3] = 23;
		matriz[5][4] = -1;
		matriz[5][5] = -1;	
	}
	
	public void shuffleArray(int[] ar) {
		Random rnd = ThreadLocalRandom.current();
		int index;
		int a;
		int i = ar.length - 1;
		for (; i > 0; i--) {
			index = rnd.nextInt(i + 1);
			a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}
	
	public Tile getTile(int pos) {
		return tiles[pos];
	}
	
	public void acrescentarNivelAgua() {
		while (!descarteFlood.isEmpty()) {	
			floodDeck.push(descarteFlood.pop());
		}
		if (nivelAgua < 9) {
			nivelAgua++;
		}
	}
	
	public boolean popFlood(int pos1, int pos2) {
		if (floodDeck.isEmpty()) {
			descarteFlood.clear();
			createFloodDeck();
		}
		
		Flood_Card card = floodDeck.pop();
		
		int i = card.getCardID();
		if (tiles[i].alagar()) {
			pendentesDestruir.push(new Integer(i));
			if (pos1 == i || pos2 == i || i == 0) {
				return true;
			}
			if (i == 17 || i == 22) {
				if (t1 && tesouro1) {
					return true;
				}
				t1 = true;
			}
			if (i == 5 || i == 12) {
				if (t2 && tesouro2) {
					return true;
				}
				t2 = true;
			}
			if (i == 21 || i == 2) {
				if (t3 && tesouro3) {
					return true;
				}
				t3 = true;
			}
			if (i == 9 || i == 18) {
				if (t4 && tesouro4) {
					return true;
				}
				t4 = true;
			}
			this.atualizarMatriz(i);
		} else {
			pendentesAlagar.push(new Integer(i));
			descarteFlood.push(card);
		}
		return false;
	}
	
	public void pushDescarteFlood(Flood_Card card) {
		descarteFlood.push(card);
	}
	
	public void pushDescarteTreasure(Treasure_Card card) {
		descarteTreasure.push(card);
	}
	
	public boolean popCartaTreasure(Personagem player) {
		if (treasureDeck.isEmpty()){
			createTreasureDeck();
		}
		
		Treasure_Card card = treasureDeck.pop();
		
		if (card.getCardID() != 0) {
			player.darCarta(card);
			return false;
		}
		this.acrescentarNivelAgua();
		this.pushDescarteTreasure(card);
		return true;	
	}
	
	public int pegarTesouro(Personagem player) { 
		if (player.getPosition() == 17 || player.getPosition() == 22 && tesouro1) {
			if (player.verificarCartas(1)) {
				player.darTesouro(1);
				tesouro1 = false;
				return 0;
			}
		} else if (player.getPosition() == 5 || player.getPosition() == 12 && tesouro2) {
			if (player.verificarCartas(2)) {
				player.darTesouro(2);
				tesouro2 = false;
				return 1;
			}
		} else if (player.getPosition() == 2 || player.getPosition() == 21 && tesouro3) {
			if (player.verificarCartas(3)) {
				player.darTesouro(3);
				tesouro3 = false;
				return 2;
			}
		} else if (player.getPosition() == 9 || player.getPosition() == 18 && tesouro4) {
			if (player.verificarCartas(4)) {
				player.darTesouro(4);
				tesouro4 = false;
				return 3;
			}
		}
		return -1;
	}
	
	public boolean verificarTesouros (int tipo) {
		switch (tipo) {
		case 1:
			return tesouro1;
		case 2:
			return tesouro2;
		case 3:
			return tesouro3;
		default:
			return tesouro4;
		}
	}
	
	public boolean moverPersonagem(Personagem player, int blocoDestino) {
		return player.mover(matriz, blocoDestino);
	}
	
	public Personagem setPosInicio(Personagem player, boolean qual) {
		if (qual)
			player.setPos(2,2, 8);
		else
			player.setPos(3,3,15);
		return player;
	}
	
	public boolean recuperarTerreno(int blocoDestino, Personagem player){
		if (player.verificarMovimento(matriz, blocoDestino)) {
			tiles[blocoDestino].recuperar();
			pendentesRestaurar.push(new Integer(blocoDestino));
			return true;
		}
		return false;
	}
	
	public boolean tentarEscapar(Personagem p1, Personagem p2){
		if (p1.getPosition() == 0 && p2.getPosition() == 0) {
			if (!tesouro1 && !tesouro2 && !tesouro3 && !tesouro4) {
				return true;
			}
		}
		return false;
	}
	
	public void atualizarMatriz(int id) {
		boolean done = false;
		
		int i = 0;
		int j;
		
		for (; i < 6; i++) {
			j = 0;
			for (; j < 6; j++) {
				if (matriz[i][j] == id) {
					matriz[i][j] = -1;
					done = true;
					break; 
				}
			}
			if (done)
				break;
		}
	}
	
	public Stack<Integer> getPendentesAlagar() {
		Stack<Integer> temp = new Stack<Integer>(); 
		int t;
		
		while (!pendentesAlagar.isEmpty()) {
			t = pendentesAlagar.pop();
			temp.push(t);
		}
		
		return temp;
	}
	
	public Stack<Integer> getPendentesDestruir() {
		Stack<Integer> temp = new Stack<Integer>(); 
		int t;
		
		while (!pendentesDestruir.isEmpty()) {
			t = pendentesDestruir.pop();
			temp.push(t);
		}
		
		return temp;
	}
	
	public Stack<Integer> getPendentesRestaurar() {
		Stack<Integer> temp = new Stack<Integer>(); 
		int t;
		
		while (!pendentesRestaurar.isEmpty()) {
			t = pendentesRestaurar.pop();
			temp.push(t);
		}
		
		return temp;
	}
}
