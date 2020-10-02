package processus;

import java.util.Comparator;

public class ComparatorDuree implements Comparator<Processus> {

	@Override
	public int compare(Processus proc1, Processus proc2){
		return (int) (proc1.getDuree() - proc2.getDuree());
	}
}
