package processus;

import java.util.ArrayList;
import java.util.Collections;

import javafx.collections.ObservableList;

public class RR {

	public void RoundR(ObservableList<Processus> liste, int N) {
		Collections.sort(liste, new ProcessusComparator(new ComparatorDebut(), new ComparatorDuree()));
		System.out.println(liste);

		int somme = 0;
		int temp = liste.get(0).getDebut();
		int waitingTime[] = new int[liste.size()];
		int[] fin = new int[liste.size()];
		System.out.println("---------------------------------Schema de Grantt-----------------------------------");
		System.out.print("Round Robin" + " : ");
		int nbquant = 0;

		for (int i = 0; i < liste.size(); i++) {
			for (int j = 0; j < liste.get(i).getDuree(); j++) {
				temp++;
			}
				
				
			nbquant += liste.get(i).getDuree();
			fin[i] = temp + nbquant/N -1;
			waitingTime[i] = temp + nbquant/N - liste.get(i).getDuree() - liste.get(i).getDebut();
			somme += waitingTime[i];
		}
		nbquant = nbquant / N;
		System.out.println("nombre de fois "+nbquant);
		for (int a = 0; a < nbquant; a++) {
			for (int i = 0; i < liste.size(); i++) {
				
				for (int j = 0; j < N; j++) {
					if(liste.get(i).getDuree() >0)
					System.out.print(liste.get(i).getNom() + " - ");

					
					liste.get(i).setDuree(liste.get(i).getDuree()-1);
				}
				

			}
		}
		System.out.println("\n-----------------------------------Fin des processus-----------------------------------");

		for (int i = 0; i < fin.length; i++) {

			System.out.println(liste.get(i).getNom() + " : " + "temps d'exécution = " + fin[i] + "   "
					+ "temps d'attente = " + waitingTime[i]);

		}

		System.out.println("************************************Statistiques************************************");

		System.out.println("le temps d'exécution est de : " + fin[fin.length - 1]);

		System.out.println("le temps moyen d'attente est de : " + (double) somme / waitingTime.length);
	}
}
