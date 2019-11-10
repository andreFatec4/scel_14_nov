package com.fatec.scel;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;
@RunWith(SpringRunner.class)
@SpringBootTest
public class REQ04ExcluirLivro {
	@Autowired
	LivroRepository repository;
	@Test
	public void CT01CadastrarLivroComSucesso() {
		// dado que o isbn 1111 esta cadastrado
		repository.deleteAll();
		repository.save(new Livro("1111", "Teste de Software", "Delamaro"));
		// quando o usuario exclui o livro 1111
		Livro livro = repository.findByIsbn("1111");
		repository.deleteById(livro.getId());
		// então - a quantidade de livros cadastrados é igual 0
		List<Livro> todos = (List<Livro>) repository.findAll();
		assertEquals(0, todos.size());
	}
}
