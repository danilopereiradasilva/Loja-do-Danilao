package loja;

import java.util.*;

public class LojadoDanilao {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Produto> produtos = new ArrayList<>();
		int carrinho[] = new int[10];
		int opcao, anoN, qtde;
		double somaTotal, preco;
		String nome, codUser;
		char continuar = 'S', genero;
		
		produtos.add(new Produto("DAN-001", "CHARGE", 2.0, 10));
		produtos.add(new Produto("DAN-002", "TENTAÇÃO", 2.5, 10));
		produtos.add(new Produto("DAN-003", "LOLO", 2.0, 10));
		produtos.add(new Produto("DAN-004", "MOÇA CHOCOLATE",2.5, 10));
		produtos.add(new Produto("DAN-005", "PRESTÍGIO BRANCO", 2.0, 10));
		produtos.add(new Produto("DAN-006", "PRESTÍGIO DARK", 2.0, 10));
		produtos.add(new Produto("DAN-007", "NESTLÉ MIO", 2.0, 10));
		produtos.add(new Produto("DAN-008", "TALENTO", 8.0, 10));
		produtos.add(new Produto("DAN-009", "KIT KAT", 4.0, 10));
		produtos.add(new Produto("DAN-010", "BATON", 1.0, 10));
		do {
			menuInicial();
			opcao = sc.nextInt();
			switch (opcao) {
			case 1:
				somaTotal = 0;
				sc.nextLine();
				System.out.println("Digite seu nome: ");
				nome = sc.nextLine();
				do {
					System.out.println("[M] para Masculino, [F] para feminino: ");
					genero = sc.next().toUpperCase().charAt(0);
				} while ((genero != 'M') && (genero != 'F'));
				do {
					System.out.println("Digite seu ano de nascimento (AAAA): ");
					anoN = sc.nextInt();
				} while (anoN < 1900);
				Cliente cliente = new Cliente(nome, genero, anoN);
				System.out.printf("Seja bem vind%s, %s\n\n", cliente.retornaGenero(), cliente.getNome());
				for (int i = 0; i < 10; i++) {
					carrinho[i] = 0;
				}
				do {
					mostrarProdutos(produtos);
					System.out.print("Digite o código do produto desejado.\nDAN-0");
					int cod = sc.nextInt();
					cod -= 1;
					for (Produto i : produtos) {
						if (produtos.indexOf(i) == cod) {
							System.out.println("Quantas unidades você quer?");
							int qtd = sc.nextInt();
							if (qtd <= i.getEstoque()) {
								carrinho[cod] += qtd;
							}
							i.tiraEstoque(qtd);

						}
					}
					do {
						System.out.println("Você gostaria de comprar outro produto (S ou N)?");
						continuar = sc.next().toUpperCase().charAt(0);
					} while ((continuar != 'S') && (continuar != 'N'));
				} while (continuar == 'S');
				System.out.println("\nCarrinho de compras");
				linha(80);
				System.out.println();
				System.out.println("QTD.\t\tPREÇO UN.\tPREÇO TOTAL\tNOME DO PRODUTO");
				linha(80);
				System.out.println();
				for (int i = 0; i < produtos.size(); i++) {
					if (carrinho[i] != 0) {
						System.out.printf("%d\t\t%.2f\t\t%.2f\t\t%s\n", 10 - produtos.get(i).getEstoque(),
								produtos.get(i).getPreco(),
								((10 - produtos.get(i).getEstoque()) * produtos.get(i).getPreco()),
								produtos.get(i).getNome());
						somaTotal += (10 - produtos.get(i).getEstoque()) * produtos.get(i).getPreco();
					}
				}
				linha(80);
				System.out.println("\n\nPreço total: " + somaTotal);
				System.out.println("\nForma de pagamento: ");
				System.out.println("[1] - Pagamento dinheiro com 10% de desconto");
				System.out.println("[2] - Pagamento no débito");
				System.out.println("[3] - Pagamento cartão de crédito (1x) +5% juros");
				System.out.println("[4] - Pagamento no credito parcelado (até 3x) +10% juros");
				do {
					System.out.println("Digite a opção desejada: ");
					opcao = sc.nextInt();
				} while (opcao < 1 || opcao > 4);
				if (opcao == 1) {
					somaTotal = 0.9 * somaTotal;
					System.out.printf("Novo valor é: R$ %.2f\n",somaTotal);
				} else if (opcao == 2) {
					System.out.printf("Valor mantido em: R$ %.2f\n",somaTotal);
				} else if (opcao == 3) {
					somaTotal = 1.05 * somaTotal;
					System.out.printf("Novo valor é: R$ %.2f\n",somaTotal);
				} else {
					somaTotal = 1.1 * somaTotal;
					System.out.printf("Novo valor é: R$ %.2f\n",somaTotal);
					System.out.printf("Cada parcela no valor de: R$ %.2f\n", somaTotal / 3);
				}
				double imposto = 0.09 * somaTotal;
				System.out.printf("Valor do imposto: R$ %.2f\n", imposto);
				System.out.printf("Preço total com imposto: R$ %.2f\n",(somaTotal + imposto));
				break;
			case 2:
				linha(80);
				System.out.println("\nAtualização de produtos");
				linha(80);
				System.out.println("\n[1] - Atualizar nome");
				System.out.println("[2] - Atualizar preço");
				System.out.println("[3] - Manutenção de estoque");
				System.out.println("[4] - Lista de produtos");
				do {
					System.out.println("Digite a opção desejada: ");
					opcao = sc.nextInt();
				} while (opcao < 1 || opcao > 5);
				if (opcao == 1) {
					sc.nextLine();
					System.out.println("Digite o código de produto que quer alterar o nome: ");
					codUser = sc.next();
					for (Produto p : produtos) {
						if (codUser.equals(p.getCodigo())) {
							System.out.println("O produto selecionado foi: ");
							System.out.println("\nCód.\t\tEstoque\t\tPreço\t\tDescrição");
							System.out.println(p.getCodigo() + "\t\t" + p.getEstoque() + "\t\t" + p.getPreco() + "\t\t"
									+ p.getNome());
							do {
								System.out.println("Digite 1 para confirmar a alteração de nome, 2 para cancelar");
								opcao = sc.nextInt();
							} while (opcao < 1 || opcao > 2);
							if (opcao == 1) {
								sc.nextLine();
								System.out.println("Digite o novo nome: ");
								nome = sc.nextLine();
								p.atualizaNome(nome);

							} else {
								System.out.println("Alteração cancelada.");
							}
						}
					}
				} else if (opcao == 2) {
					sc.nextLine();
					System.out.println("Digite o código de produto que quer alterar o preço: ");
					codUser = sc.next();
					for (Produto p : produtos) {
						if (codUser.equals(p.getCodigo())) {
							System.out.println("O produto selecionado foi: ");
							System.out.println("\nCód.\t\tEstoque\t\tPreço\t\tDescrição");
							System.out.println(p.getCodigo() + "\t\t" + p.getEstoque() + "\t\t" + p.getPreco() + "\t\t"
									+ p.getNome());
							do {
								System.out.println("Digite 1 para confirmar a alteração de preço, 2 para cancelar");
								opcao = sc.nextInt();
							} while (opcao < 1 || opcao > 2);
							if (opcao == 1) {
								System.out.println("Digite o novo preço: ");
								preco = sc.nextDouble();
								p.atualizaPreco(preco);

							} else {
								System.out.println("Alteração cancelada.");
							}
						}
					}
				} else if (opcao == 3) {
					sc.nextLine();
					System.out.println("Digite o código de produto que quer atualizar estoque: ");
					codUser = sc.next();
					for (Produto p : produtos) {
						if (codUser.equals(p.getCodigo())) {
							System.out.println("O produto selecionado foi: ");
							System.out.println("\nCód.\t\tEstoque\t\tPreço\t\tDescrição");
							System.out.println(p.getCodigo() + "\t\t" + p.getEstoque() + "\t\t" + p.getPreco() + "\t\t"
									+ p.getNome());
							do {
								System.out.println(
										"Digite 1 para adicionar estoque, 2 para retirar estoque, 3 para cancelar");
								opcao = sc.nextInt();
							} while (opcao < 1 || opcao > 3);
							if (opcao == 1) {
								do {
									System.out.println("Digite a quantidade a ser adicionada: ");
									qtde = sc.nextInt();
								} while (qtde < 0);
								p.addEstoque(qtde);
							} else if (opcao == 2) {
								do {
									System.out.println("Digite a quantidade a ser retirada: ");
									qtde = sc.nextInt();
								} while (qtde < 0);
								p.tiraEstoque(qtde);
							} else {
								System.out.println("Alteração cancelada.");
							}
						}
					}
				} else {
					linha(80);
					System.out.println("\nLista de produtos");
					linha(80);
					System.out.println("\nCód.\t\tEstoque\t\tPreço\t\tDescrição");
					for (Produto p : produtos) {
						System.out.println(
								p.getCodigo() + "\t\t" + p.getEstoque() + "\t\t" + p.getPreco() + "\t\t" + p.getNome());
					}
				}
				break;
			case 3:
				System.out.println("Agradecemos pelo interesse, volte sempre!");
				break;
			default:
				//
			}
			do {
				sc.nextLine();
				System.out.println("Você deseja fazer outra operação S ou N");
				continuar = sc.next().toUpperCase().charAt(0);
			} while ((continuar != 'S') && (continuar != 'N'));
		} while (continuar != 'N');
		sc.close();

	}

	static void linha(int tam) {
		for (int i = 1; i <= tam; i++) {
			System.out.print("-");
		}
	}

	static void menuInicial() {
		linha(80);
		System.out.println("\n                               LOJA DO DANILÃO");
		System.out.println("                 PARA MAIS MOMENTOS DE ALEGRIA E DE PRAZER");
		linha(80);
		System.out.println("\n[1] - COMPRAR PRODUTOS");
		System.out.println("[2] - GERENCIAR ESTOQUE");
		System.out.println("[3] - SAIR");
		System.out.println("Digite a opção desejada: ");
	}

	static void mostrarProdutos(List<Produto> produtos) {
		linha(60);
		System.out.println("\nCÓD.\t\tPREÇO\t\tNOME DO PRODUTO");
		linha(60);
		System.out.println();
		for (Produto i : produtos) {
			System.out.printf("%s\t\t%.2f\t\t%s\n", i.getCodigo(), i.getPreco(), i.getNome());
		}
	}
}
