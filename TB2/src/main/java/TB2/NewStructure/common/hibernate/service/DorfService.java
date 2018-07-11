package TB2.NewStructure.common.hibernate.service;

import java.util.List;

import TB2.NewStructure.common.hibernate.model.Dorf;

public interface DorfService {

	
	void saveDorf(Dorf dorf);

	List<Dorf> findByName(String name);

	void updateDorf(Dorf dorf);
	
	Dorf findById(int id);

	List<Dorf> findAll();

}
