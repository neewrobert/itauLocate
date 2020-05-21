package com.itau.itauLocate.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itau.itauLocate.model.AgencyModel;

@Service("agencyDao")
@Transactional
@Repository
public class AgencyDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4368373078661376306L;

	@PersistenceContext
	private EntityManager em;

	private GenericDao<AgencyModel> dao;

	@PostConstruct
	private void init() {

		this.dao = new GenericDao<>(em, AgencyModel.class);

	}

	public void insert(AgencyModel t) {
		dao.insert(t);
	}

	public void remove(AgencyModel t) {
		dao.remove(t);
	}

	public void update(AgencyModel t) {
		dao.update(t);
	}

	public List<AgencyModel> getAll() {
		return dao.getAll();
	}

	public AgencyModel findById(long id) {
		return dao.findById(id);
	}


}
