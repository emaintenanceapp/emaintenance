package br.com.sistemamanutencao.emaintenance.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = { "${app.security.cors.origin}" })
@RestController
@RequestMapping("/api/estrategias-manutencao")
public class EstrategiaManutencaoController {

}
