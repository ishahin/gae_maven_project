package com.easyteam.killline.common.repository;

import static com.easyteam.killline.common.service.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.easyteam.killline.common.model.GenericEntity;
import com.googlecode.objectify.Key;

@Repository
@Service("GenericDAO")
public class GenericDAO<T extends GenericEntity> {
	/**
	 * number of pages loaded for paging
	 */
	static final Integer NUMBER_OF_PAGES = 10;

	/**
	 * Entity class
	 */
	protected Class<T> entityClass;

	/**
	 * Set entity class
	 * 
	 * @param entityClass
	 */
	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * @param clazz
	 * @return
	 */
	public Key<T> save(T clazz) {
		return ofy().save().entity(clazz).now();
	}

	/**
	 * @param clazz
	 * @return
	 */
	public boolean remove(T clazz) {
		// TODO: Testar... nao sei se funciona...
		clazz.setDeleted(true);
		save(clazz);
		return true;
	}

	/**
	 * @param id
	 * @return
	 */
	public boolean removeId(Long id) {
		T findById = findById(id);
		findById.setDeleted(true);
		save(findById);
		return true;
	}

	/**
	 * @param id
	 * @return
	 */
	public boolean purge(Long id) {
		Key<T> key = Key.create(this.entityClass, id);
		ofy().delete().key(key).now();
		return true;
	}

	/**
	 * @param clazz
	 * @return
	 */
	public Key<T> update(T clazz) {
		return save(clazz);
	}

	/**
	 * @param id
	 * @return
	 */
	public T findById(Long id) {
		Key<T> key = Key.create(entityClass, id);
		return ofy().load().key(key).get();
	}

	/**
	 * @return
	 */
	public List<T> listAll() {
		return ofy().load().type(this.entityClass).filter("deleted", false)
				.list();
	}

	/**
	 * Return a list of Entity found by Id's
	 * 
	 * @param ids
	 * @return
	 */
	public List<T> listByIds(List<Long> ids) {
		return new ArrayList<T>(ofy().load().type(this.entityClass).ids(ids).values());
	}

}
