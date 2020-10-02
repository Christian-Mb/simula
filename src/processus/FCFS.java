/**
 * 
 */
package processus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import application.SampleController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author 21706589t
 *
 */
public class FCFS  {

	Processus p;
	private ObservableList<Processus> attente = FXCollections.observableArrayList();
	private int waitingTime[];
	private int[] fin ;	
	public void Fcfs(ObservableList<Processus> liste) {
		Collections.sort(liste, Processus.ComparatorDebut);
		System.out.println(liste);
		waitingTime = new int[liste.size()];
		fin = new int[liste.size()];	
		int somme =0;
		int n = 0;
		int temp = liste.get(0).getDebut();
		
		System.out.println("---------------------------------Schema de Grantt-----------------------------------");
		System.out.print("FCFS" + " : ");
		for (int i = 0; i < liste.size(); i++) {
			for (int j = 0; j < liste.get(i).getDuree(); j++) {
				System.out.print(liste.get(i).getNom() + " - ");
				temp++;
			}
			fin[i] = temp;
			waitingTime[i] = temp - liste.get(i).getDuree() - liste.get(i).getDebut();
			somme += waitingTime[i];
			
			p=new Processus(fin[i], waitingTime[i]);
		
			
		}
		
		System.out.println("\n-----------------------------------Fin des processus-----------------------------------");
		for (int i = 0; i < fin.length; i++) {

			System.out.println(liste.get(i).getNom() + " : " + "temps d'exécution = " + fin[i] + "   "
					+ "temps d'attente = " + waitingTime[i]);
			
		
		
		}
		System.out.println("************************************Statistiques************************************");
		
			System.out.println("le temps d'exécution est de : " + fin[fin.length-1]);
		
		
		System.out.println("le temps moyen d'attente est de : "+(double)somme/waitingTime.length+" ms");
	}
	
}
