//package org.fpij.jitakyoei.business;
package com.example.business;
import java.util.List;

import com.example.model.beans.Entidade;

public interface EntidadeBO {
	public void createEntidade(Entidade entidade) throws Exception;
	public void updateEntidade(Entidade entidade) throws Exception;
	public List<Entidade> searchEntidade(Entidade entidade) throws Exception;
	public List<Entidade> listAll() throws Exception;
}
