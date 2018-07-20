package TB2.NewStructure.common.hibernate.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import TB2.NewStructure.common.hibernate.dao.BarbarendorfDao;
import TB2.NewStructure.common.hibernate.dao.PointDao;
import TB2.NewStructure.common.hibernate.model.Point;

@Service("pointService")
@Transactional
public class PointServiceImpl implements PointService {

	@Autowired
	private PointDao dao;

	public List<Point> findByName(String name) {
		return dao.findByName(name);
	}

	public void savePoint(Point dorf) {
		dao.savePoint(dorf);

	}

	public void updatePoint(Point dorf) {
		dao.updatePoint(dorf);

	}

	public Point findById(int id) {

		return dao.findById(id);
	}

	public List<Point> findAll() {
		return dao.findAll();
	}

	public void initPoints() {
		dao.initPoints();

	}

}
