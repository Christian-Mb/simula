package processus;

import java.util.Comparator;

public class ComparatorDebut implements Comparator<Processus>{

	@Override
	public int compare(Processus proc1, Processus proc2){
		return (int) (proc1.getDebut() - proc2.getDebut());
	}
}
