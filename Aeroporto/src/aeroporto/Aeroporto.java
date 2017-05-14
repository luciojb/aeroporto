package aeroporto;

import java.util.Random;

import pista.Pista;

public class Aeroporto {
	private Pista pista1, pista2;
	
	public Aeroporto(){
		pista1 = new Pista();
		pista2 = new Pista();
	}
	
	public Pista getPista1() {
		return pista1;
	}
	
	public void setPista1(Pista pista1) {
		this.pista1 = pista1;
	}

	public Pista getPista2() {
		return pista2;
	}

	public void setPista2(Pista pista2) {
		this.pista2 = pista2;
	}
	
	/**
	 * Envia um avião para prateleira de aterrissagem
	 */
	public void adicionaAviaoAterrissagem(){
		Random rand = new Random();
		int combustivelMinimo = 1, combustivelMaximo = 20;
		
		try{
			if (pista1.quantidadeAvioesPoucoCombustivel()==2 && pista2.quantidadeAvioesPoucoCombustivel()==2){
				combustivelMinimo = 3;
			}
		} catch (Exception e) {
			e.getMessage();
		}
		
		int id = 0, combustivel = 0;
		id = rand.nextInt(500)+1;
		combustivel = rand.nextInt(combustivelMaximo-combustivelMinimo)+combustivelMinimo;
		
		if (verificaPrateleiraAdicionar()==0){
			try{
				while (pista1.getAterrissagem().existeId(id) || pista2.getAterrissagem().existeId(id)){
					id = rand.nextInt(500)+1;
				}
			}catch (Exception e) {
				e.getMessage();
			}
			pista1.getAterrissagem().inserirFim(id, combustivel);
			System.out.println("Inserindo avião na prateleira 1...");
		} else {
			try{
				while (pista2.getAterrissagem().existeId(id) || pista1.getAterrissagem().existeId(id)){
					id = rand.nextInt(500)+1;
				}
			}catch (Exception e) {
				e.getMessage();
			}
			pista2.getAterrissagem().inserirFim(id, combustivel);
			System.out.println("Inserindo avião na prateleira 2...");
		}
	}
	
	/**
	 * Util para verificação
	 * Retorna a prateleira na qual o aviao pode ser adicionado
	 * @return 0 para a primeira, ou 1 para a segunda 
	 */
	private int verificaPrateleiraAdicionar(){
		int avioesPrateleira1;
		int avioesPrateleira2;
		try{
			avioesPrateleira1 = pista1.getAterrissagem().contarAvioes();
			avioesPrateleira2 = pista2.getAterrissagem().contarAvioes();
		} catch (Exception e) {
			avioesPrateleira1 = 0;
			avioesPrateleira2 = 0;
		}
		
		if( avioesPrateleira1 == 0){
			return 0;
		} else if(avioesPrateleira2 == 0){
			return 1;
		} else if (avioesPrateleira1<=avioesPrateleira2 && pista1.quantidadeAvioesPoucoCombustivel()<2){
			return 0;
		}
		return 1;
	}
}
