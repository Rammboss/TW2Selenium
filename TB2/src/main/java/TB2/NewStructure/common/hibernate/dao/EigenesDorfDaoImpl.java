package TB2.NewStructure.common.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import TB2.NewStructure.common.hibernate.model.Barbarendorf;
import TB2.NewStructure.common.hibernate.model.Dorf;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;
import TB2.NewStructure.common.hibernate.model.Provinz;

@Repository("eigenesDorfDao")
public class EigenesDorfDaoImpl extends AbstractDao implements EigenesDorfDao {

	public void saveDorf(EigenesDorf dorf) {
		persist(dorf);

	}

	@SuppressWarnings("unchecked")
	public EigenesDorf findBySpielerAndName(String spieler, String name) {
		Criteria criteria = getSession().createCriteria(EigenesDorf.class);
		List<EigenesDorf> list = new ArrayList<EigenesDorf>();
		if (list.size() == 1) {
			return (EigenesDorf) list.get(0);

		} else {
			return new EigenesDorf(-1, -1, "Kein Name", -1, "Kein Spieler");
		}

	}

	@SuppressWarnings("unchecked")
	public List<EigenesDorf> findBySpieler(String spieler) {
		Criteria criteria = getSession().createCriteria(EigenesDorf.class);

		return criteria.add(Restrictions.like("spieler", spieler)).list();
	}

	public void updateDorf(EigenesDorf dorf) {
		getSession().update(dorf);

	}

	@SuppressWarnings("unchecked")
	public EigenesDorf findById(int id) {
		// Criteria criteria = getSession().createCriteria(Dorf.class);
		// return criteria.add(Restrictions.like("id", id)).list();
		return (EigenesDorf) getSession().get(EigenesDorf.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<EigenesDorf> findAll() {

		Criteria criteria = getSession().createCriteria(EigenesDorf.class);

		// List<EigenesDorf> tmp = criteria.add(Restrictions.like("spieler",
		// "Rammboss")).list();
		return criteria.list();

	}

	public EigenesDorf findByXandY(int x, int y) {
		Criteria criteria = getSession().createCriteria(EigenesDorf.class);

		List<EigenesDorf> list = criteria.add(Restrictions.like("x", x)).add(Restrictions.like("y", y)).list();

		if (list.size() == 1) {
			return (EigenesDorf) list.get(0);

		} else {
			return new EigenesDorf(-1, -1, "Kein Name", -1, "Kein Spieler");
		}
	}
}