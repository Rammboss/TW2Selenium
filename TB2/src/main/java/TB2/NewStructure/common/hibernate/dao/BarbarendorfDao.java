package TB2.NewStructure.common.hibernate.dao;

import java.util.List;

import TB2.NewStructure.common.hibernate.model.Barbarendorf;

public interface BarbarendorfDao {

	void saveDorf(Barbarendorf dorf);

	List<Barbarendorf> findByName(String name);
	
	Barbarendorf findById(int id);

	void updateDorf(Barbarendorf dorf);
	
	List<Barbarendorf> findAll();
	
	Barbarendorf findByXandY(int x, int y);
	
	void deleteDorf(Barbarendorf dorf);

}
