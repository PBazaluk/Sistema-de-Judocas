package com.example.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.example.facade.AppFacade;
import com.example.model.beans.Aluno;
import com.example.view.forms.AlunoForm;
import com.example.view.gui.AlunoCadastrarPanel;

public class AlunoCadastrarView implements ViewComponent {

	private AlunoCadastrarPanel gui;
	private AlunoForm alunoForm;
	private AppFacade facade;
	private MainAppView parent;

	public AlunoCadastrarView(MainAppView parent) {
		this.parent = parent;
		gui = new AlunoCadastrarPanel();
		gui.getCadastrar().addActionListener(new CadastrarActionHandler());
		gui.getCancelar().addActionListener(new CancelarActionHandler());
		gui.setVisible(true);
		alunoForm = new AlunoForm(gui.getAlunoPanel());
	}

	@Override
	public JPanel getGui() {
		return gui;
	}
	
	@Override
	public void registerFacade(AppFacade fac) {
		this.facade = fac;
	}
	
	
	/**
	 * Classe interna responsável por responder aos cliques no botão "Cadastrar".
	 * 
	 * @author Aécio
	 */
	public class CadastrarActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Aluno aluno = alunoForm.getAluno();
			try {
				facade.createAluno(aluno);
				JOptionPane.showMessageDialog(gui, "Aluno cadastrado com sucesso!");
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
