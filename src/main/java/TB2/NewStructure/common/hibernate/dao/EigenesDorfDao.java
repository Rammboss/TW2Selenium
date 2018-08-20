package TB2.NewStructure.common.hibernate.dao;

import TB2.NewStructure.common.hibernate.model.EigenesDorf;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EigenesDorfDao extends CrudRepository<EigenesDorf, Integer> {

	EigenesDorf findBySpielerAndName(String name, String spieler);

	List<EigenesDorf> findBySpieler(String spieler);

	Optional<EigenesDorf> findByXAndY(int x, int y);

	List<EigenesDorf> findAll();

}