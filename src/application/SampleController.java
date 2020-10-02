package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.IntegerStringConverter;
import processus.ComparatorDebut;
import processus.ComparatorDuree;
import processus.ComparatorPrio;
import processus.FCFS;
import processus.PrioNoPre;
import processus.PrioPre;
import processus.Processus;
import processus.ProcessusComparator;
import processus.RR;
import processus.SJF;
import processus.SRTF;

/**
 * @author Christian Mada-Mbari
 *
 */
public class SampleController implements Initializable {
	private ObservableList<Processus> liste = FXCollections.observableArrayList();
	private ObservableList<Processus> listes = FXCollections.observableArrayList();
	private ObservableList<Processus> list = FXCollections.observableArrayList();

	@FXML
	private CheckBox fcfs = new CheckBox();
	@FXML
	private CheckBox sjf = new CheckBox();
	@FXML
	private CheckBox srtf = new CheckBox();
	@FXML
	private CheckBox rr = new CheckBox();
	@FXML
	private CheckBox priorite = new CheckBox();
	@FXML
	private CheckBox priorite2 = new CheckBox();
	@FXML
	private CheckBox manuellement = new CheckBox();
	@FXML
	private CheckBox automatiquement = new CheckBox();

	@FXML
	private TableView<Processus> saisie = new TableView<Processus>();
	@FXML
	private TableColumn<Processus, String> nomProcessus = new TableColumn<>();
	@FXML
	private TableColumn<Processus, Integer> arriveTime = new TableColumn<>();
	@FXML
	private TableColumn<Processus, Integer> burstTime = new TableColumn<>();
	@FXML
	private TableColumn<Processus, Integer> SaisieABS = new TableColumn<>();

	@FXML
	private TableView<Processus> resultat = new TableView<Processus>();
	@FXML
	private TableColumn<Processus, Integer> executeTime = new TableColumn<>();
	@FXML
	private TableColumn<Processus, Integer> waitingTime = new TableColumn<>();
	

	@FXML
	Label label1 = new Label();
	@FXML
	Label label2 = new Label();
	@FXML
	Label label3 = new Label();
	@FXML
	Label label4 = new Label();
	@FXML
	Label label5 = new Label();

	@FXML
	TextArea textArea = new TextArea();

	@FXML
	Button lancer = new Button();
	@FXML
	Button cancel = new Button();

	@FXML
	Spinner<Integer> quantum = new Spinner<Integer>();
	@FXML
	Spinner<Integer> nbrsProcess = new Spinner<Integer>();

	
	@FXML
	private void box2() {
		if (manuellement.isSelected()) {
			automatiquement.setDisable(true);
			saisie.setVisible(true);
			label5.setVisible(true);
			nbrsProcess.setVisible(true);
		} else {
			automatiquement.setDisable(false);
			saisie.setVisible(false);
			label5.setVisible(false);
			nbrsProcess.setVisible(false);
		}
	}

