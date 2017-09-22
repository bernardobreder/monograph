
package breder.risk.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import breder.risk.model.IRisk;

public class RiskTopList extends RiskList {

	protected IRisk[] accept(IRisk[] risks) {
		Arrays.sort(risks, new Comparator<IRisk>() {

			@Override
			public int compare(IRisk o1, IRisk o2) {
				if (o1.getCategory().ordinal() != o2.getCategory().ordinal()) {
					return ((Integer) o1.getCategory().ordinal()).compareTo(o2.getCategory().ordinal());
				} else {
					return o1.getName().compareTo(o2.getName());
				}
			}
		});
		List<IRisk> list = new ArrayList<IRisk>();
		for (IRisk risk : risks) {
			if (!risk.getName().startsWith("[task]")) {
				list.add(risk);
			}
		}
		return list.toArray(new IRisk[list.size()]);
	}

}
