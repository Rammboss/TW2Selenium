package TB2.NewStructure.common.hibernate.dao;

import TB2.NewStructure.common.hibernate.model.Point;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PointDao extends CrudRepository<Point, Integer> {

	List<Point> findAll();
}
