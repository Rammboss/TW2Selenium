package TB2.NewStructure.common.hibernate.service;

import java.util.List;

import TB2.NewStructure.common.hibernate.model.Provinz;

public interface ProvinzService {
	
	void saveProvinz(Provinz dorf);

	List<Provinz> findByName(String name);

	void updateProvinz(Provinz dorf);
	
	Provinz findById(int id);
  	
	List<Provinz> findAll();
}
