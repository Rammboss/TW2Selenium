package TB2.NewStructure.common.hibernate.dao;

import java.util.List;

import TB2.NewStructure.common.hibernate.model.Barbarendorf;
import TB2.NewStructure.common.hibernate.model.Dorf;


public interface DorfDao {

	void saveDorf(Dorf dorf);

	List<Dorf> findByName(String name);
	
	Dorf findById(int id);

	void updateDorf(Dorf dorf);
	
	List<Dorf> findAll();


}
