package br.com.exemplo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.exemplo.entity.Pessoa;
import br.com.exemplo.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	 public List<Pessoa> findAll(){
		 return pessoaRepository.findAll();
	 }
	 
	 public Optional<Pessoa> findById(Integer id){
		 return pessoaRepository.findById(id);
	 }
	
	 public Pessoa save(Pessoa pessoa) {
		 return pessoaRepository.save(pessoa);
	 }
	 
	 public void deleteById(Integer id) {
		 pessoaRepository.deleteById(id);
	 }
	 
	 
	
}
