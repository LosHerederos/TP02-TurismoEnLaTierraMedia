package dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {
	
	public List<T> findAll() throws SQLException;
	public T findById(int id) throws SQLException;
	public int countAll();
	public int insert(T t);
	public int update(T t);
	public int delete(T t);
}
