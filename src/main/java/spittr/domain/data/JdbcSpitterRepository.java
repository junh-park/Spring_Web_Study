package spittr.domain.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import spittr.domain.Spitter;

@Repository
public class JdbcSpitterRepository implements SpitterRepository {
	private static final String INSERT_SPITTER = "insert into spitter values(?,?,?,?,?)";
	private JdbcOperations jdbcOperations;
	
	@Autowired
	public JdbcSpitterRepository(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}
	
	@Override
	public Spitter save(Spitter unsaved) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Spitter findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
