package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {

		cadastrarProduto();
		Long id = 1l;
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());

		List<Produto> todos = produtoDao.buscarTodos();
		todos.forEach(p2 -> System.out.println(p2.getNome()));

		List<Produto> todos2 = produtoDao.buscarPorNome("Xiaomi Redmi");
		todos2.forEach(p2 -> System.out.println(p2.getNome()));

		List<Produto> todos3 = produtoDao.buscarPorNomeDaCategoria("CELULARES");
		todos3.forEach(p2 -> System.out.println(p2.getNome()));

		BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("XPTO");

		System.out.println("Preco do produto: " + precoDoProduto);
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");

		Produto celular = new Produto("teste", "teste", new BigDecimal("800"), celulares);

		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(celulares);
		celulares.setNome("XPTO1");

		em.flush();
		em.clear();
		celulares = em.merge(celulares);
		celulares.setNome("1234");
		em.flush();
		em.clear();
//		em.remove(celulares);
//		em.flush();

//		ProdutoDao produtoDao = new ProdutoDao(em);
//		CategoriaDao categoriaDao = new CategoriaDao(em);
//		em.getTransaction().begin();
//		categoriaDao.cadastrar(celulares);
//		produtoDao.cadastrar(celular);

		em.getTransaction().commit();
		em.close();
	}

}
