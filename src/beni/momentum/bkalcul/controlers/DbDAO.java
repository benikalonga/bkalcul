package beni.momentum.bkalcul.controlers;

import java.util.ArrayList;

public interface DbDAO<T> {

	// Interface to generate CRUD functions

	boolean create(T t);

	T read(int id);
	T read(String id);

	ArrayList<T> readAll();

	boolean delete(int id);
	boolean delete(String username);

	boolean updateRecord(T t);

}
