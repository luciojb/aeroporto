package pista;

import java.util.Random;

import fila.Fila;
import lista.ListaAviao;
import nodo.Aviao;

public class Pista {
	private ListaAviao aterrissagem, manutencaoDesembarque;
	private Fila decolagem;
	private int totalEsperaDecolagem=0, totalEsperaDecolagemTempo=0;
	private int totalEsperaAterrissagem=0, totalEsperaAterrissagemTempo=0, totalReservaCombustivel=0;
	
	public Pista(){
		aterrissagem = new ListaAviao();
		manutencaoDesembarque = new ListaAviao();
		decolagem = new Fila();
	}
	
	public ListaAviao getAterrissagem() {
		return aterrissagem;
	}

	public void setAterrissagem(ListaAviao aterrissagem) {
		this.aterrissagem = aterrissagem;
	}

	public ListaAviao getManutencaoDesembarque() {
		return manutencaoDesembarque;
	}

	public void setManutencaoDesembarque(ListaAviao manutencaoDesembarque) {
		this.manutencaoDesembarque = manutencaoDesembarque;
	}

	public Fila getDecolagem() {
		return decolagem;
	}

	public void setDecolagem(Fila decolagem) {
		this.decolagem = decolagem;
	}
	
	public int getTotalReservaCombustivel() {
		return totalReservaCombustivel;
	}

	public void setTotalReservaCombustivel(int totalReservaCombustivel) {
		this.totalReservaCombustivel = totalReservaCombustivel;
	}

	/**
	 * Util para verificação
	 * Retorna o número de aviões em situação de falta de combustível 
	 * @return cont, o contador
	 */
	public int quantidadeAvioesPoucoCombustivel(){
		try{
			int cont=0;
			
			if(aterrissagem.getInicio() != null){
				Aviao aux = aterrissagem.getInicio();
				while (aux!=null){
					if(aux.getUnidadeTempoOriginal()<5){
						cont++;
					}
					aux = aux.getProx();
				}
			}
			return cont;
		} catch (Exception e) {
			return 0;
		}
	}
	

	/**
	 * Realiza o processo de aterrissagem
	 * Envia o aviao para lista de avioes em manutencao e desembarque
	 * @return Aviao que aterrissou
	 */
	public void aterrissaAviao(){
		Aviao aux = aterrissagem.getInicio();
		if(aux != null){
			Aviao aterrisar = aterrissagem.getInicio();
			while (aux.getProx()!=null){
				if (aux.getUnidadeTempoOriginal()<3){
					
					if (aux.getUnidadeTempoOriginal()<3 && aux.getUnidadeTempoOriginal()<aterrisar.getUnidadeTempoOriginal()){
						aterrisar = aux;
					}
				}
				aux = aux.getProx();
			}
			manutencaoDesembarque.inserirFim(aterrisar.getId(), aterrisar.getUnidadeTempoOriginal());
			totalEsperaAterrissagem++;
			totalEsperaAterrissagemTempo+=aterrisar.getTempoNaFila();
			if(aterrisar.getUnidadeTempoOriginal()<3)
				totalReservaCombustivel++;
			aterrissagem.removerAviao(aterrisar.getId());
		}
		
		
		
		
	}
	
	/**
	 * Adiciona um aviao que esta em manutencao à fila de decolagem
	 * Precisa estar com combustivel cheio
	 */
	private void adicionaAviaoFilaDecolagem(){
		Aviao aux = manutencaoDesembarque.getInicio();
		int adicionados = 0;
		while (aux != null && adicionados<2){
			if (aux.getUnidadeTempoOriginal() >= 20){
				aux.setUnidadeTempoOriginal(20);
				decolagem.inserir(aux.getId(), aux.getUnidadeTempoOriginal());
				adicionados++;
				System.out.println("Lista de manutenção: ");
				manutencaoDesembarque.removerAviao(aux.getId());
			}
			aux = aux.getProx();
		}
	}
	
