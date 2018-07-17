package TB2.NewStructure.common.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import TB2.NewStructure.common.hibernate.dao.EigenesDorfDao;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;

@Service("eigenesDorfService")
@Transactional
public class EigenesDorfServiceImpl implements EigenesDorfService {

	@Autowired
	private EigenesDorfDao dao;

	public EigenesDorf findBySpielerAndName(String name, String spieler) {
		return (EigenesDorf) dao.findBySpielerAndName(name, spieler);
	}

	public void saveDorf(EigenesDorf dorf) {
		dao.saveDorf(dorf);

	}

	public void updateDorf(EigenesDorf dorf) {
		dao.updateDorf(dorf);

	}

	public EigenesDorf findById(int id) {

		return dao.findById(id);
	}

	public List<EigenesDorf> findAll() {
		return dao.findAll();
	}

	public EigenesDorf findByXandY(int x, int y) {
		return dao.findByXandY(x, y);
	}

	public List<EigenesDorf> findBySpieler(String spieler) {
		return dao.findBySpieler(spieler);

	}

}
