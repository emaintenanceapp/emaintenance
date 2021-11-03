package br.com.sistemamanutencao.emaintenance.exception;

public class UserCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 32999804944596676L;

	public UserCadastradoException( String login ){
        super("Usuário já cadastrado para o login " + login);
    }
}
