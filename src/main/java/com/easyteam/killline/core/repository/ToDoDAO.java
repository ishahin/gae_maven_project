/**
 * 
 */
package com.easyteam.killline.core.repository;

import org.springframework.stereotype.Service;

import com.easyteam.killline.common.repository.GenericDAO;
import com.easyteam.killline.core.model.ToDo;


@Service("ToDoDAO")
public class ToDoDAO extends GenericDAO<ToDo> {

	public ToDoDAO() {
		super.setEntityClass(ToDo.class);
	}
		
}
