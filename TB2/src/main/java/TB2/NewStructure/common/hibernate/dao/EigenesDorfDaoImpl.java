package TB2.NewStructure.common.hibernate.dao;

import java.util.List;

import javax.persistence.DiscriminatorValue;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import TB2.NewStructure.common.hibernate.model.Barbarendorf;
import TB2.NewStructure.common.hibernate.model.Dorf;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;

@Repository("eigenesDorfDao")
public class EigenesDorfDaoImpl extends AbstractDao implements EigenesDorfDao {

	public void saveDorf(EigenesDorf dorf) {
		persist(dorf);

	}

	@SuppressWarnings("unchecked")
	public List<EigenesDorf> findByName(String name) {
		Criteria criteria = getSession().createCriteria(EigenesDorf.class);

		return criteria.add(Restrictions.like("name", name)).list();
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
		
		//List<EigenesDorf> tmp = criteria.add(Restrictions.like("spieler", "Rammboss")).list();
		 return criteria.list();
		
	}
}