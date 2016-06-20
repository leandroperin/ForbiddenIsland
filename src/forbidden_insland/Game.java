package forbidden_insland;

import java.util.Stack;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Game implements Jogada {

	private static final long serialVersionUID = 4970159161693823009L;
	
	private static final int level = 1;
	private static final int numActions = 4;
	
	protected Board tabuleiro;
	protected Personagem jogador1;
	protected Personagem jogador2;
	protected boolean turno;
	protected boolean alagar;
	protected boolean running;
	protected boolean win;
	protected int nivel;
	protected int acoes;
	
	public Game() {		
		initComponents();
	}
	
	public void initComponents() {
		tabuleiro = new Board();
		
		jogador1 = new Personagem();
		jogador2 = new Personagem();
		jogador1 = tabuleiro.setPosInicio(jogador1, true);
		jogador2 = tabuleiro.setPosInicio(jogador2, false);
		
		turno = true;
		running = true;
		win = false;
		
		nivel = level;
		acoes = numActions;
	}
	
	public boolean fazerMovimento(int destino) {
		boolean mov;
		
		if (turno) {
			if (acoes > 0) {
				mov =  tabuleiro.moverPersonagem(jogador1, destino);
				if (mov)
					acoes--;
				return mov;
			} else {
				return false;
			}
		} else {
			if (acoes > 0) {
				mov =  tabuleiro.moverPersonagem(jogador2, destino);
				if (mov)
					acoes--;
				return mov;
			} else {
				return false;
			}
		}
	}
	
	public boolean endTurn() {
		boolean resp;
		
		if (turno) {
			resp = tabuleiro.popCartaTreasure(jogador1);
			
			if (resp) {
				nivel++;
			}
			
			int i = 0;
			for (; i < nCards(); i++) {
				if (nivel == 10 || tabuleiro.popFlood(jogador1.getPosition(), jogador2.getPosition())) {
					derrota();
				}
			}
		} else {
			resp = tabuleiro.popCartaTreasure(jogador2);
			
			if (resp) {
				nivel++;
			}
			
			int i = 0;
			for (; i < nCards(); i++) {
				if (nivel == 10 || tabuleiro.popFlood(jogador1.getPosition(), jogador2.getPosition())) {
					derrota();
				}
			}
		}
		
		acoes = 4;
		turno = !turno;
		alagar = resp;
		return resp;
	}
	
	public void derrota() {
		running = false;
	}
	
	public boolean getAlagar() {
		return alagar;
	}
	
	public int getPlayerPosition(int jogador) {
		if (jogador == 1) {
			return jogador1.getPosition();
		}
		return jogador2.getPosition();
	}
	
	public int pegarTesouro() {
		int resposta;
		
		if (acoes > 0) {
			if (turno)
				resposta = tabuleiro.pegarTesouro(jogador1);
			else
				resposta = tabuleiro.pegarTesouro(jogador2);
			
			if (resposta != -1) {
				acoes--;
				return resposta;
			}
		}
		
		return -1;
	}
	
	public boolean restaurarTerreno(int alvo) {
		if (acoes > 0) {
			if (turno) {
				if (tabuleiro.recuperarTerreno(alvo, jogador1)) {
					acoes--;
					return true;
				}
			return false;
			} else {
				if (tabuleiro.recuperarTerreno(alvo, jogador2)) {
					acoes--;
					return true;
				}
			return false;
			}
		}
		
		return false;
	}
	
	public boolean verificarTesouros(int tipo) {
		return tabuleiro.verificarTesouros(tipo);
	}
	
	public boolean tentarEscapar() {
		if (tabuleiro.tentarEscapar(jogador1, jogador2)) {
			running = false;
			win = true;
			return true;
		}
		
		return false;
	}
	
	public Stack<Integer> getPendentesAlagar() {
		return tabuleiro.getPendentesAlagar();
	}
	
	public Stack<Integer> getPendentesRestaurar() {
		return tabuleiro.getPendentesRestaurar();
	}
	
	public Stack<Integer> getPendentesDestruir() {
		return tabuleiro.getPendentesDestruir();
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public int getCardQuantity(boolean player, int card) {
		if (player) {
			return jogador1.verificarNCartas(card);
		}
		return jogador2.verificarNCartas(card);
	}
	
	public int nCards() {
		if (nivel > 3) {
			return 1;
		} else if (nivel < 6) {
			return 2;
		} else if (nivel < 8) {
			return 3;
		}
		return 4;
	}
	
}
