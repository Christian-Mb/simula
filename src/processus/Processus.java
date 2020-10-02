package processus;

import java.util.Comparator;

public class Processus {
	
	private String nom;
	private int debut;
	private int duree;
	private int priorite;
	private int waitingTime;
	private int executeTime;

	/**
	 * @param waitingTime2
	 * @param fin
	 */
	public Processus(int fin, int waitingTime) {
		super();
		this.waitingTime = waitingTime;
		this.executeTime = fin;
	}
	public Processus(String nom, int debut, int duree){
		this.nom = nom;
		this.debut = debut;
		this.duree = duree;
	}
	
	
	







	public Processus(String nom, int debut, int duree, int priorite){
		this.nom = nom;
		this.debut = debut;
		this.duree = duree;
		this.priorite = priorite;
	}
	
	 public Processus(){ 
         
	        this.nom = null;
	        this.debut = 1;
	        this.duree= 1;
	        this.priorite = 0;
	         
	    }
	 
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return the debut
	 */
	public int getDebut() {
		return debut;
	}

	/**
	 * @return the duree
	 */
	public int getDuree() {
		return duree;
	}
	
	public void setDuree(int duree) {
		this.duree = duree;
	}

	/**
	 * @return the priorite
	 */
	public int getPriorite() {
		return priorite;
	}

	@Override
	public String toString() {
		return "Processus [nom=" + nom + ", debut=" + debut + ", duree=" + duree + ", priorite=" + priorite
				+ ", waitingTime=" + waitingTime + ", executeTime=" + executeTime + "]";
	}

	
	
	/**
	 * @return the waitingTime
	 */
	public int getWaitingTime() {
		return  waitingTime;
	
	}





	/**
	 * @param waitingTime the waitingTime to set
	 */
	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}





	/**
	 * @return the executeTime
	 */
	public int getExecuteTime() {
		return executeTime;
	}





	/**
	 * @param executeTime the executeTime to set
	 */
	public void setExecuteTime(int executeTime) {
		this.executeTime = executeTime;
	}



	public static Comparator<Processus> ComparatorDebut = new Comparator<Processus>(){
		
		@Override
		public int compare(Processus proc1, Processus proc2){
			return (int) (proc1.getDebut() - proc2.getDebut());
		}
	};
	
	public static Comparator<Processus> ComparatorDuree = new Comparator<Processus>(){
		
		@Override
		public int compare(Processus proc1, Processus proc2){
			return (int) (proc1.getDuree() - proc2.getDuree());
		}
	};



}