	@FXML
	private void box3() {
		if (automatiquement.isSelected()) {
			manuellement.setDisable(true);
			saisie.setVisible(true);
			label5.setVisible(true);
			nbrsProcess.setVisible(true);

			FCFS f = new FCFS();
			SJF sj = new SJF();
			SRTF st = new SRTF();
			RR r = new RR();
			PrioNoPre pn= new PrioNoPre();
			PrioPre pp = new PrioPre();
			Random random = new Random();
		
			
			Processus p1 = null;
			Processus p2 = null;
			Processus p3= null;
			String chaine = "";
			int n = 0, n1 = 0, n2=0;
			for (int i = 0; i < nbrsProcess.getValue(); i++) {
				
				while (n1 == 0 ||n1<0) {
					n1 = (int) (Math.random() * (16));
				}
				while (n2 == 0||n2<0) {
					n2 = (int) (Math.random() * (15));
				}
				while (n == 0||n<0) {
					n = (int) (Math.random() * (12));
				}
				char c = (char) (random.nextInt(26) + 97);
				chaine = String.valueOf(c);
				String nomProcessus = null;
				int debutProcessus = 0;
				int dureeProcessus = 0;
				int priorites =0;
				debutProcessus = n;
				dureeProcessus = n1;
				priorites=n2;
				nomProcessus = chaine;
				
				p1 = new Processus(nomProcessus, debutProcessus, dureeProcessus);
				p3 = new Processus(nomProcessus, debutProcessus, dureeProcessus);
				p2= new Processus(nomProcessus, debutProcessus, dureeProcessus, priorites);
				
				 
				if( priorite.isSelected() || priorite2.isSelected()) {
					liste.add(p2);
				}
				else if(rr.isSelected()) {
					liste.add(p3);
					listes.add(p2);
				}
				else {
				liste.add(p1);
				}
				saisie.setItems(liste);
				n1 = 0;
				n = 0;
				n2=0;
			
		}
			if (rr.isSelected()) {
				r.RoundR(listes,quantum.getValue());
				Collections.sort(liste, new ProcessusComparator(new ComparatorDebut(), new ComparatorDuree()));
				Processus p =null;
				String affichage = new String();
				String affichage2 = new String();
				String affichage3 = new String();
				String affichage1 = new String();
				String affichage4 = new String();
				int nbquant = 0;			
				int somme = 0;
				int temp = liste.get(0).getDebut();
				int waitingTime[] = new int[liste.size()];
				int[] fin = new int[liste.size()];
				affichage="Round Robin :---------------------------------Schema de Grantt-----------------------------------";
		
				affichage2="************************************Statistiques************************************";
				for (int i = 0; i < liste.size(); i++) {
					for (int j = 0; j < liste.get(i).getDuree(); j++) {
						temp++;
					}
						
						
					nbquant += liste.get(i).getDuree();
					fin[i] = temp + nbquant/quantum.getValue() -1;
					waitingTime[i] = temp + nbquant/quantum.getValue() - liste.get(i).getDuree() - liste.get(i).getDebut();
					somme += waitingTime[i];
					p=new Processus(fin[i], waitingTime[i]);
					list.add(p);
				}
				resultat.setItems(list);
				
				nbquant = nbquant / quantum.getValue();
				for (int a = 0; a < nbquant; a++) {
					for (int i = 0; i < liste.size(); i++) {
						
						for (int j = 0; j < quantum.getValue(); j++) {
							if(listes.get(i).getDuree() >0)
							affichage1 +=liste.get(i).getNom() + " - ";

							
							listes.get(i).setDuree(liste.get(i).getDuree()-1);
						}
						

					}
				}
				affichage2= "************************************Statistiques************************************";
				affichage3="le temps d'exécution est de : " + fin[fin.length - 1];

				affichage4="le temps moyen d'attente est de : " + (double) somme / waitingTime.length;
					
				
				textArea.appendText("\n"+affichage+"\n");
				textArea.appendText("\n"+affichage1+"\n");
				textArea.appendText("\n"+affichage2+"\n");
				textArea.appendText("\n"+affichage3+"\n");
				textArea.appendText("\n"+affichage4+"\n");
			
			
			
				
			
				}
			else if(priorite.isSelected()) {
				pn.PrioNoPre(liste);
				Collections.sort(liste, new ProcessusComparator(
						new ComparatorDebut(),
						new ComparatorPrio(),
						new ComparatorDuree())
						);
				System.out.println(liste);
			
				int somme =0;
				String affichage = new String();
				String affichage1 = new String();
				String affichage2 = new String();
				String affichage3 = new String();
				String affichage4 = new String();
				int temp = liste.get(0).getDebut();
				int waitingTime[] = new int[liste.size()];
				int[] fin = new int[liste.size()];
				affichage1= "Piorite sans preemption :---------------------------------Schema de Gantt-----------------------------------";
				System.out.print("Priorité non préemptif" + " : ");
				for (int i = 0; i < liste.size(); i++) {
					for (int j = 0; j < liste.get(i).getDuree(); j++) {
						System.out.print(liste.get(i).getNom() + " - ");
						temp++;
						
						affichage += affichage.valueOf(liste.get(i).getNom() + " - ");
					}
					fin[i] = temp;
					waitingTime[i] = temp - liste.get(i).getDuree() - liste.get(i).getDebut();
					somme += waitingTime[i];

					Processus p=  new Processus(fin[i],waitingTime[i]);
				
					list.add(p);
					
				}
				affichage2= "************************************Statistiques************************************";
				
				affichage3 +="le temps d'exécution est de : " + fin[fin.length-1];			
				affichage4 +="le temps moyen d'attente est de : "+(double)somme/waitingTime.length;
				resultat.setItems(list);
				textArea.appendText("\n"+affichage1+"\n");
				textArea.appendText("\n"+affichage+"\n");
				textArea.appendText("\n"+affichage2+"\n");
				textArea.appendText("\n"+affichage3+"\n");
				textArea.appendText("\n"+affichage4+"\n");
				
				
				
				
			}
	else if(priorite2.isSelected()) {
				pp.PrioPre(liste);
				Collections.sort(liste, new ProcessusComparator(
						new ComparatorDebut(),
						new ComparatorPrio())
						);
				System.out.println(liste);
			
				int somme =0;
			String affichage = new String();
			String affichage1 = new String();
			String affichage2 = new String();
			String affichage3 = new String();
			String affichage4 = new String();
				int temp = liste.get(0).getDebut();
				int waitingTime[] = new int[liste.size()];
				int[] fin = new int[liste.size()];
				affichage ="Piorite avec preemption :---------------------------------Schema de Grantt-----------------------------------";
				System.out.print("Priorité préemptif" + " : ");
				for (int i = 0; i < liste.size(); i++) {
					for (int j = 0; j < liste.get(i).getDuree(); j++) {
						affichage1 += affichage.valueOf(liste.get(i).getNom() + " - ");
						temp++;
					}
					fin[i] = temp;
					waitingTime[i] = temp - liste.get(i).getDuree() - liste.get(i).getDebut();
					somme += waitingTime[i];
					Processus p=  new Processus(fin[i],waitingTime[i]);
					
					list.add(p);
					affichage2="************************************Statistiques************************************";
					
					affichage3= "le temps d'exécution est de : " + fin[fin.length-1];
				
				
				affichage4 ="le temps moyen d'attente est de : "+(double)somme/waitingTime.length;
				}resultat.setItems(list);
				textArea.appendText("\n"+affichage+"\n");
				textArea.appendText("\n"+affichage1+"\n");
				textArea.appendText("\n"+affichage2+"\n");
				textArea.appendText("\n"+affichage3+"\n");
				textArea.appendText("\n"+affichage4+"\n");
			}
			else if (fcfs.isSelected()) {
				f.Fcfs(liste);
				String affichage = new String();
				String affichage1 = new String();
				String affichage2 = new String();
				String affichage3 = new String();
				String affichage4 = new String();
				Collections.sort(liste, Processus.ComparatorDebut);
				System.out.println(liste);
				int [] waitingTime = new int[liste.size()];
				int[] fin = new int[liste.size()];	
				int somme =0;
				
				int temp = liste.get(0).getDebut();
				for (int i = 0; i < liste.size(); i++) {
					for (int j = 0; j < liste.get(i).getDuree(); j++) {
						affichage1 +=affichage.valueOf(liste.get(i).getNom() + " - ");
						temp++;
					}
					fin[i] = temp;
					waitingTime[i] = temp - liste.get(i).getDuree() - liste.get(i).getDebut();
					somme += waitingTime[i];
				
					
					Processus p=  new Processus(fin[i],waitingTime[i]);
				
					list.add(p);
					
					
				}affichage= "FCFS:---------------------------------Schema de Gantt-----------------------------------";
				System.out.println("\n-----------------------------------Fin des processus-----------------------------------");
				for (int i = 0; i < fin.length; i++) {

					System.out.println(liste.get(i).getNom() + " : " + "temps d'exécution = " + fin[i] + "   "
							+ "temps d'attente = " + waitingTime[i]);
					
				
				
				}
				affichage2="************************************Statistiques************************************";
				
				affichage3="le temps d'exécution est de : " + fin[fin.length-1];
				
				
				affichage4= "le temps moyen d'attente est de : " +(double)somme/waitingTime.length;
				
				textArea.appendText("\n"+affichage+"\n");
				textArea.appendText("\n"+affichage1+"\n");
				textArea.appendText("\n"+affichage2+"\n");
				textArea.appendText("\n"+affichage3+"\n");
				textArea.appendText("\n"+affichage4+"\n");

				resultat.setItems(list);
			}

			else if (sjf.isSelected()) {
				sj.Sjf(liste);
			Collections.sort(liste, Processus.ComparatorDuree);
			System.out.println(liste);
			String affichage = new String();
			String affichage1 = new String();
			String affichage2 = new String();
			String affichage3 = new String();
			String affichage4 = new String();
			
			int somme =0;
			int temp = liste.get(0).getDebut();
			int waitingTime[] = new int[liste.size()];
			int[] fin = new int[liste.size()];
		affichage= "SJF: ---------------------------------Schema de Grantt-----------------------------------";
			System.out.print("SJF" + " : ");
			for (int i = 0; i < liste.size(); i++) {
				for (int j = 0; j < liste.get(i).getDuree(); j++) {
				affichage1 +=	liste.get(i).getNom() + " - ";
					temp++;
				}
				fin[i] = temp;
				waitingTime[i] = temp - liste.get(i).getDuree() - liste.get(i).getDebut();
				somme += waitingTime[i];
				Processus p=  new Processus(fin[i],waitingTime[i]);
			
				list.add(p);
				affichage2= "************************************Statistiques************************************";
				
				affichage3= "le temps d'exécution est de : " + fin[fin.length-1];
			
			
			affichage4 ="le temps moyen d'attente est de : "+(double)somme/waitingTime.length;
			}
			resultat.setItems(list);
			textArea.appendText("\n"+affichage+"\n");
			textArea.appendText("\n"+affichage1+"\n");
			textArea.appendText("\n"+affichage2+"\n");
			textArea.appendText("\n"+affichage3+"\n");
			textArea.appendText("\n"+affichage4+"\n");
			}

			else if (srtf.isSelected()) {
				String affichage = new String();
				String affichage1 = new String();
				String affichage2 = new String();
				String affichage3 = new String();
				String affichage4 = new String();
				st.Srtf(liste);
				Collections.sort(liste, new ProcessusComparator(
						new ComparatorDebut(),
						new ComparatorDuree())
						);
				System.out.println(liste);
			
				int somme =0;
				int temp = liste.get(0).getDebut();
				int waitingTime[] = new int[liste.size()];
				int[] fin = new int[liste.size()];
				affichage ="STRF:---------------------------------Schema de Gantt-----------------------------------";
				System.out.print("SRTF" + " : ");
				for (int i = 0; i < liste.size(); i++) {
					for (int j = 0; j < liste.get(i).getDuree(); j++) {
						affichage1 +=liste.get(i).getNom() + " - ";
						temp++;
					}
					fin[i] = temp;
					waitingTime[i] = temp - liste.get(i).getDuree() - liste.get(i).getDebut();
					somme += waitingTime[i];
					Processus p=  new Processus(fin[i],waitingTime[i]);
					affichage2="************************************Statistiques************************************";
					
					affichage3="le temps d'exécution est de : " + fin[fin.length-1];
				
				
			affichage4="le temps moyen d'attente est de : "+(double)somme/waitingTime.length;
					list.add(p);
				}resultat.setItems(list);
				textArea.appendText("\n"+affichage+"\n");
				textArea.appendText("\n"+affichage1+"\n");
				textArea.appendText("\n"+affichage2+"\n");
				textArea.appendText("\n"+affichage3+"\n");
				textArea.appendText("\n"+affichage4+"\n");
}

			
		}

		 else {
			manuellement.setDisable(false);
			saisie.setVisible(false);
			label5.setVisible(false);
			nbrsProcess.setVisible(false);
		}
	
		}
		

