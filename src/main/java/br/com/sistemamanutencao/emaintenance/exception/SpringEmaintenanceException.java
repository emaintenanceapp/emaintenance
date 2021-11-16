package br.com.sistemamanutencao.emaintenance.exception;

public class SpringEmaintenanceException extends RuntimeException {

	private static final long serialVersionUID = -7143260395434762851L;

	public SpringEmaintenanceException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public SpringEmaintenanceException(String exMessage) {
        super(exMessage);
    }
}
