package dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class AtraccionDAOTest {

	@Test
	public void test() throws SQLException {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		
		System.out.println(atraccionDAO.findAll());
		System.out.println(atraccionDAO.findById(2));
		System.out.println(atraccionDAO.findByNombre("Minas Tirith"));
		System.out.println(atraccionDAO.countAll());
	}

}
