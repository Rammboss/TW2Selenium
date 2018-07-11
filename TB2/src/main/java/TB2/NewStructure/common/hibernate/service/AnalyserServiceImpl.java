package TB2.NewStructure.common.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import TB2.NewStructure.common.hibernate.dao.AnalyserDao;
import TB2.NewStructure.common.hibernate.model.Analyser;

@Service("analyserService")
@Transactional
public class AnalyserServiceImpl implements AnalyserService {

	@Autowired
	private AnalyserDao dao;

	public void saveAnalyser(Analyser a) {
		dao.saveAnalyser(a);

	}

	public void updateAnalyser(Analyser a) {
		dao.update(a);

	}

	public Analyser findById(int id) {

		return dao.findbyId(id);
	}

}
