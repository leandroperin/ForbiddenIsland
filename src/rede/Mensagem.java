package rede;

import br.ufsc.inf.leobr.cliente.Jogada;
import forbidden_insland.Game;

public class Mensagem implements Jogada {

	private static final long serialVersionUID = -1174520272312829815L;
	
	private Game mensagem;
	
	public Mensagem(Game mensagem) {
		super();
		this.mensagem = mensagem;
	}
	
	public Game getMensagem() {
		return mensagem;
	}
	
}
