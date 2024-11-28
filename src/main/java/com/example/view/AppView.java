//package org.fpij.jitakyoei.view;
package com.example.view;

//import org.fpij.jitakyoei.facade.AppFacade;
import com.example.facade.AppFacade;
public interface AppView {
	public void handleModelChange(Object obj);
	public void displayException(Exception e);
	public void registerFacade(AppFacade facade);
}
