package forbidden_insland;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Treasure_Card implements Jogada {

	private static final long serialVersionUID = 1470838993074483152L;
	
	protected int code;
	
	public Treasure_Card(int code) {
    	this.code = code;
	}
	
	public int getCardID() {
		return code;
	}
}
