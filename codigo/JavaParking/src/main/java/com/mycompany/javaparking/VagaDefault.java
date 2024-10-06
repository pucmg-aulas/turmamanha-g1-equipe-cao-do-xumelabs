/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaparking;

public class VagaDefault extends Vaga {

	private static final double taxa;
	
	static {
		taxa = 1;
	}
	
	public VagaDefault(String numeroVaga) {
		super(numeroVaga);
		super.alterarDisponibilidade(false);
	}


	@Override
	public double calcularAjuste(){
		return taxa;
	}

}
