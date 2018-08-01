package TB2.NewStructure.common.hibernate.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import TB2.NewStructure.common.hibernate.dao.BarbarendorfDao;
import TB2.NewStructure.common.hibernate.model.Barbarendorf;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;

@Service("barbarenDorfService")
@Transactional
public class BarbarendorfServiceImpl implements BarbarendorfService {

	@Autowired
	private BarbarendorfDao dao;

	public List<Barbarendorf> findByName(String name) {
		return dao.findByName(name);
	}

	public void saveDorf(Barbarendorf dorf) {
		dao.saveDorf(dorf);

	}

	public void updateDorf(Barbarendorf dorf) {
		dao.updateDorf(dorf);

	}

	public Barbarendorf findById(int id) {

		return dao.findById(id);
	}

	public List<Barbarendorf> findAll() {
		return dao.findAll();
	}
	
	public Barbarendorf findByXandY(int x, int y) {
		return dao.findByXandY(x, y);
	}

	@Override
	public void deleteDorf(Barbarendorf dorf) {
		dao.deleteDorf(dorf);
		
	}

}
