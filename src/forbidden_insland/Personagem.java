package forbidden_insland;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Personagem implements Jogada {

	private static final long serialVersionUID = 5712457244647920170L;
	
	protected boolean tesouro1 = false;
	protected boolean tesouro2 = false;
	protected boolean tesouro3 = false;
	protected boolean tesouro4 = false;
	protected int posX;
	protected int posY;
	protected int pos;
	protected int nCartasTesouro1;
	protected int nCartasTesouro2;
	protected int nCartasTesouro3;
	protected int nCartasTesouro4;
	
	public Personagem() {
		
	}
	
	public boolean verificarMovimento(int[][] matriz, int blocoDestino) {
		if (blocoDestino != -1)	{
			if (pos !=  0 && pos!= 1 && matriz[posX-1][posY] == blocoDestino) {
				return true;
			}
			if (pos != 11 && pos != 17 && matriz[posX][posY+1] == blocoDestino) {
				return true;
			}
			if (pos != 22 && pos != 23 && matriz[posX+1][posY] == blocoDestino) {
				return true;
			}
			if (pos != 6 && pos != 12 && matriz[posX][posY-1] == blocoDestino) {
				return true;
			}
		}
		return false;
	}
	
	public boolean mover(int[][] matriz, int blocoDestino) {
		if (blocoDestino != -1)	{
			if (pos != 0 && pos!= 1 && matriz[posX-1][posY] == blocoDestino) {
				posX--;
				pos = blocoDestino;
				return true;
			}
			if (pos != 11 && pos != 17 && matriz[posX][posY+1] == blocoDestino) {
				posY++;
				pos = blocoDestino;
				return true;
			}
			if (pos != 22 && pos != 23 && matriz[posX+1][posY] == blocoDestino) {
				posX++;
				pos = blocoDestino;
				return true;
			}
			if (pos != 6 && pos != 12 && matriz[posX][posY-1] == blocoDestino) {
				posY--;
				pos = blocoDestino;
				return true;
			}
		}
		return false;
	}
	
	public int verificarNCartas(int tipo) {
		switch (tipo) {
		case 1 : 
			return nCartasTesouro1;
		case 2 : 
			return nCartasTesouro2;
		case 3 : 
			return nCartasTesouro3;
		default : 
			return nCartasTesouro4;
		}
	}
	
	public void darCarta(Treasure_Card carta) {
		switch (carta.getCardID()) {
			case 1:
				nCartasTesouro1++;
				break;
			case 2:
				nCartasTesouro2++;
				break;
			case 3:
				nCartasTesouro3++;
				break;
			case 4:
				nCartasTesouro4++;
				break;
		}
	}
	
	public boolean verificarCartas(int tipo) {
		switch (tipo) {
			case 1 : 
				if (nCartasTesouro1 >= 4) {
					nCartasTesouro1 -= 4;
					return true;
				} 
				return false;
			case 2 : 
				if (nCartasTesouro2 >= 4) {
					nCartasTesouro2 -= 4;
					return true;
				} 
				return false;
			case 3 : 
				if (nCartasTesouro3 >= 4) {
					nCartasTesouro3 -= 4;
					return true;
				}
				return false;
			default : 
				if(nCartasTesouro4 >= 4) {
					nCartasTesouro4 -= 4;
					return true;
				}
				return false;
		}
	}
	
	public void darTesouro(int tipo) {
		switch (tipo) {
			case 1:
				tesouro1 = true;
				break;
			case 2:
				tesouro2 = true;
				break;
			case 3:
				tesouro3 = true;
				break;
			case 4:
				tesouro4 = true;
				break;
		}
	}
	
	public void setPos(int x, int y, int bloc) {
		posX = x;
		posY = y;
		pos = bloc;
	}
	
	public int getPosition() {
		return pos;
	}
	
	
}
