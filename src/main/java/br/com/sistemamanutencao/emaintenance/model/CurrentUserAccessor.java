package br.com.sistemamanutencao.emaintenance.model;

import java.io.Serializable;

import br.com.sistemamanutencao.emaintenance.model.entity.SpringUserAccessor;

public class CurrentUserAccessor implements Serializable {
	
	private static final long serialVersionUID = -4805391275690575974L;

	private static UserAccessor _accessor;

	public CurrentUserAccessor() {
		_accessor = new SpringUserAccessor();
	}

	public User getUser() {
		return _accessor.getUser();
	}

	public static void UseTestingAccessor(User user) {
		_accessor = new TestUserAccessor(user);
	}
}