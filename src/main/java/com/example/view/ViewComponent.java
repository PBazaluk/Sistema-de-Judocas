package com.example.view;

import javax.swing.JPanel;

import com.example.facade.AppFacade;

public interface ViewComponent {
	public JPanel getGui();
	public void registerFacade(AppFacade fac);
}
