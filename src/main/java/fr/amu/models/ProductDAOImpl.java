package fr.amu.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional  //Chaque méthode est une transaction (ouvre les transactions)
@Repository
public class ProductDAOImpl implements ProductDAO {

	//CREATE TABLE IF NOT EXISTS products(id INTEGER IDENTITY PRIMARY KEY, category VARCHAR(255), productTitle VARCHAR(255), 
	//img VARCHAR(255), description VARCHAR(255), date VARCHAR(255) );
	
	@Autowired
	JdbcTemplate jt;
	
	private String INSERT_PRODUCT="INSERT INTO products(category,productTitle,description) values(?,?,?)";
	private String UPDATE_PRODUCT="UPDATE products SET category=?, productTitle=?, description=? WHERE id=?";
	private String DELETE_PRODUCT="DELETE FROM products WHERE id=?";
	private String SELECTBYID_PRODUCT="SELECT * FROM products WHERE id=?";
	private String SELECTBYCATEGORY_PRODUCT="SELECT * FROM products WHERE category=?";
	private String SELECTALL_PRODUCT="SELECT * FROM products";

	
	
	
	@Override
	public void add(Product product) {
		jt.update(INSERT_PRODUCT, product.getCategory(), product.getTitle(), product.getDescription());
	}

	@Override
	public void update(Product product) {
		jt.update(UPDATE_PRODUCT, product.getCategory(), product.getTitle(), product.getDescription());
	}

	@Override
	public void delete(Product product) {
		jt.update(DELETE_PRODUCT, product.getId());
	}

	@Override
	public List<Product> findAll() {
		PRODUCTRowMapper ProductRM = new PRODUCTRowMapper();
		return jt.query(SELECTALL_PRODUCT, ProductRM);
	}

	@Override
	public Product findById(Integer id) {
		PRODUCTRowMapper ProductRM = new PRODUCTRowMapper();
		return jt.queryForObject(SELECTBYID_PRODUCT, ProductRM, id);
	}

	@Override
	public List<Product> findByCategory(String category) {
		PRODUCTRowMapper ProductRM = new PRODUCTRowMapper();
		return jt.query(SELECTBYCATEGORY_PRODUCT, ProductRM, category);
	}
	
	
}

class PRODUCTRowMapper implements RowMapper<Product>
{
	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product product = new Product();
		product.setId(rs.getInt("id"));
		product.setCategory(rs.getString("category"));
		product.setTitle(rs.getString("productTitle"));
		product.setDescription(rs.getString("description"));
		return product;
	}
}
