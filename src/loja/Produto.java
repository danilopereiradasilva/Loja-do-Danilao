package loja;

public class Produto {
	private String codigo, nome;
	private double preco;
	private int estoque;

	public Produto() {

	}

	public Produto(String codigo, String nome, double preco, int estoque) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.preco = preco;
		this.estoque = estoque;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void atualizaNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void atualizaPreco(double preco) {
		this.preco = preco;
	}

	public int getEstoque() {
		return estoque;
	}

	public void addEstoque(int estoque) {
		this.estoque += estoque;
	}

	public boolean tiraEstoque(int estoque) {
		if (this.estoque >= estoque) {
			this.estoque -= estoque;
			return true;
		} else {
			System.out.println("Não temos essa quantidade no estoque.");
			return false;
		}
	}
	
	public double venda(int qtdeVendida) {
        if(qtdeVendida>this.estoque || qtdeVendida<=0) {
            System.out.printf("Quantidade inválida. Quantidade disponível: %d\n", this.estoque);
            qtdeVendida = 0;
        }
        return (qtdeVendida*this.preco);
    }

}
