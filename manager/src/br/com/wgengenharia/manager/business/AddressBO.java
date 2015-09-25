package br.com.wgengenharia.manager.business;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.AddressDAO;
import br.com.wgengenharia.manager.dao.AddressDAOImpl;
import br.com.wgengenharia.manager.model.Address;
import br.com.wgengenharia.manager.model.Branch;

public class AddressBO implements AddressDAO{

	private AddressDAO DAO;
	
	public AddressBO(EntityManager em) {
		this.DAO = new AddressDAOImpl(em); 
	}
	
	@Override
	public void insert(Address address) {
		DAO.insert(address);
	}

	@Override
	public void update(Address address) {
		DAO.update(address);
	}

	@Override
	public void delete(Address address) {
		DAO.delete(address);
	}

	@Override
	public Address findById(Integer id) {
		return findById(id);
	}

	@Override
	public List<Address> listByBranch(Branch branch) {
		return DAO.listByBranch(branch);
	}

}
