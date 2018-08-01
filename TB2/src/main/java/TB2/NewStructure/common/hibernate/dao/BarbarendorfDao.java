package TB2.NewStructure.common.hibernate.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import TB2.NewStructure.common.hibernate.model.Barbarendorf;

public interface BarbarendorfDao extends CrudRepository<Barbarendorf, Integer> {

	List<Barbarendorf> findByName(String name);

	Optional<Barbarendorf> findByXAndY(int x, int y);

	List<Barbarendorf> findAll();
}
