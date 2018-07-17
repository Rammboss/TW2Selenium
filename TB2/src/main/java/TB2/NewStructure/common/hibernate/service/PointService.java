package TB2.NewStructure.common.hibernate.service;

import java.util.List;

import TB2.NewStructure.common.hibernate.model.Barbarendorf;
import TB2.NewStructure.common.hibernate.model.Point;

public interface PointService {	
	
	void savePoint(Point dorf);
	
	List<Point> findByName(String name);

	void updatePoint(Point dorf);
	
	Point findById(int id);
  	
	List<Point> findAll();
	
	

}
