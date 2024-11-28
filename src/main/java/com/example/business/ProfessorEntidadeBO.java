//package org.fpij.jitakyoei.business;
package com.example.business;
import java.util.List;

import com.example.model.beans.ProfessorEntidade;

public interface ProfessorEntidadeBO {
	public void createProfessorEntidade(List<ProfessorEntidade> relacionamentos)
	throws Exception;
}
