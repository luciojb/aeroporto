package nodo;

public class Aviao {
	private int id, unidadeTempoOriginal, tempoNaFila=0;
	private Aviao prox;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUnidadeTempoOriginal() {
		return unidadeTempoOriginal;
	}
	
	public void setUnidadeTempoOriginal(int unidadeTempoOriginal) {
		this.unidadeTempoOriginal = unidadeTempoOriginal;
	}
	
	public int getTempoNaFila() {
		return tempoNaFila;
	}

	public void setTempoNaFila(int tempoNaFila) {
		this.tempoNaFila = tempoNaFila;
	}

	public Aviao getProx() {
		return prox;
	}
	
	public void setProx(Aviao prox) {
		this.prox = prox;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Aviao [id = ");
		builder.append(id);
		builder.append(", unidades de tempo original = ");
		builder.append(unidadeTempoOriginal);
		builder.append(", unidades de tempo na fila = ");
		builder.append(tempoNaFila);
		builder.append("]");
		return builder.toString();
	}
	
	
}
