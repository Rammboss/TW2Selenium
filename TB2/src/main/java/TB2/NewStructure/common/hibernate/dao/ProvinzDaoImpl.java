package TB2.NewStructure.common.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import TB2.NewStructure.common.hibernate.model.Provinz;

@Repository("provinzDao")
public class ProvinzDaoImpl extends AbstractDao implements ProvinzDao {

	public void saveProvinz(Provinz dorf) {
		persist(dorf);

	}

	@SuppressWarnings("unchecked")
	public List<Provinz> findByName(String name) {
		Criteria criteria = getSession().createCriteria(Provinz.class);

		return criteria.add(Restrictions.like("name", name)).list();
	}

	public void updateProvinz(Provinz dorf) {
		getSession().update(dorf);

	}

	@SuppressWarnings("unchecked")
	public Provinz findById(int id) {
		return (Provinz) getSession().get(Provinz.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Provinz> findAll() {
		Criteria criteria = getSession().createCriteria(Provinz.class);
		List<Provinz> list = criteria.list();
		return list;
	}

}