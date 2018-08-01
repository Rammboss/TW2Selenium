package TB2.NewStructure.common.hibernate.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import TB2.NewStructure.common.hibernate.model.Dorf;

public interface DorfDao extends CrudRepository<Dorf, Integer> {

	List<Dorf> findByName(String name);

	Optional<Dorf> findByXAndY(int x, int y);

	List<Dorf> findAll();
}
