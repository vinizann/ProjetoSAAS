package classes;

public class CaixaEletronico {

	private AbastecimentoDoCaixa abasteceCaixa;

	// construtor recebe uma referencia do objeto AbastecimentoDoCaixa
	public CaixaEletronico(AbastecimentoDoCaixa abasteceCaixa) {
		this.abasteceCaixa = abasteceCaixa;
	}

	public AbastecimentoDoCaixa getAbasteceCaixa() {
		return abasteceCaixa;
	}

	public int getTotalCaixa() {
		return abasteceCaixa.getTotal();
	}

	public boolean temSaldo(int valor) {
		return valor <= this.getTotalCaixa();
	}

	public void efetuarSaque(int valor) {
		if (valor < 0) {
			throw new IllegalArgumentException("Valor para saque deve ser positivo!");
		}
		if (!temSaldo(valor)) {
			System.out.println("Saque rejeitado! Valor no caixa eh insuficiente.");
		} else {
			for (int i = 0; i < abasteceCaixa.getNotasUtilizadas().length; i++) {
				// verifica se existe quantidade de notas disponiveis para o valor solicitado
				if (valor >= abasteceCaixa.getCedulas()[i] && abasteceCaixa.getQtdeCedulas()[i] > 0) {
					// atribui a quantidade de notas utilizadas para determinada cedula
					abasteceCaixa.getNotasUtilizadas()[i] = valor / abasteceCaixa.getCedulas()[i];
					// atualiza a qtde atual
					abasteceCaixa.getQtdeCedulas()[i] -= abasteceCaixa.getNotasUtilizadas()[i];
					if (abasteceCaixa.getQtdeCedulas()[i] < 0) {
						break;
					}
					valor = valor % abasteceCaixa.getCedulas()[i];
				}
			}
			// se o valor for diferente de zero, entao nao existem notas validas para efetuar o saque
			if (valor != 0) {
				System.out.println("Valor solicitado eh invalido. Nao existem notas para completar a quantia.");
				for (int i = 0; i < abasteceCaixa.getNotasUtilizadas().length; i++) {
					if (abasteceCaixa.getNotasUtilizadas()[i] > 0) {
						// volta a quantidade de notas que havia anteriormente
						abasteceCaixa.getQtdeCedulas()[i] += abasteceCaixa.getNotasUtilizadas()[i];
					}
				}
			} else {
				System.out.println("\nSaque aprovado! Notas utilizadas: ");
				printNotasUtilizadas();
			}
			abasteceCaixa.zerarNotas();
		}
	}

	public void printNotasUtilizadas() {
		for (int i = 0; i < abasteceCaixa.getNotasUtilizadas().length; i++) {
			if (abasteceCaixa.getNotasUtilizadas()[i] != 0) {
				System.out.println("R$ " + abasteceCaixa.getCedulas()[i] + ",00 : " + abasteceCaixa.getNotasUtilizadas()[i]);
			}
		}
	}

}
