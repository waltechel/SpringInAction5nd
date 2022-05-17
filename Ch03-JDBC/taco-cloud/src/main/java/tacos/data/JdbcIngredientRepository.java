package tacos.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;

import tacos.Ingredient;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {
	
	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcIngredientRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public Iterable<Ingredient> findAll() {
		return jdbc.query("select id, name, type from Ingredient",
				this::mapRowToIngredient);
	}
	
	@Override
	public Ingredient findById(String id) {
		return jdbc.queryForObject(
				"select id, name, type from Ingredient where id=?",
				this::mapRowToIngredient, id);
	}
	
	/**
	 * 그러나 종전처럼 RowMapper 인터페이스의 mapRow() 메서드를 구현하는 방법을
	 * 사용할 수도 있다. 
	 * @param id
	 * @return
	 */
	public Ingredient findByIdBymapRow(String id) {
		return jdbc.queryForObject(
				"select id, name, type from Ingredient where id=?",
				new RowMapper<Ingredient>() {
					@Override
					public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new Ingredient(rs.getString("id"), rs.getString("name"), 
								Ingredient.Type.valueOf(rs.getString("type")));
					}					
				}, id);
	}
	
	/**
	 * mapper ResultSet을 Ingredient로 mapping하는 mapper method
	 * @param rs 리턴받은 ResultSet 객체
	 * @param rowNum 몇 개를 리턴받았는지
	 * @return
	 * @throws SQLException
	 */
	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum)
			throws SQLException {
		return new Ingredient(
				rs.getString("id"),
				rs.getString("name"),
				Ingredient.Type.valueOf(rs.getString("type")));
	}
	
	@Override
	public Ingredient save(Ingredient ingredient) {
		jdbc.update(
				"insert into Ingredient (id, name, type) values (?, ?, ?)",
				ingredient.getId(),
				ingredient.getName(),
				ingredient.getType().toString());
		return ingredient;
	}
}
