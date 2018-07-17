package TB2.NewStructure.common.hibernate.service;

import java.util.List;

import TB2.NewStructure.common.hibernate.model.Barbarendorf;

public interface BarbarendorfService {

	void saveDorf(Barbarendorf dorf);

	List<Barbarendorf> findByName(String name);

	void updateDorf(Barbarendorf dorf);

	Barbarendorf findById(int id);

	List<Barbarendorf> findAll();

	Barbarendorf findByXandY(int x, int y);

}
