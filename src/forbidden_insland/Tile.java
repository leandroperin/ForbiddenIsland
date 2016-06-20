package forbidden_insland;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Tile implements Jogada {

	private static final long serialVersionUID = -4689900830730829345L;
	
	protected Personagem j1;
	protected Personagem j2;
	protected boolean status;
	protected boolean inGame;
	protected int type;
	protected int posX;
	protected int posY;
	
	public Tile(int tileCode) {
		inGame = true;
		status = false;
		
		type = tileCode;
	}
	
	public int getX() {
		return posX;
	}
	
	public int getY() {
		return posY;
	}
	
	public boolean avaliarStatus() {
		return status;
	}
	
	public boolean alagar() {
		if (status == false) {
			status = !status;
			return false;
		}
		inGame = false;
		return true;
	}
	
	public void recuperar() {
		if (status && inGame)
			status = false;
	}
	
	public int getType() {
		return type;
	}
	
	public Personagem getJogador(int per) {
		if (per == 1) {
			return j1;
		}
		return j2;
	}
	
	public boolean verificarJogadores() {
		if (j1 == null && j2 == null)
			return false;
		return true;
	}
	
	public void removerJogador(int per) {
		if (per == 1) {
			j1 = null;
		} else {
			j2 = null;
		}
	}
	
	
	
	
	
	
}
