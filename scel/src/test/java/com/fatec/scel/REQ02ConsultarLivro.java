package com.fatec.scel;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;
@RunWith(SpringRunner.class)
@SpringBootTest
public class REQ02ConsultarLivro {
	@Autowired
	LivroRepository repository;
	@Test
	public void CT01ConsultarLivroPorISBNComSucesso() {
		//dado que o isbn 3333 esta cadastrado
		Livro livro1 = new Livro("3333", "Teste de Software", "Delamaro");
		repository.save(livro1);
		//quando o usurio consulta pelo isbn 
		Livro livro2 = repository.findByIsbn("3333");
		//o sistema retonra not null
		assertNotNull(livro2);
        assertTrue(livro1.equals(livro2));
		repository.deleteAll();
	}
	@Test
	public void CT02ConsultarLivroPorISBN_invalido() {
		//dado que o isbn 3333 esta cadastrado
		Livro livro1 = new Livro("3333", "Teste de Software", "Delamaro");
		repository.save(livro1);
		//quando o usurio consulta pelo isbn 
		Livro livro2 = repository.findByIsbn("4444");
		//o sistema retonra not null
		assertNull(livro2);
      	repository.deleteAll();
	}
}
