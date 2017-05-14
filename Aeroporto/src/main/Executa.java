package main;

import java.util.Random;

import aeroporto.Aeroporto;

public class Executa {
	
	public static void esperar(){
		for(int i=0; i<=5; i++){
			try{
				Thread.sleep(333);
				System.out.print(".");
				Thread.sleep(333);
				System.out.print(".");
				Thread.sleep(333);
				System.out.print(".   ");
			} catch(Exception e){
				e.getMessage();
			}
		}
	}

	public static void main(String[] args) {
		Aeroporto aeroporto = new Aeroporto();
		Random rand = new Random();
		
		while(true){
			System.out.println("----------------------------------------------------------------------------------\n");
			
			int adicionarAterrissagem = rand.nextInt(5);
			
			System.out.println("-Temos "+adicionarAterrissagem+" aviões chegando às prateleiras de aterrissagem.");
			for(int i=0;i<adicionarAterrissagem;i++){
				aeroporto.adicionaAviaoAterrissagem();
			}
			
			System.out.println("\n\n> - - - Estado da Pista 1 - - - <:\n");
			
			System.out.println("Coletando dados das pistas. Iniciando Logs. Mostrando dados: ");
			
			System.out.println("\n-> Pilha de aterrissagem:\n");
				
			aeroporto.getPista1().getAterrissagem().mostraLista();
			
			
			System.out.println("\n-> Lista de Aviões em manutenção ou desembarque:\n");
			aeroporto.getPista1().getManutencaoDesembarque().mostraLista();
			
			System.out.println("\n-> Fila dos aviões para decolagem:\n");
			aeroporto.getPista1().getDecolagem().mostraFila();
			
			System.out.println("\n-->Média de espera para decolagem = "+aeroporto.getPista1().calculaMediaTempoDecolagem()+" unidades de tempo.");
			System.out.println("\n-->Média de espera para aterrissagem = "+aeroporto.getPista1().calculaMediaTempoAterrissagem()+" unidades de tempo.");
			
			System.out.println("\n-->Quantidade de aviões que aterrissaram com reserva de combustível:"
						+ "=> "+aeroporto.getPista1().getTotalReservaCombustivel()+"\n\n");
			
			System.out.println("\nAtualizando status das filas, quantidade de combustível, tempo nas filas...\n\n");
			aeroporto.getPista1().executaRotina();
			esperar();
			
			System.out.println("\n");
			
			
			if(aeroporto.getPista1().quantidadeAvioesPoucoCombustivel()==0 && aeroporto.getPista1().getDecolagem().contarAvioes()!=0){
				int aterrissaDecola = rand.nextInt(2);
				if(aterrissaDecola==0 && aeroporto.getPista1().getAterrissagem().getInicio()!=null){
					System.out.println("Aterrissando Avião.......");
					aeroporto.getPista1().aterrissaAviao();
				} else if (aterrissaDecola==1) {
					aeroporto.getPista1().decolaAviao();
					System.out.println("Decolando Avião.......");
				}
			} else if (aeroporto.getPista1().quantidadeAvioesPoucoCombustivel()>0 
					|| (aeroporto.getPista1().quantidadeAvioesPoucoCombustivel()==0 && aeroporto.getPista1().getDecolagem().contarAvioes()==0)) {
				if(aeroporto.getPista1().getAterrissagem().getInicio()!=null){
					System.out.println("Aterrissando Avião.......");
					aeroporto.getPista1().aterrissaAviao();
				}
			} else {
				if(aeroporto.getPista1().getDecolagem().getInicio()!=null){
					System.out.println("Decolando Avião.......");
					aeroporto.getPista1().decolaAviao();
				}
			}
			
			
			System.out.println("\n\nPronto.");
			
			System.out.println("Aviões na prateleira:\n");
			aeroporto.getPista1().getAterrissagem().mostraLista();
			
			System.out.println("\n----------------------------------------------------------------------------------\n");
			
			System.out.println("\n\n> - - - Estado da Pista 2 - - - <:\n");
			
			System.out.println("Coletando dados das pistas. Iniciando Logs. Mostrando dados: ");
			
			System.out.println("\n-> Pilha de aterrissagem:\n");
				
			aeroporto.getPista2().getAterrissagem().mostraLista();
			
			
			System.out.println("\n-> Lista de Aviões em manutenção ou desembarque:\n");
			aeroporto.getPista2().getManutencaoDesembarque().mostraLista();
			
			System.out.println("\n-> Fila dos aviões para decolagem:\n");
			aeroporto.getPista2().getDecolagem().mostraFila();
			
			System.out.println("\n-->Média de espera para decolagem = "+aeroporto.getPista2().calculaMediaTempoDecolagem()+" unidades de tempo.");
			System.out.println("\n-->Média de espera para aterrissagem = "+aeroporto.getPista2().calculaMediaTempoAterrissagem()+" unidades de tempo.");
			
			System.out.println("\n-->Quantidade de aviões que aterrissaram com reserva de combustível:"
						+ "=> "+aeroporto.getPista2().getTotalReservaCombustivel()+"\n\n");
			
			System.out.println("\nAtualizando status das filas, quantidade de combustível, tempo nas filas...\n\n");
			aeroporto.getPista2().executaRotina();
			esperar();
			
			System.out.println("\n");
			
			if(aeroporto.getPista2().quantidadeAvioesPoucoCombustivel()==0 && aeroporto.getPista2().getDecolagem().contarAvioes()==0){
				int aterrissaDecola = rand.nextInt(2);
				if(aterrissaDecola==0 && aeroporto.getPista2().getAterrissagem().getInicio()!=null){
					System.out.println("Aterrissando Avião.......");
					aeroporto.getPista2().aterrissaAviao();
				} else if (aterrissaDecola==1 && aeroporto.getPista2().getDecolagem().getInicio()!=null) {
					aeroporto.getPista2().decolaAviao();
					System.out.println("Decolando Avião.......");
				}
			} else if (aeroporto.getPista2().quantidadeAvioesPoucoCombustivel()>0 
					|| (aeroporto.getPista2().quantidadeAvioesPoucoCombustivel()==0 && aeroporto.getPista2().getDecolagem().contarAvioes()==0)) {
				if(aeroporto.getPista2().getAterrissagem().getInicio()!=null){
					System.out.println("Aterrissando Avião.......");
					aeroporto.getPista2().aterrissaAviao();
				}
			} else {
				if(aeroporto.getPista2().getDecolagem().getInicio()!=null){
					System.out.println("Decolando Avião.......");
					aeroporto.getPista2().decolaAviao();
				}
			}
			
		}

	}

}
