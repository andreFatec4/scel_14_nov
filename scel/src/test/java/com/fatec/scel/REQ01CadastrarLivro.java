package com.fatec.scel;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class REQ01CadastrarLivro {

	@Autowired
	LivroRepository repository;
	private Validator validator;
	private ValidatorFactory validatorFactory;

	@Test
	public void CT01CadastrarLivroComSucesso() {
		// dado que não existe nenhum livro cadastrado
		repository.deleteAll();
		// quando o usuario inclui um livro valido
		repository.save(new Livro("1111", "Teste de Software", "Delamaro"));
		// então - a quantidade de livros cadastrados é igual 1
		List<Livro> todos = (List<Livro>) repository.findAll();
		assertEquals(1, todos.size());
		repository.deleteAll();
	}
	@Test
	public void CT02DeveDetectarTituloInvalido() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// dado que o titulo do livro esta invalido
		Livro livro = new Livro("3333", "", "Delamaro");
		// when:
		Set<ConstraintViolation<Livro>> violations = validator.validate(livro);
		// then:
		assertEquals(violations.size(), 1);
		assertEquals("O titulo deve ser preenchido", violations.iterator().next().getMessage());
	}

	@Test
	public void CT03CadastrarLivroISBNjaCadastrado() {
		// dado que não existe nenhum livro cadastrado
		repository.deleteAll();
		// quando o usuario inclui um livro que ja esta cadastrado
		Livro livro = new Livro("1111", "Teste de Software", "Delamaro");
		repository.save(livro);
		try {
			repository.save(livro);
		} catch (Exception e) {
			System.out.println("erro ===================" + e.getMessage());
		}

		// então a quantidade de livros continua 1
		List<Livro> todos = (List<Livro>) repository.findAll();
		assertEquals(1, todos.size());
		repository.deleteAll();
	}
	

}
