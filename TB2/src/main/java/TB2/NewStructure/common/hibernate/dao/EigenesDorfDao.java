package TB2.NewStructure.common.hibernate.dao;

import java.util.List;

import TB2.NewStructure.common.hibernate.model.Barbarendorf;
import TB2.NewStructure.common.hibernate.model.Dorf;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;
import TB2.NewStructure.common.hibernate.model.Provinz;

public interface EigenesDorfDao {

	void saveDorf(EigenesDorf dorf);

	List<EigenesDorf> findByName(String name);

	EigenesDorf findById(int id);

	void updateDorf(EigenesDorf dorf);

	List<EigenesDorf> findAll();

	EigenesDorf findByXandY(int x, int y);

}