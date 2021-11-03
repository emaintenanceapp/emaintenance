package br.com.sistemamanutencao.emaintenance.exception;

public class ClienteCadastradoException extends RuntimeException {

	private static final long serialVersionUID = -1342941994832559042L;

	public ClienteCadastradoException( String nome ){
        super("Cliente "+ nome +" já cadastrado!");
    }
}
