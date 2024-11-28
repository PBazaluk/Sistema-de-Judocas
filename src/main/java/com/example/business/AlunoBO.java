package com.example.business;

import java.util.List;
import com.example.model.beans.Aluno;
//import org.fpij.jitakyoei.model.beans.Aluno;

public interface AlunoBO {
	public void createAluno(Aluno aluno) throws Exception;
	public void updateAluno(Aluno aluno) throws Exception;
	public List<Aluno> searchAluno(Aluno aluno) throws Exception;
	public List<Aluno> listAll() throws Exception;
}
