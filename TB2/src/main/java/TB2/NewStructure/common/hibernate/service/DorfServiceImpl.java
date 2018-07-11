package TB2.NewStructure.common.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import TB2.NewStructure.common.hibernate.dao.DorfDao;
import TB2.NewStructure.common.hibernate.model.Dorf;

@Service("dorfService")
@Transactional
public class DorfServiceImpl implements DorfService {

	@Autowired
	private DorfDao dao;

	public List<Dorf> findByName(String name) {
		return dao.findByName(name);
	}

	public void saveDorf(Dorf dorf) {
		dao.saveDorf(dorf);

	}

	public void updateDorf(Dorf dorf) {
		dao.updateDorf(dorf);

	}

	public Dorf findById(int id) {

		return dao.findById(id);
	}

}
