package TB2.NewStructure.common.hibernate.dao;

import TB2.NewStructure.common.hibernate.model.Analyser;

public interface AnalyserDao {
	void saveAnalyser(Analyser a);

	Analyser findbyId(int id);

	void update(Analyser a);

}
