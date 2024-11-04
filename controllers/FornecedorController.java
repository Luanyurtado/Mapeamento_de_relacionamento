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

import com.example.demo.entities.Fornecedor;
import com.example.demo.services.FornecedorService;


@RestController
@RequestMapping("/Fornecedor")
public class FornecedorController {

	private final FornecedorService fornecedorService;

	@Autowired
	public FornecedorController(FornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;
	}

	@PostMapping("/criar")
	public Fornecedor salvar(@RequestBody Fornecedor fornecedor) {
		return fornecedorService.salvarFornecedor(fornecedor);
	}

	@GetMapping
	public List<Fornecedor> buscarTodos() {
		return fornecedorService.buscarTodosFornecedores();
	}

	@GetMapping("/{id}")
	public Fornecedor buscarPorId(@PathVariable Long id) {
		return fornecedorService.buscarFornecedorPorId(id);
	}

	@DeleteMapping("/{id}")
	public void deletarFornecedor(@PathVariable Long id) {
		fornecedorService.excluirFornecedor(id);
	}

	@PutMapping
	public ResponseEntity<Fornecedor> atualizarFornecedor(@PathVariable Long id,
			@RequestBody Fornecedor fornecedor) {
		Fornecedor fornecedorAtualizado = fornecedorService.atualizarFornecedor(id, fornecedor);
		if (fornecedorAtualizado != null) {
			return ResponseEntity.ok(fornecedorAtualizado);
		} else {
			return null;
		}
	}
}