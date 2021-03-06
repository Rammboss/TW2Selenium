package TB2.NewStructure.common.hibernate.dao;

import TB2.NewStructure.common.hibernate.model.Dorf;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DorfDao extends CrudRepository<Dorf, Integer> {

    List<Dorf> findByName(String name);

    Optional<Dorf> findByXAndY(int x, int y);

    List<Dorf> findAll();
}
