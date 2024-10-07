/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaparking;

public class VagaIdoso extends Vaga {
	private static final double desconto;
	
	static {
		desconto = 0.85;
	}
	
	public VagaIdoso(String numeroVaga) {
		super(numeroVaga);
		super.alterarDisponibilidade(false);
	}

	@Override
	public double calcularAjuste(){
		return desconto;
	}
}
