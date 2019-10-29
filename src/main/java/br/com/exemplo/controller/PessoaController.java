package br.com.exemplo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.exemplo.entity.Pessoa;
import br.com.exemplo.exception.ResourceNotFoundException;
import br.com.exemplo.service.PessoaService;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> findAll() {
		return new ResponseEntity<>(pessoaService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pessoa> findById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
		Pessoa pessoa = pessoaService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pessoa nao encontrada com este id " + id));
		return ResponseEntity.ok().body(pessoa);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Pessoa> save(@Valid @RequestBody Pessoa pessoa) {
		pessoaService.save(pessoa);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Pessoa> update(@PathVariable(value = "id") Integer id,
			@Valid @RequestBody Pessoa pessoaUpdate) throws ResourceNotFoundException {

		Pessoa pessoa = pessoaService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pessoa nao encontrada com este id " + id));
		pessoa.setNome(pessoaUpdate.getNome());
		pessoa.setIdade(pessoaUpdate.getIdade());
		final Pessoa updatedPessoa = pessoaService.save(pessoa);
		return ResponseEntity.ok(updatedPessoa);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Map<String, Boolean> deleteById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {

		Pessoa pessoa = pessoaService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pessoa nao encontrada com este id: " + id));

		pessoaService.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deletado", Boolean.TRUE);
		return response;

	}

}
