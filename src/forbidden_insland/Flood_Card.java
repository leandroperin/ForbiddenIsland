package forbidden_insland;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Flood_Card implements Jogada {

	private static final long serialVersionUID = -7214801695796533787L;
	
	protected int code;
	
	public Flood_Card(int code) {
    	this.code = code;
	}
	
	public int getCardID() {
		return code;
	}
}
