package processus;

import java.util.Comparator;

public class ComparatorPrio implements Comparator<Processus> {

	@Override
	public int compare(Processus proc1, Processus proc2){
		return (int) (proc1.getPriorite() - proc2.getPriorite());
	}
}
