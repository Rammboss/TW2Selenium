package TB2.NewStructure.common.hibernate.dao;

import java.util.List;

import TB2.NewStructure.common.hibernate.model.Provinz;

public interface ProvinzDao {
	void saveProvinz(Provinz dorf);

	List<Provinz> findByName(String name);

	Provinz findById(int id);

	void updateProvinz(Provinz dorf);

	List<Provinz> findAll();
}
