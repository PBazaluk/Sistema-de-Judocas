//package org.fpij.jitakyoei.model.validator;
package  com.example.model.validator;

//import org.fpij.jitakyoei.model.beans.Aluno;
import com.example.model.beans.Aluno;

public class AlunoValidator implements Validator<Aluno>{
	public boolean validate(Aluno obj) {
		return true;
	}
}