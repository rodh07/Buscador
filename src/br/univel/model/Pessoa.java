package br.univel.model;

public class Pessoa {

	private Integer id;
	private String nome;
	private Integer idade;
	private String profissao;

	public Pessoa(Integer id, String nome, Integer idade, String profissao) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.profissao = profissao;
		
	}

	public Pessoa() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", idade=" + idade + ", profissao=" + profissao + "]";
	}

}
