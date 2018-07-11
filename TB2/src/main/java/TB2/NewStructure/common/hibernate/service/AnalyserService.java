package TB2.NewStructure.common.hibernate.service;

import TB2.NewStructure.common.hibernate.model.Analyser;

public interface AnalyserService {
	void saveAnalyser(Analyser a);

	void updateAnalyser(Analyser a);

	Analyser findById(int id);

}
