package forbidden_insland;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Card implements Jogada  {

	private static final long serialVersionUID = 507370343802860432L;
	
	protected int code;
	
	public Card(int code) {
    	this.code = code;
	}
	
	public int getCardID() {
		return code;
	}

}
