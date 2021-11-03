package br.com.sistemamanutencao.emaintenance.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistemamanutencao.emaintenance.model.entity.Manutencao;
import br.com.sistemamanutencao.emaintenance.repository.ManutencaoRepository;

@Service
public class ManutencaoService {
	
	@Autowired
	private ManutencaoRepository manutencaoRepository;
    
    public List<Manutencao> findEquipamentoByUser(Long userId) {
		List<Manutencao> manutencoes = manutencaoRepository.findManutencaoByUser(userId);
		return manutencoes.stream().collect(Collectors.toList());
    }
}
