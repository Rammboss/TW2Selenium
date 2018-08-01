package TB2.NewStructure.common.hibernate.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import TB2.NewStructure.common.hibernate.model.Point;

public interface PointDao extends CrudRepository<Point, Integer> {

	List<Point> findAll();
}
