package TB2.NewStructure.common.hibernate.dao;

import java.util.List;

import TB2.NewStructure.common.hibernate.model.Point;

public interface PointDao {
	void savePoint(Point dorf);

	List<Point> findByName(String name);

	Point findById(int id);

	void updatePoint(Point dorf);

	List<Point> findAll();

	void initPoints();
}
