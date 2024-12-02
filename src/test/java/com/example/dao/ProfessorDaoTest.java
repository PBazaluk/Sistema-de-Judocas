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

public class ProfessorDaoTest {
	
	private static DAO<Professor> professorDao;
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
		
		professorDao = new DAOImpl<Professor>(Professor.class);
	}

	public static void clearDatabase(){
		List<Professor> all = professorDao.list();
		for (Professor each : all) {
			professorDao.delete(each);
		}
		assertEquals(0, professorDao.list().size());
	}
	
	
	@Test
	public void  SalvarProfessorTest() throws Exception{
		clearDatabase();
		
		professorDao.save(professor);
		assertEquals("036.464.453-27", professorDao.get(professor).getFiliado().getCpf());
		assertEquals("Professor", professorDao.get(professor).getFiliado().getNome());
		assertEquals("Dirceu", professorDao.get(professor).getFiliado().getEndereco().getBairro());
		assertEquals("Teresina", professorDao.get(professor).getFiliado().getEndereco().getCidade());
	}
	
	@Test
	public void updateProfessorTest() throws Exception{
		clearDatabase();
		assertEquals(0, professorDao.list().size());
		
		professorDao.save(professor);
		assertEquals(1, professorDao.list().size());
		assertEquals("Professor", professor.getFiliado().getNome());
		
		Professor p1 = professorDao.get(professor);
		p1.getFiliado().setNome("TesteUpdateProf");
		professorDao.save(p1);
		
		Professor p2 = professorDao.get(p1);
		assertEquals("TesteUpdateProf", p2.getFiliado().getNome());
		assertEquals(1, professorDao.list().size());
	}
	
	@Test
	public void ListarEAdicionarProfessoresTest(){
		int qtd = professorDao.list().size();
		
		Professor p1 = new Professor();
		p1.setFiliado(filiadoProf);
		professorDao.save(p1);
		assertEquals(qtd+1, professorDao.list().size());
		
		Professor p2 = new Professor();
		p2.setFiliado(filiadoProf);
		professorDao.save(p2);
		assertEquals(qtd+2, professorDao.list().size());
		
		Professor p3 = new Professor();
		p3.setFiliado(filiadoProf);
		professorDao.save(p3);
		assertEquals(qtd+3, professorDao.list().size());
		
		Professor p4 = new Professor();
		p4.setFiliado(filiadoProf);
		professorDao.save(p4);
		assertEquals(qtd+4, professorDao.list().size());
		
		clearDatabase();
		assertEquals(0, professorDao.list().size());
		
		Professor p5 = new Professor();
		p5.setFiliado(filiadoProf);
		professorDao.save(p5);
		assertEquals(1, professorDao.list().size());
	}
	
	@Test
	public void SearchProfessorTest() throws Exception{
		clearDatabase();
		professorDao.save(professor);
		
		Filiado f = new Filiado();
		f.setNome("Professor");
		Professor a = new Professor();
		a.setFiliado(f);
		
		List<Professor> result = professorDao.search(a);
		assertEquals(1, result.size());
		assertEquals("036.464.453-27", result.get(0).getFiliado().getCpf());
		
		clearDatabase();
		assertEquals(0, professorDao.search(a).size());
	}
	
	@AfterClass
	public static void closeDatabase(){
		clearDatabase();
		DatabaseManager.close();
	}
	
}