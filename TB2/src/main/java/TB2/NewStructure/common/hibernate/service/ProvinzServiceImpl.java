package TB2.NewStructure.common.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import TB2.NewStructure.common.hibernate.dao.PointDao;
import TB2.NewStructure.common.hibernate.dao.ProvinzDao;
import TB2.NewStructure.common.hibernate.model.Provinz;

@Service("provinzService")
@Transactional
public class ProvinzServiceImpl implements ProvinzService {

	@Autowired
	private ProvinzDao dao;

	public List<Provinz> findByName(String name) {
		return dao.findByName(name);
	}

	public void saveProvinz(Provinz dorf) {
		dao.saveProvinz(dorf);

	}

	public void updateProvinz(Provinz dorf) {
		dao.updateProvinz(dorf);

	}

	public Provinz findById(int id) {

		return dao.findById(id);
	}

	public List<Provinz> findAll() {
		return dao.findAll();
	}
}