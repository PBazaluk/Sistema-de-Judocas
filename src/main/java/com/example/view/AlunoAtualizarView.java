package com.example.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.example.facade.AppFacade;
import com.example.model.beans.Aluno;
import com.example.view.forms.AlunoForm;
import com.example.view.gui.AlunoAtualizarPanel;

public class AlunoAtualizarView implements ViewComponent{
	private AlunoAtualizarPanel gui;
	private AlunoForm alunoForm;
	private AppFacade facade;
	private MainAppView parent;
	
	public AlunoAtualizarView(MainAppView parent, Aluno aluno){
		this.parent = parent;
		gui = new AlunoAtualizarPanel();
		alunoForm = new AlunoForm(gui.getAlunoPanel());
		alunoForm.setAluno(aluno);
		gui.getAtualizar().addActionListener(new AtualizarActionHandler());
		gui.getCancelar().addActionListener(new CancelarActionHandler());
	}
	
	@Override
	public JPanel getGui(){
		return gui;
	}
	
	@Override
	public void registerFacade(AppFacade fac) {
		this.facade = fac;		
	}	
	
	/**
	 * Classe interna responsável por responder aos cliques no botão "Atualizar".
	 * 
	 * @author Aécio
	 */
	public class AtualizarActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Aluno aluno = alunoForm.getAluno();
			try {
				facade.updateAluno(aluno);
				JOptionPane.showMessageDialog(gui, "Aluno atualizado com sucesso!");
				parent.removeTabPanel(gui);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Classe interna responsável por responder aos cliques no botão "Cancelar".
	 * 
	 * @author Aécio
	 */
	public class CancelarActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			parent.removeTabPanel(gui);
		}
	}
}
