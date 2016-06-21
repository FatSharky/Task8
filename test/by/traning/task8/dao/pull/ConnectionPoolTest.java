package by.traning.task8.dao.pull;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

import by.traning.task8.dao.pull.ConnectionPool;
import by.traning.task8.dao.pull.exception.ConnectionPoolException;

public class ConnectionPoolTest {
	@Test(expected = ConnectionPoolException.class)
	public void getFreeConnectionTest()throws IllegalAccessException, ClassNotFoundException, InstantiationException, SQLException, ConnectionPoolException {
	ConnectionPool connectionPool = ConnectionPool.getInstance();
	Connection conn = connectionPool.takeConnection();
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hr_system","root","214248");
	connectionPool.closeConnection(conn);
	}

}
