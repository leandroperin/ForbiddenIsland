package rede;

import javax.swing.JOptionPane;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import forbidden_insland.Game;
import forbidden_insland.Interface_Board;

public class AtorRede implements OuvidorProxy {

	private static final long serialVersionUID = 4670583759347725514L;

	private Interface_Board atorJogador;
	
	private Proxy proxy;
	
	private boolean vez = false;
	
	public AtorRede(Interface_Board atorJogador) {
		super();
		this.atorJogador = atorJogador;
		
		proxy = Proxy.getInstance();
		proxy.addOuvinte(this);
	}
	
	public boolean conectar(String nome, String servidor) {
		try {
			proxy.conectar(servidor, nome);
			return true;
		} catch (JahConectadoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (NaoPossivelConectarException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (ArquivoMultiplayerException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	public void iniciarPartidaRede() {
		try {
			proxy.iniciarPartida(2);
		} catch (NaoConectadoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void iniciarNovaPartida(Integer posicao) {
		if (posicao == 1) {
			vez = false;
		} else if (posicao == 2) {
			vez = true;
		}
		
		atorJogador.iniciarPartidaRede();
	}
	
	public void enviarJogada(Game mensagem) {
		Mensagem msg = new Mensagem(mensagem);
		try {
			proxy.enviaJogada(msg);
			vez = false;
		} catch (NaoJogandoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public void receberJogada(Jogada jogada) {
		Mensagem msg = (Mensagem) jogada;
		vez = true;
		atorJogador.receberMensagemRede(msg.getMensagem());
	}
	
	public void desconectar() {
		try {
			proxy.desconectar();
		} catch (NaoConectadoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void finalizarPartidaComErro(String message) {
		JOptionPane.showMessageDialog(null, message);
		System.exit(0);
	}

	@Override
	public void receberMensagem(String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tratarConexaoPerdida() {
		JOptionPane.showMessageDialog(null, "Conexão Perdida!");
		System.exit(0);
	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		//JOptionPane.showMessageDialog(null, "Partida não iniciada!");
		//System.exit(0);
	}

	public boolean minhaVez() {
		return vez;
	}

}