	/**
	 * Decola um avião
	 */
	public void decolaAviao(){
		if (decolagem.contarAvioes()>0){
			totalEsperaDecolagem++;
			totalEsperaAterrissagemTempo+=decolagem.getInicio().getTempoNaFila();
			decolagem.remover();
		}
	}
	
	/**
	 * Retira uma unidade de tempo por aviao que esta na fila de aterrissagem
	 */
	private void retiraUnidadeTempoAterrissagem(){
		Aviao aux = aterrissagem.getInicio();
		while(aux != null){
			aux.setUnidadeTempoOriginal(aux.getUnidadeTempoOriginal()-1);
			aux = aux.getProx();
		}
	}
	
	/**
	 * Adiciona uma quantidade de unidades de tempo/combustivel ao aviao na lista de manutenção
	 */
	private void adicionaUnidadeTempoAviaoManutencao(){
		Aviao aux = manutencaoDesembarque.getInicio(); 
		Random rand = new Random();
		while(aux != null){
			aux.setUnidadeTempoOriginal(aux.getUnidadeTempoOriginal()+rand.nextInt(5));
			aux = aux.getProx();
		}
	}
	
	/**
	 * Adiciona uma tempo de fila ao aviao na lista de manutenção
	 */
	private void adicionaTempoFilaManutenção(){
		Aviao aux = manutencaoDesembarque.getInicio();
		while(aux != null){
			aux.setTempoNaFila(aux.getTempoNaFila()+1);
			aux = aux.getProx();
		}
	}
	
	/**
	 * Calcula a média de tempo de espera dos aviões que estavam na fila de decolagem  
	 * @return o tempo em unidades de tempo em decimal
	 */
	public double calculaMediaTempoDecolagem(){
		try{
			return totalEsperaDecolagemTempo/totalEsperaDecolagem;
		} catch (Exception e){
			return 0;
		}
	}
	
	/**
	 * Calcula a média de tempo de espera dos aviões que estavam na prateleira de aterrissagem  
	 * @return o tempo em unidades de tempo em decimal
	 */
	public double calculaMediaTempoAterrissagem(){
		try{
			return totalEsperaAterrissagemTempo/totalEsperaAterrissagem;
		} catch (Exception e){
			return 0;
		}
	}
	
	/**
	 * Adiciona uma unidade de tempo nos aviões na fila de espera para aterrissar
	 */
	private void adicionaTempoPrateleiraAterrissagem(){
		Aviao aux = aterrissagem.getInicio();
		while(aux != null){
			aux.setTempoNaFila(aux.getTempoNaFila()+1);
			aux = aux.getProx();
		}
	}
	
	/**
	 * Adiciona uma unidade de tempo nos aviões na fila de espera para decolar
	 */
	private void adicionaTempoFilaDecolagem(){
		Aviao aux = decolagem.getInicio();
		while(aux != null){
			aux.setTempoNaFila(aux.getTempoNaFila()+1);
			aux = aux.getProx();
		}
	}
	
	/**
	 * Executa a rotina entre unidades de tempo
	 */
	public void executaRotina(){
		System.out.println("Adiciona aviões na fila de decolagem.");
		adicionaAviaoFilaDecolagem();
		System.out.println("Retirando combustível dos aviões na prateleira.");
		retiraUnidadeTempoAterrissagem();
		System.out.println("Adicionando tempo de fila dos aviões na prateleira de aterrissagem.");
		adicionaTempoPrateleiraAterrissagem();
		System.out.println("Adicionando tempo de fila dos aviões na lista de desembarque/manutenção.");
		adicionaUnidadeTempoAviaoManutencao();
		System.out.println("Adicionando tempo de fila dos aviões na fila de manutenção ou desenbarque.");
		adicionaTempoFilaManutenção();
		System.out.println("Adicionando tempo de fila dos aviões na fila de decolagem.");
		adicionaTempoFilaDecolagem();
	}
}