	@FXML
	private void box() {
		if (fcfs.isSelected()) {
			label2.setVisible(true);
			label4.setVisible(false);
			lancer.setVisible(true);
			cancel.setVisible(true);
			saisie.setVisible(false);
			sjf.setDisable(true);
			rr.setDisable(true);
			srtf.setDisable(true);
			priorite.setDisable(true);
			priorite2.setDisable(true);
			textArea.setVisible(false);
			resultat.setVisible(false);
			manuellement.setDisable(false);
			automatiquement.setDisable(false);
			SaisieABS.setVisible(false);

		} else {
			label4.setVisible(true);
			label2.setVisible(false);
			lancer.setVisible(false);
			cancel.setVisible(false);
			saisie.setVisible(false);
			sjf.setDisable(false);
			rr.setDisable(false);
			srtf.setDisable(false);
			priorite.setDisable(false);
			priorite2.setDisable(false);
			textArea.setVisible(false);
			resultat.setVisible(false);
			manuellement.setDisable(true);
			automatiquement.setDisable(true);
			SaisieABS.setVisible(false);
		}
	}

	@FXML
	private void box4() {
		if (sjf.isSelected()) {
			label2.setVisible(true);
			label4.setVisible(false);
			lancer.setVisible(true);

			cancel.setVisible(true);
			saisie.setVisible(false);
			fcfs.setDisable(true);
			rr.setDisable(true);
			srtf.setDisable(true);
			priorite.setDisable(true);
			priorite2.setDisable(true);
			textArea.setVisible(false);
			resultat.setVisible(false);
			manuellement.setDisable(false);
			automatiquement.setDisable(false);
			SaisieABS.setVisible(false);

		} else {
			label4.setVisible(true);
			label2.setVisible(false);
			lancer.setVisible(false);
			cancel.setVisible(false);
			saisie.setVisible(false);
			fcfs.setDisable(false);
			rr.setDisable(false);
			srtf.setDisable(false);
			priorite.setDisable(false);
			priorite2.setDisable(false);
			textArea.setVisible(false);
			resultat.setVisible(false);
			manuellement.setDisable(true);
			automatiquement.setDisable(true);
			SaisieABS.setVisible(false);
		}
	}

