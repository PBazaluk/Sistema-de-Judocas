package test.java.com.example;

import java.util.Date;

import com.example.model.beans.Aluno;
import com.example.model.beans.Endereco;
import com.example.model.beans.Entidade;
import com.example.model.beans.Filiado;
import com.example.model.beans.Professor;
import com.example.model.beans.Rg;
import com.example.util.DatabaseManager;
import org.junit.BeforeClass;

public class ModelIntegrationTests {
//	private static DAO<Aluno> alunoDao;
	private static Aluno aluno;
	private static Entidade entidade;
	private static Endereco endereco;
	private static Filiado filiadoAluno;
	private static Filiado filiadoProf;
	private static Professor professor;
	
	@BeforeClass
	public static void setUp(){
		DatabaseManager.setEnviroment(DatabaseManager.TEST);
		filiadoAluno = new Filiado();
		filiadoAluno.setNome("Aécio");
		filiadoAluno.setCpf("036.464.453-27");
		filiadoAluno.setDataNascimento(new Date());
		filiadoAluno.setDataCadastro(new Date());
		filiadoAluno.setId(1332L);
		filiadoAluno.setRg(new Rg("5026762-0", "SSP-PI"));
		
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
		aluno.setFiliado(filiadoAluno);
		aluno.setProfessor(professor);
		aluno.setEntidade(entidade);
		
//		alunoDao = new DAOImpl<Aluno>(Aluno.class);
	}
	
}
