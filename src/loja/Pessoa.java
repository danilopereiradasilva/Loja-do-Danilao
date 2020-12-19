package loja;

public abstract class Pessoa {
	private String nome;
	private char genero;
	private int anoNascimento;


	public Pessoa(String nome, char genero, int anoNascimento) {
		super();
		this.nome = nome;
		this.genero = genero;
		this.anoNascimento = anoNascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}
	
	public int voltaIdade(int ano) {
		return ano - this.anoNascimento;
	}

	public String retornaGenero() {
		String pronome = "";
		if (this.genero == 'M' || this.genero == 'm') {
			pronome = "o";
		} else if (this.genero == 'F' || this.genero == 'f') {
			pronome = "a";
		}
		return pronome;
	}
}