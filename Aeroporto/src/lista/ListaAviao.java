package lista;

import nodo.Aviao;

public class ListaAviao {
	private Aviao inicio = null, fim = null, aux = null, anterior = null;

	public Aviao getInicio() {
		return inicio;
	}

	public void setInicio(Aviao inicio) {
		this.inicio = inicio;
	}

	public Aviao getFim() {
		return fim;
	}

	public void setFim(Aviao fim) {
		this.fim = fim;
	}

	public Aviao getAux() {
		return aux;
	}

	public void setAux(Aviao aux) {
		this.aux = aux;
	}

	public Aviao getAnterior() {
		return anterior;
	}

	public void setAnterior(Aviao anterior) {
		this.anterior = anterior;
	}
	
	public void inserirInicio(int id, int unidadeTempo){
		Aviao novo = new Aviao();
		novo.setId(id);
		novo.setUnidadeTempoOriginal(unidadeTempo);
		
		if (inicio == null){
			//Vazia, inicio e fim recebem o elemento
			inicio = novo;
			fim = novo;
			novo.setProx( null);
		} else {
			//A lista contém elementos, e o novo elemento será inserido no inicio da lista
			novo.setProx(inicio);
			inicio = novo;
		}
		System.out.println("Inserido novo aviao com ID = "+id+" e "+unidadeTempo+" unidades de tempo.");
	}
	
	public void mostraLista(){
		if (inicio == null){
			System.out.println("Lista de aviões Vazia.");
		} else {
			aux = inicio;
			while(aux!=null){
				System.out.println(aux.toString());
				aux = aux.getProx();
			}
		}
	}
	
	
	public void inserirFim(int id, int unidadeTempo){
		Aviao novo = new Aviao();
		novo.setId(id);
		novo.setTempoNaFila(0);
		novo.setUnidadeTempoOriginal(unidadeTempo);
		
		if (inicio == null){
			//Vazia, inicio e fim recebem o elemento
			inicio = novo;
			fim = novo;
			novo.setProx(null);
		} else {
			//A lista contém elementos, e o novo elemento será inserido no fim da lista
			fim.setProx(novo);
			fim = novo;
			fim.setProx(null);
		}
	}
	
	public void removerAviao(int id){
		int ocorrencias = 0;
		if(inicio == null){
			System.out.println("Lista Vazia");
		} else {
			aux = inicio;
			anterior = null;
			while (aux != null) {
				if (aux.getId() == id){
					ocorrencias++;
					if(aux == inicio){
						inicio = aux.getProx();
					} else if(aux == fim){
						if(anterior!=null)
							anterior.setProx(null);
						fim = anterior;
						aux = null;
					} else {
						if(anterior!=null)
						anterior.setProx(aux.getProx());
						aux = aux.getProx();
					}
				} else {
					anterior = aux;
					aux = aux.getProx();
				}
			}
		}
		if (ocorrencias == 0){
			System.out.println("Id não encontrado.");
		} else {
			System.out.println("Removido avião "+id);
		}
	}
	
	
	public void removerInicio(){
		if(inicio == null){
			System.out.println("Lista Vazia");
		} else {
			inicio = inicio.getProx();
		}
	}
	
	public int contarAvioes(){
		int ocorrencias = 0;
		if(inicio != null){
			aux = inicio;
			ocorrencias++;
			while(aux.getProx() != null){
				aux = aux.getProx();
				ocorrencias++;
			}
		}
		return ocorrencias;
	}
	
	public int calculaAltura(int posicao){
		int indice = 0;
		if(inicio != null){
			aux= inicio;
			while(aux.getProx() != null && indice != posicao){
				aux = aux.getProx();
				indice++;
			}
		}
		int tamanho = contarAvioes()-indice;
		return tamanho<0?0:tamanho;
	}
	
	public boolean existeId(int id){
		if(inicio != null){
			aux = inicio;
			while(aux != null){
				if (aux.getId() == id){
					return true;
				}
				aux = aux.getProx();
			}
		}
		return false;
	}
}
