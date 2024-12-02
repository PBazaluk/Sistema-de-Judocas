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

public class AlunoDaoTest {
	
	private static DAO<Aluno> alunoDao;
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
	}

	public static void clearDatabase(){
		List<Aluno> all = alunoDao.list();
		for (Aluno each : all) {
			alunoDao.delete(each);
		}
		assertEquals(0, alunoDao.list().size());
	}
	
	
	@Test
	public void  SalvarAlunoComAssociassoesTest() throws Exception{
		clearDatabase();
		
		alunoDao.save(aluno);
		assertEquals("036.464.453-27", alunoDao.get(aluno).getFiliado().getCpf());
		assertEquals("Aécio", alunoDao.get(aluno).getFiliado().getNome());
		assertEquals("Professor", alunoDao.get(aluno).getProfessor().getFiliado().getNome());
		assertEquals("Dirceu", alunoDao.get(aluno).getProfessor().getFiliado().getEndereco().getBairro());
		assertEquals("(086)1234-5432", alunoDao.get(aluno).getEntidade().getTelefone1());
		assertEquals("Academia 1", alunoDao.get(aluno).getEntidade().getNome());
	}
	
	@Test
	public void updateAlunoTest() throws Exception{
		clearDatabase();
		assertEquals(0, alunoDao.list().size());
		
		alunoDao.save(aluno);
		assertEquals(1, alunoDao.list().size());
		assertEquals("Aécio", aluno.getFiliado().getNome());
		
		Aluno a1 = alunoDao.get(aluno);
		a1.getFiliado().setNome("TesteUpdate");
		alunoDao.save(a1);
		
		Aluno a2 = alunoDao.get(a1);
		assertEquals("TesteUpdate", a2.getFiliado().getNome());
		assertEquals(1, alunoDao.list().size());
	}
	
	@Test
	public void ListarEAdicionarAlunosTest(){
		int qtd = alunoDao.list().size();
		
		Aluno a1 = new Aluno();
		a1.setFiliado(f1);
		alunoDao.save(a1);
		assertEquals(qtd+1, alunoDao.list().size());
		
		Aluno a2 = new Aluno();
		a2.setFiliado(f1);
		alunoDao.save(a2);
		assertEquals(qtd+2, alunoDao.list().size());
		
		Aluno a3 = new Aluno();
		a3.setFiliado(f1);
		alunoDao.save(a3);
		assertEquals(qtd+3, alunoDao.list().size());
		
		Aluno a4 = new Aluno();
		a4.setFiliado(f1);
		alunoDao.save(a4);
		assertEquals(qtd+4, alunoDao.list().size());
		
		clearDatabase();
		assertEquals(0, alunoDao.list().size());
		
		Aluno a5 = new Aluno();
		a5.setFiliado(f1);
		alunoDao.save(a5);
		assertEquals(1, alunoDao.list().size());
	}
	
	@Test
	public void SearchAlunoTest() throws Exception{
		clearDatabase();
		alunoDao.save(aluno);
		
		Filiado f = new Filiado();
		f.setNome("Aécio");
		Aluno a = new Aluno();
		a.setFiliado(f);
		
		List<Aluno> result = alunoDao.search(a);
		assertEquals(1, result.size());
		assertEquals("036.464.453-27", result.get(0).getFiliado().getCpf());
		
		clearDatabase();
		assertEquals(0, alunoDao.search(a).size());
	}
	
	// @AfterClass
	// public static void closeDatabase(){
	// 	clearDatabase();
	// 	DatabaseManager.close();
	// }
	
}