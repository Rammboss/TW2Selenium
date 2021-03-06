package TB2.NewStructure.common.hibernate.dao;

import TB2.NewStructure.common.hibernate.model.Provinz;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProvinzDao extends CrudRepository<Provinz, Integer> {
	List<Provinz> findByName(String name);

	Optional<Provinz> findByXAndY(int x, int y);

	List<Provinz> findAll();
}
