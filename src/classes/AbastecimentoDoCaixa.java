package classes;

public class AbastecimentoDoCaixa {

	// atributos para registrar o abastecimento de notas do caixa
	private final int[] cedulas = { 100, 50, 20, 10 };
	private int[] qtdeCedulas = new int[4];
	private int[] notasUtilizadas = new int[4];
	private int total;

	public int[] getCedulas() {
		return cedulas;
	}

	public int[] getQtdeCedulas() {
		return qtdeCedulas;
	}

	public int[] getNotasUtilizadas() {
		return notasUtilizadas;
	}

	// metodo para zerar o contador de notas apos o metodo efetuarSaque ser executado
	public void zerarNotas() {
		this.notasUtilizadas = new int[4];
	}

	// armazena a quantidade de acordo com o indice informado para suas respectivas cedulas
	public void setQtdeCedulas(int qtdeCedulas, int index) {
		this.qtdeCedulas[index] += qtdeCedulas;
	}

	public int getTotal() {
		int total = 0;
		for (int i = 0; i < cedulas.length; i++) {
			total += cedulas[i] * qtdeCedulas[i];
		}
		return this.total + total;
	}

	public void printNotasDisponiveis() {
		if (getTotal() > 0) {
			System.out.println("Notas no caixa: ");
			for (int i = 0; i < cedulas.length; i++) {
				if (qtdeCedulas[i] > 0) {
					System.out.println("R$ " + cedulas[i] + ",00 : " + qtdeCedulas[i]);
				}
			}
		}
	}

}
