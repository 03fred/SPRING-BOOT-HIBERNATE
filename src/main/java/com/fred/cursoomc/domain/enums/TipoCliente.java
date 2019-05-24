package com.fred.cursoomc.domain.enums;

public enum TipoCliente {
      
	PESSOAFISICA(0,"Pessoa Física"),
	PESSOAJURIDICA(1,"Pessoa Jurídica");
	
	private int cod;
	private String  descricao;
	
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String descricao() {
		return descricao;
	}
}