	@FXML
	private void box5() {
		if (rr.isSelected()) {
			label2.setVisible(true);
			label4.setVisible(false);
			lancer.setVisible(true);

			cancel.setVisible(true);
			saisie.setVisible(false);
			fcfs.setDisable(true);
			sjf.setDisable(true);
			srtf.setDisable(true);
			priorite.setDisable(true);
			priorite2.setDisable(true);
			textArea.setVisible(false);
			resultat.setVisible(false);
			manuellement.setDisable(false);
			automatiquement.setDisable(false);
			SaisieABS.setVisible(false);
			quantum.setDisable(false);

		} else {
			label4.setVisible(true);
			label2.setVisible(false);
			lancer.setVisible(false);
			cancel.setVisible(false);
			saisie.setVisible(false);
			fcfs.setDisable(false);
			sjf.setDisable(false);
			srtf.setDisable(false);
			priorite.setDisable(false);
			priorite2.setDisable(false);
			textArea.setVisible(false);
			resultat.setVisible(false);
			manuellement.setDisable(true);
			automatiquement.setDisable(true);
			SaisieABS.setVisible(false);
			quantum.setDisable(true);
		}
	}

	@FXML
	private void box6() {
		if (srtf.isSelected()) {
			label2.setVisible(true);
			label4.setVisible(false);
			lancer.setVisible(true);

			cancel.setVisible(true);
			saisie.setVisible(false);
			fcfs.setDisable(true);
			sjf.setDisable(true);
			rr.setDisable(true);
			priorite.setDisable(true);
			priorite2.setDisable(true);
			textArea.setVisible(false);
			resultat.setVisible(false);
			manuellement.setDisable(false);
			automatiquement.setDisable(false);
			SaisieABS.setVisible(false);

		} else {
			label4.setVisible(true);
			label2.setVisible(false);
			lancer.setVisible(false);
			cancel.setVisible(false);
			saisie.setVisible(false);
			fcfs.setDisable(false);
			sjf.setDisable(false);
			rr.setDisable(false);
			priorite.setDisable(false);
			priorite2.setDisable(false);
			textArea.setVisible(false);
			resultat.setVisible(false);
			manuellement.setDisable(true);
			automatiquement.setDisable(true);
			SaisieABS.setVisible(false);
		}
	}

