package br.com.sistemamanutencao.emaintenance.model;

import java.io.Serializable;

public class TestUserAccessor implements UserAccessor, Serializable {

	private static final long serialVersionUID = 6228716010247768643L;

	private static User _user;

	public TestUserAccessor(User user) {
		_user = user;
	}

	@Override
	public User getUser() {
		return _user;
	}

}
