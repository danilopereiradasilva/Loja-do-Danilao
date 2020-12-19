package loja;

public class Cliente extends Pessoa{
	private String numCPF;

	public Cliente(String nome, char genero, int anoNascimento) {
		super(nome, genero, anoNascimento);
	}

	public String getNumCPF() {
		return numCPF;
	}

	public void corrigeNumCPF(String numCPF) {
		this.numCPF = numCPF;
	}
}