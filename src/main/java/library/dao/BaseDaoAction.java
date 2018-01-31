package library.dao;

import library.bean.Entity;

public interface BaseDaoAction<T extends Entity> {
	
	// C R U D 
		void create(T t);
		T read();
		void update(T t);
		void delete(T t);

}
