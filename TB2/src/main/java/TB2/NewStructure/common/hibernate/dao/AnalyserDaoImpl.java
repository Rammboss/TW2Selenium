package TB2.NewStructure.common.hibernate.dao;

import org.springframework.stereotype.Repository;

import TB2.NewStructure.common.hibernate.model.Analyser;

@Repository("AnalyserDao")
public class AnalyserDaoImpl extends AbstractDao implements AnalyserDao {

	public void saveAnalyser(Analyser a) {
		persist(a);

	}

	public Analyser findbyId(int id) {
		return (Analyser) getSession().get(Analyser.class, id);
	}

	public void update(Analyser a) {
		getSession().update(a);

	}

}
