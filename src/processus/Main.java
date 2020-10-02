package processus;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Main {

	/**
	 * @param nbrs
	 * @param faire
	 * Cette méthode permet de générer aléatoirement des :
	 * -le temps d'arrivé des processus
	 * -le la durée des processus
	 * -et la priorité des processus
	 */
	public static void aleatoire(int nbrs, int faire) {
		ObservableList<Processus> liste =  FXCollections.observableArrayList();
		System.out.println("Vous aviez choisi de générer " + nbrs + " processus.");
		FCFS f = new FCFS();
		SJF sj = new SJF();
		SRTF st = new SRTF();
		RR r= new RR();
		PrioPre pp = new PrioPre();
		PrioNoPre pnp = new PrioNoPre();
		Random random = new Random();
		boolean ok = false;
		int nbrsproc=0;
		int prio = 0;
		Scanner sc = new Scanner(System.in);
		String chaine = "";
		int n = 0, n1 = 0, n2=0;Processus p1;
		for (int i = 0; i < nbrs; i++) {
			while (n1 == 0) {
				n1 = (int) (Math.random() * (13 - 1));
			}

			while (n == 0) {
				n = (int) (Math.random() * (13 - 1));
			}
			while(n2==0) {
				n2 = (int)(Math.random()*(16-1));
			}
			char c = (char) (random.nextInt(26) + 97);
			chaine = String.valueOf(c);
			String nomProcessus = null;
			int debutProcessus = 0;
			int dureeProcessus = 0;
			int priorite = 0;
			debutProcessus = n;
			dureeProcessus = n1;
			nomProcessus = chaine;		
			priorite= n2;
			
			if(faire ==5 || faire ==6) {
				p1 = new Processus(nomProcessus, debutProcessus, dureeProcessus,priorite);
			}
			else {
				p1 = new Processus(nomProcessus, debutProcessus, dureeProcessus);
			}
			 
			liste.add(p1);
			n1 = 0;
			n = 0;
		}
		 if(faire==4) {
			 ok = false;
			 while (!ok) {
					sc = new Scanner(System.in);
					try {
						System.out.println("Veuillez choisir le quantum ?");
						nbrsproc = sc.nextInt();
						ok = true;
					} catch (InputMismatchException e) {
						System.out.println("Erreur, veuillez entrer une valeur numérique.");
					}
				}
		}
		 
		 if (faire==5) {
			 prio = new Random().nextInt((10-1-1)+1)+1; 
		 }
		 
		switch (faire) {

		case 1:
			f.Fcfs(liste);
			break;
		case 2:
			sj.Sjf(liste);
			break;
		case 3:
			st.Srtf(liste);
			break;
		case 4:
			r.RoundR(liste,nbrsproc);
			break;
		case 5:
			pp.PrioPre(liste);
			break;
		case 6:
			pnp.PrioNoPre(liste);
		}
	}
	
	/**
	 * @param nbrs
	 * @param faire
	 * Cette méthode permet de générer manuellement  :
	 * -le temps d'arrivé des processus
	 * -le la durée des processus
	 * -et la priorité des processus
	 */
	public static void SaisieProc(int nbrs, int faire) {
		ObservableList<Processus> liste =  FXCollections.observableArrayList();
		System.out.println("Vous allez devoir entrer " + nbrs + " processus.");
		FCFS f = new FCFS();
		SJF sj = new SJF();
		SRTF st = new SRTF();
		PrioPre pp = new PrioPre();
		PrioNoPre pnp = new PrioNoPre();
		RR r= new RR();
		boolean ok = false;
		int nbrsproc=0;
		int prio=0;
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < nbrs; i++) {
			String nomProcessus = null;
			int debutProcessus = 0;
			int dureeProcessus = 0;
			 sc = new Scanner(System.in);
			System.out.println("Entrez un nouveau processus (1 caractère)");

			nomProcessus = sc.nextLine();

			int test1 = nomProcessus.length();
			while (test1 != 1) {
				System.out.println("Le nom est trop grand, 1 caractère seulement.");
				nomProcessus = sc.nextLine();
				test1 = nomProcessus.length();
			}

			ok = false;
			while (!ok) {
				System.out.println("duree processus ? ");
				sc = new Scanner(System.in);

				try {
					dureeProcessus = sc.nextInt();
					ok = true;
				} catch (InputMismatchException e) {
					System.out.println("Veuillez entrer une valeur numérique entière");
				}
			}
			
			ok = false;

			while (!ok) {
				System.out.println("debut processus ? ");
				sc = new Scanner(System.in);
				try {
					debutProcessus = sc.nextInt();
					ok = true;
				} catch (InputMismatchException e) {
					System.out.println("Veuillez entrer une valeur numérique entière");
				}

			} 
			ok= false;
			Processus p1;
			if (faire==5 || faire ==6) {
				 while (!ok) {
					 sc = new Scanner(System.in);
					 try {
						 System.out.println("Veuillez choisir la priorité :");
						 prio = sc.nextInt();
						 ok = true;
					 } catch(InputMismatchException e) {
						 System.out.println("Erreur, veuillez entrer une valeur numérique.");
					 }
				 }
				 p1 = new Processus(nomProcessus, debutProcessus, dureeProcessus,prio);
			 }
			else {
			 p1 = new Processus(nomProcessus, debutProcessus, dureeProcessus);
			 }
			liste.add(p1);

		}
		ok = false;
		
		 if(faire==4) {
			 while (!ok) {
					sc = new Scanner(System.in);
					try {
						System.out.println("Veuillez choisir le quantum ?");
						nbrsproc = sc.nextInt();
						ok = true;
					} catch (InputMismatchException e) {
						System.out.println("Erreur, veuillez entrer une valeur numérique.");
					}
				}
		}
		 
		
		switch (faire) {

		case 1:
			f.Fcfs(liste);
			break;
		case 2:
			sj.Sjf(liste);
			break;
		case 3:
			st.Srtf(liste);
			break;
		case 4:
			r.RoundR(liste,nbrsproc);
			break;
		case 5:
			pp.PrioPre(liste);
			break;
		case 6:
			pnp.PrioNoPre(liste);
		}

	}

	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		boolean ok = false;
		int faire = 0;
		while (!ok) {
			sc = new Scanner(System.in);
			try {
				System.out.println(
						"\nQuel algorithme voulez vous utilisé ?\n1. FCFS (First Come First Served) \n2. SJF (Shortest Job First)\n3. SRTF (Shortest Remaining Time First) \n4. RR (Round Robin)\n5. Priorité non préemptif\n6. Priorité préemptif");
				faire = sc.nextInt();
				ok = true;
			} catch (InputMismatchException e) {
				System.out.println("Erreur, veuillez entrer une valeur numérique.");
			}
			ok = false;

			if (faire == 1 || faire == 2 || faire == 3 || faire == 4|| faire==5|| faire ==6) {
				int nbrsproc = 0;
				
				while (!ok) {
					sc = new Scanner(System.in);
					try {
						System.out.println("Combien voulez vous de processus ?");
						nbrsproc = sc.nextInt();
						ok = true;
					} catch (InputMismatchException e) {
						System.out.println("Erreur, veuillez entrer une valeur numérique.");
					}
				}
				ok = true;
				System.out.println("\nComment voulez-vous génerer les processus ?\n1. Manuellement \n2. Automatiquement");
				int choix = 0;
				ok = false;
				while (!ok || choix!=1 && choix!=2 ) {
					try {
						 
					
						choix = sc.nextInt();
						
						ok = true;
						
					} catch (InputMismatchException e) {
						System.out.println("Erreur, veuillez saisir 1 ou 2");
					}
				}
				
				
			
				if (choix == 1)
					SaisieProc(nbrsproc, faire);
				else if (choix == 2)
					aleatoire(nbrsproc, faire);
				else {

				}
				
			}
		}
	}

}
