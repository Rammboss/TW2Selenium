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

	public List<EigenesDorf> findByName(String name) {
		return dao.findByName(name);
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

}