	@FXML
	private void box7() {
		if (priorite.isSelected()) {
			label2.setVisible(true);
			label4.setVisible(false);
			lancer.setVisible(true);

			cancel.setVisible(true);
			saisie.setVisible(false);
			fcfs.setDisable(true);
			sjf.setDisable(true);
			rr.setDisable(true);
			srtf.setDisable(true);
			priorite2.setDisable(true);
			textArea.setVisible(false);
			resultat.setVisible(false);
			manuellement.setDisable(false);
			automatiquement.setDisable(false);
			SaisieABS.setVisible(true);

		} else {
			label4.setVisible(true);
			label2.setVisible(false);
			lancer.setVisible(false);
			cancel.setVisible(false);
			saisie.setVisible(false);
			fcfs.setDisable(false);
			sjf.setDisable(false);
			rr.setDisable(false);
			srtf.setDisable(false);
			priorite2.setDisable(false);
			textArea.setVisible(false);
			resultat.setVisible(false);
			manuellement.setDisable(true);
			automatiquement.setDisable(true);
			SaisieABS.setVisible(false);
		}
	}

	@FXML
	private void box8() {
		if (priorite2.isSelected()) {
			label2.setVisible(true);
			label4.setVisible(false);
			lancer.setVisible(true);

			cancel.setVisible(true);
			saisie.setVisible(false);
			fcfs.setDisable(true);
			sjf.setDisable(true);
			rr.setDisable(true);
			priorite.setDisable(true);
			srtf.setDisable(true);
			textArea.setVisible(false);
			resultat.setVisible(false);
			manuellement.setDisable(false);
			automatiquement.setDisable(false);
			SaisieABS.setVisible(true);

		} else {
			label4.setVisible(true);
			label2.setVisible(false);
			lancer.setVisible(false);
			cancel.setVisible(false);
			saisie.setVisible(false);
			fcfs.setDisable(false);
			sjf.setDisable(false);
			rr.setDisable(false);
			priorite.setDisable(false);
			srtf.setDisable(false);
			textArea.setVisible(false);
			resultat.setVisible(false);
			manuellement.setDisable(true);
			automatiquement.setDisable(true);
			SaisieABS.setVisible(false);
		}
	}

	@FXML
	private void box9() {
		if (lancer.isArmed()) {
			resultat.setVisible(true);
			textArea.setVisible(true);
		} else {
			resultat.setVisible(false);
			textArea.setVisible(false);
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		box();

		cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

			}

		});

		SpinnerValueFactory<Integer> gradesValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
		this.quantum.setValueFactory(gradesValueFactory);
		quantum.getValue();
		SpinnerValueFactory<Integer> gradesValueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 13, 1);
		this.nbrsProcess.setValueFactory(gradesValueFactory1);
		nbrsProcess.getValue();

		this.nomProcessus.setCellValueFactory(new PropertyValueFactory<Processus, String>("nom"));
		this.arriveTime.setCellValueFactory(new PropertyValueFactory<Processus, Integer>("debut"));
		this.burstTime.setCellValueFactory(new PropertyValueFactory<Processus, Integer>("duree"));
		this.SaisieABS.setCellValueFactory(new PropertyValueFactory<Processus, Integer>("priorite"));

		this.executeTime.setCellValueFactory(new PropertyValueFactory<Processus, Integer>("executeTime"));
		this.waitingTime.setCellValueFactory(new PropertyValueFactory<Processus, Integer>("waitingTime"));

	}

}
