package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Medicamento;
import com.example.demo.services.MedicamentoService;

@RestController
@RequestMapping("/Medicamento")
public class MedicamentoController {

	private final MedicamentoService medicamentoService;

	@Autowired
	public MedicamentoController(MedicamentoService medicamentoService) {
		this.medicamentoService = medicamentoService;
	}

	@PostMapping("/criar")
	public ResponseEntity<Medicamento> criarMedicamento(@RequestBody Medicamento medicamento) {
		Medicamento medicamentoSalvo = medicamentoService.salvarMedicamento(medicamento);
		if (medicamentoSalvo != null) {
			return ResponseEntity.ok(medicamentoSalvo);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping
	public List<Medicamento> buscarTodos() {
		return medicamentoService.buscarTodosMedicamentos();
	}

	@GetMapping("/{id}")
	public Medicamento buscarPorId(@PathVariable Long id) {
		return medicamentoService.buscarMedicamentoPorId(id);
	}

	@DeleteMapping("/{id}")
	public void deletarUsuario(@PathVariable Long id) {
		medicamentoService.excluirMedicamento(id);
	}

	@PutMapping
	public ResponseEntity<Medicamento> atualizarMedicamento(@PathVariable Long id,
			@RequestBody Medicamento medicamento) {
		Medicamento medicamentoAtualizado = medicamentoService.atualizarMedicamento(id, medicamento);
		if (medicamentoAtualizado != null) {
			return ResponseEntity.ok(medicamentoAtualizado);
		} else {
			return null;
		}
	}
}