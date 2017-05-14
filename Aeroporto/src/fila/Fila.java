package fila;

import nodo.Aviao;

public class Fila {
	private Aviao inicio = null, fim = null, aux = null;
	
	
	public void mostraFila(){
		if (inicio == null){
			System.out.println("Fila de Decolagem Vazia.");
		} else {
			System.out.println("Mostrando toda a fila de Decolagem: ");
			aux = inicio;
			while(aux!=null){
				System.out.println(aux.toString());
				aux = aux.getProx();
			}
		}
	}
	
	
	public void inserir(int id, int unidadeTempo){
		Aviao novo = new Aviao();
		novo.setId(id);
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
		System.out.println("Avião com ID ="+id+" e U.T. = "+unidadeTempo+" inserido na fila com sucesso.");
	}
	
	public void remover(){
		if(inicio == null){
			System.out.println("Fila de aviões vazia, sem aviões para remover.");
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
}
