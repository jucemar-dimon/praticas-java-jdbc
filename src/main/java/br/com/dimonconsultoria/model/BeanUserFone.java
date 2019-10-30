package br.com.dimonconsultoria.model;

public class BeanUserFone {

	private String nome;
	private String numero;
	private String email;

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getNumero() {
		return numero;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public BeanUserFone(String nome, String numero, String email) {
		super();
		this.nome = nome;
		this.numero = numero;
		this.email = email;
	}
	
	public BeanUserFone() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "BeanUserFone [nome=" + nome + ", numero=" + numero + ", email=" + email + "]";
	}
	
	
	
	

}
