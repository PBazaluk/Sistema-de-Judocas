package com.example.model.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import com.example.model.beans.Aluno;
import com.example.model.beans.Endereco;
import com.example.model.beans.Entidade;
import com.example.model.beans.Filiado;
import com.example.model.beans.Professor;
import com.example.util.DatabaseManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class EntidadeDaoTest {
	
	private static DAO<Aluno> alunoDao;
	private static DAO<Professor> professorDao;
	private static DAO<Entidade> entidadeDao;
	private static Aluno aluno;
	private static Entidade entidade;
	private static Endereco endereco;
	private static Filiado f1;
	private static Filiado filiadoProf;
	private static Professor professor;
	
	@BeforeClass
	public static void setUp(){
		DatabaseManager.setEnviroment(DatabaseManager.TEST);
		f1 = new Filiado();
		f1.setNome("Aécio");
		f1.setCpf("036.464.453-27");
		f1.setDataNascimento(new Date());
		f1.setDataCadastro(new Date());
		f1.setId(1332L);
		
		endereco = new Endereco();
		endereco.setBairro("Dirceu");
		endereco.setCep("64078-213");
		endereco.setCidade("Teresina");
		endereco.setEstado("PI");
		endereco.setRua("Rua Des. Berilo Mota");
		
		filiadoProf = new Filiado();
		filiadoProf.setNome("Professor");
		filiadoProf.setCpf("036.464.453-27");
		filiadoProf.setDataNascimento(new Date());
		filiadoProf.setDataCadastro(new Date());
		filiadoProf.setId(3332L);
		filiadoProf.setEndereco(endereco);
		
		professor = new Professor();
		professor.setFiliado(filiadoProf);
		
		entidade = new Entidade();
		entidade.setEndereco(endereco);
		entidade.setNome("Academia 1");
		entidade.setTelefone1("(086)1234-5432");
		
		aluno = new Aluno();
		aluno.setFiliado(f1);
		aluno.setProfessor(professor);
		aluno.setEntidade(entidade);
		
		alunoDao = new DAOImpl<Aluno>(Aluno.class);
		professorDao = new DAOImpl<Professor>(Professor.class);
		entidadeDao = new DAOImpl<Entidade>(Entidade.class);
	}

	public static void clearDatabase(){
		List<Entidade> all = entidadeDao.list();
		for (Entidade each : all) {
			entidadeDao.delete(each);
		}
		assertEquals(0, entidadeDao.list().size());
	}
	
	
	@Test
	public void  SalvarEntidadeTest() throws Exception{
		clearDatabase();
		
		entidadeDao.save(entidade);
		assertEquals("(086)1234-5432", entidadeDao.get(entidade).getTelefone1());
		assertEquals("Academia 1", entidadeDao.get(entidade).getNome());
		assertEquals("Dirceu", entidadeDao.get(entidade).getEndereco().getBairro());
		assertEquals("Teresina", entidadeDao.get(entidade).getEndereco().getCidade());
	}
	
	@Test
	public void updateEntidadeTest() throws Exception{
		clearDatabase();
		assertEquals(0, entidadeDao.list().size());
		
		entidadeDao.save(entidade);
		assertEquals(1, entidadeDao.list().size());
		assertEquals("Academia 1", entidade.getNome());
		
		Entidade e1 = entidadeDao.get(entidade);
		e1.setNome("TesteUpdateEntidade");
		entidadeDao.save(e1);
		
		Entidade e2 = entidadeDao.get(e1);
		assertEquals("TesteUpdateEntidade", e2.getNome());
		assertEquals(1, entidadeDao.list().size());
	}
	
	@Test
	public void SearchEntidadeTest() throws Exception{
		clearDatabase();
		
		entidadeDao.save(entidade);
		
		Entidade e = new Entidade();
		e.setNome("Academia 1");

		List<Entidade> result = entidadeDao.search(e);
		assertEquals(1, result.size());
		assertEquals("Teresina", result.get(0).getEndereco().getCidade());
		
		clearDatabase();
		assertEquals(0, entidadeDao.search(e).size());
	}
	
	// @AfterClass
	// public static void closeDatabase(){
	// 	clearDatabase();
	// 	DatabaseManager.close();
	// }
	
}