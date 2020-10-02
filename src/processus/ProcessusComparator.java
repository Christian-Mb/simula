package processus;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ProcessusComparator implements Comparator<Processus> {

	private List<Comparator<Processus>> listeComparators;
	
	
	@SafeVarargs
	public ProcessusComparator(Comparator<Processus>... comparators) {
		this.listeComparators = Arrays.asList(comparators);
	}


	@Override
	public int compare(Processus proc1, Processus proc2) {
		for (Comparator<Processus> comparator : listeComparators) {
			int result = comparator.compare(proc1, proc2);
			if (result != 0) {
				return result;
			}
		}
		return 0;
	}

}
