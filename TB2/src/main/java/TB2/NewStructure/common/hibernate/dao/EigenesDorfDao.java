package TB2.NewStructure.common.hibernate.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import TB2.NewStructure.common.hibernate.model.EigenesDorf;

public interface EigenesDorfDao extends CrudRepository<EigenesDorf, Integer> {

	EigenesDorf findBySpielerAndName(String name, String spieler);

	List<EigenesDorf> findBySpieler(String spieler);

	Optional<EigenesDorf> findByXAndY(int x, int y);

	List<EigenesDorf> findAll();

}