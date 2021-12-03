package br.com.sistemamanutencao.emaintenance.exception;

public class ClienteUserException extends RuntimeException {

	private static final long serialVersionUID = 1529611637011553690L;

	public ClienteUserException(Integer id) {
		super("Usuário " + id + " não encontrado!");
	}

	public ClienteUserException(String string) {
		super("Usuário " + string + " não encontrado!");
	}
}
