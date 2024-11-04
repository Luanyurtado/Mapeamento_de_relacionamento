package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Fornecedor;
import com.example.demo.entities.Medicamento;
import com.example.demo.repositories.FornecedorRepository;
import com.example.demo.repositories.MedicamentoRepository;

@Service
public class MedicamentoService {

	private final MedicamentoRepository medicamentoRepository;
	private final FornecedorRepository fornecedorRepository;

	@Autowired
	public MedicamentoService(MedicamentoRepository medicamentoRepository, FornecedorRepository fornecedorRepository) {
		this.medicamentoRepository = medicamentoRepository;
		this.fornecedorRepository = fornecedorRepository;
	}

	public Medicamento salvarMedicamento(Medicamento medicamento) {
		if (medicamento.getFornecedor() != null && medicamento.getFornecedor().getId() != null) {
			Optional<Fornecedor> fornecedor = fornecedorRepository.findById(medicamento.getFornecedor().getId());
			if (fornecedor.isPresent()) {
				medicamento.setFornecedor(fornecedor.get());
				return medicamentoRepository.save(medicamento);
			} else {
				throw new RuntimeException("Fornecedor não encontrado");
			}
		} else {
			throw new RuntimeException("ID do fornecedor é obrigatório");
		}
	}

	public Medicamento buscarMedicamentoPorId(Long id) {
		return medicamentoRepository.findById(id).orElse(null);
	}

	public List<Medicamento> buscarTodosMedicamentos() {
		return medicamentoRepository.findAll();
	}

	public void excluirMedicamento(Long id) {
		medicamentoRepository.deleteById(id);
	}

	public Medicamento atualizarMedicamento(Long id, Medicamento medicamentoAtualizado) {
		Optional<Medicamento> medicamentoExistente = medicamentoRepository.findById(id);
		if (medicamentoExistente.isPresent()) {
			Medicamento medicamento = medicamentoExistente.get();
			medicamento.setNome(medicamentoAtualizado.getNome());
			medicamento.setBula(medicamentoAtualizado.getBula());
			medicamento.setDataValidade(medicamentoAtualizado.getDataValidade());
			if (medicamentoAtualizado.getFornecedor() != null) {
				medicamento.setFornecedor(medicamentoAtualizado.getFornecedor());
			}
			return medicamentoRepository.save(medicamento);
		} else {
			return null;
		}

	}

}