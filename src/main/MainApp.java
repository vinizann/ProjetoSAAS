package main;

import classes.AbastecimentoDoCaixa;
import classes.CaixaEletronico;
import java.util.Scanner;

public class MainApp {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		AbastecimentoDoCaixa abasteceCaixa = new AbastecimentoDoCaixa();
		CaixaEletronico caixa = new CaixaEletronico(abasteceCaixa);

		int opcao;
		do {
			menu();
			opcao = input.nextInt();
			switch (opcao) {
				// Exibir total em caixa
				case 1:
					System.out.println("Total disponivel no caixa eletronico: R$ " + caixa.getTotalCaixa());
					caixa.getAbasteceCaixa().printNotasDisponiveis();
					break;
				// Abastecer caixa
				case 2:
					menuAbastecerCaixa();
					int indexNota = input.nextInt();
					if (indexNota < 0 || indexNota > 3) {
						System.out.println("Opcao invalida!");
						break;
					}
					System.out.print("Informe a quantidade de notas de R$ " + abasteceCaixa.getCedulas()[indexNota] + ": ");
					int qtdNotas = input.nextInt();
					abasteceCaixa.setQtdeCedulas(qtdNotas, indexNota);
					break;
				// Efetuar saque
				case 3:
					System.out.print("Informe o valor que deseja sacar: R$ ");
					int valor = input.nextInt();
					try {
						caixa.efetuarSaque(valor);
					} catch (IllegalArgumentException ex) {
						ex.printStackTrace();
					}
					break;
				case 4:
					System.out.println("Saindo...");
					input.close();
					break;
				default:
					System.out.println("Opcao invalida!");
					break;
			}
			System.out.println("");
		} while (opcao != 4);
	}

	public static void menu() {
		System.out.println("=== Subsistema Caixa Eletronico ===");
		System.out.println("1 - Exibir total em caixa");
		System.out.println("2 - Abastecer caixa");
		System.out.println("3 - Efetuar saque");
		System.out.println("4 - Sair");
		System.out.print("Qual deseja? ");
	}

	public static void menuAbastecerCaixa() {
		System.out.println("\nOpcoes de notas para abastecer o caixa: ");
		System.out.println("0 - R$ 100,00");
		System.out.println("1 - R$ 50,00");
		System.out.println("2 - R$ 20,00");
		System.out.println("3 - R$ 10,00");
		System.out.print("Qual deseja? ");
	}

}
