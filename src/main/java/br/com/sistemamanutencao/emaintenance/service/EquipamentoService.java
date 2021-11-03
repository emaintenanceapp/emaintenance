package br.com.sistemamanutencao.emaintenance.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistemamanutencao.emaintenance.model.entity.Equipamento;
import br.com.sistemamanutencao.emaintenance.repository.EquipamentoRepository;

@Service
public class EquipamentoService {
	
	@Autowired
	private EquipamentoRepository equipamentoRepository;
    
    public List<Equipamento> findEquipamentoByuser(Long userId) {
		List<Equipamento> equipamentos = equipamentoRepository.findEquipamentoByUser(userId);
		return equipamentos.stream().collect(Collectors.toList());
    }

}
