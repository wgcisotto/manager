package br.com.wgengenharia.manager.coffe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.wgengenharia.manager.coffe.db.DBManager;
import br.com.wgengenharia.manager.coffe.model.Product;

public class ProductDAOImpl implements ProductDAO{
	
	@Override
	public void insert(Product product) {
		Connection con = null;
		try {
			con = DBManager.getInstace().getConnection();
//	   Statement statement = DAOHelper.getInstance().getStatementConfig(Constants.ST_CRIAR_DIMENSAO);
			PreparedStatement ps = null;

			ps = con.prepareStatement("");
//	      ps.setInt(1, dim.getId());
//	      ps.setString(2, dim.getNome());
//	      ps.setInt(3, dim.getGrupo().getId());
      ps.setString(4, "");    // ATRIBUTO_VISAO
//	      ps.setString(5, dim.getColuna());

      ps.executeUpdate();
    }
    catch(SQLException ex) {
//	      throw new DAOException(ex.getMessage(), ex);
    	System.out.println(ex.getMessage());
    }
    finally {
//	     DAOHelper.closeStatement(ps);
      this.closeConnnection(con);
    }
	}

	@Override
	public void update(Product product) {
		
	}

	@Override
	public void delete(Product product) {
		
	}

	@Override
	public Product findById(Integer id) {
		return null;
	}

}
