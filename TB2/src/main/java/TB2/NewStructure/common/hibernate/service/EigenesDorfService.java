package TB2.NewStructure.common.hibernate.service;

import java.util.List;

import TB2.NewStructure.common.hibernate.model.Dorf;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;

public interface EigenesDorfService {

	void saveDorf(EigenesDorf dorf);

	EigenesDorf findBySpielerAndName(String name, String spieler);

	List<EigenesDorf> findBySpieler(String spieler);

	void updateDorf(EigenesDorf dorf);

	Dorf findById(int id);

	List<EigenesDorf> findAll();

	EigenesDorf findByXandY(int x, int y);

}
