package puzzlegame;


/**
 * Classe representativa da estrutura de dados PILHA.
 * <br>Funcionalidades minimalistas - vide javadoc.
 *
 * @author Marcio Porto Feitosa - 23/09/2022 - 10:45:23
 */
public class TadPilha {

    public TadPilha(int tam, boolean repeat) {
        this.tamanho = tam;
        this.topo = -1;
        this.pilha = new int[tam];
        this.repeat = repeat;
    }

    private final int tamanho;
    private int topo;
    private int[] pilha;
    private boolean repeat;

    private int retorno;
    
    public int getRetorno() {
        return retorno;
    }
    
    public int[] getElements() {
    	return this.pilha;
    }

    /**
     * Informa se a pilha esta' com a capacidade máxima ocupada.
     *
     * @return verdadeiro/falso
     */
    public boolean full() {
        return this.topo == this.tamanho - 1;
    }

    /**
     * Informa a quantidade de elementos na pilha.
     *
     * @return numerico
     */
    public int qtd() {
        return this.topo + 1;
    }

    /**
     * Informa se a pilha esta' vazia.
     *
     * @return verdadeiro/falso
     */
    public boolean empty() {
        return this.qtd() == 0;
    }

    /**
     * Insere um elemento na pilha.
     *
     * @param valor
     * @return true = operacao bem sucedida; false = operacao falhou (pilha
     * cheia)
     */
    public boolean push(int valor) {
        if (!this.full()) {
        	if (this.repeat == true) {
				this.topo += 1;
				this.pilha[this.topo] = valor;
				return true;
        	} else {
        		if (!search(valor)) {
        			this.topo += 1;
    				this.pilha[this.topo] = valor;
    				return true;
        		}
        		else
        			return false;
        	}
        } else {
            return false;
        }
    }

    /**
     * Retira um elemento da pilha.
     *
     * @return true = retirada bem sucedida; elemento disponivel via metodo
     * getRetorno()
     * <br>false = retirada falhou (pilha vazia?)
     * @see #getRetorno()
     */
    public boolean pop() {
        if (this.empty()) {
            return false;
        }
        this.retorno = this.pilha[this.topo];
        this.topo--;
        return true;
    }
    
    public boolean search(int value) {
    	for (int i = 0; i <= topo; i++) {
    		if (pilha[i] == value) {
    			this.retorno = i;
    			return true;
    		}
    	}
    	return false;
    }

    /**
     * Imprime o conteudo da pilha em uma string.
     *
     * @return
     */
    public String print() {
        String ret = "";
        if (this.empty()) {
            return "A pilha esta' vazia";
        }
        ret += "A pilha tem " + this.qtd() + " elementos.\n";
        for (int i = 0; i <= this.topo; i++) {
            ret += this.pilha[i] + " ";
        }
        return ret;
    }

}
