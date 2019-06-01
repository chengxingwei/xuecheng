package com.xuecheng.userservice.service.impl;

import com.xuecheng.entities.Dept;
import com.xuecheng.entities.Person;
import com.xuecheng.userservice.dao.DeptDao;
import com.xuecheng.userservice.repository.PersonRepsotory;
import com.xuecheng.userservice.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class DeptServiceImpl implements DeptService
{
	@Autowired
	private DeptDao dao;

	@Autowired
	private PersonRepsotory personRepsotory;
	
	@Override
	public boolean add(Dept dept)
	{
		return dao.addDept(dept);
	}

	@Override
	public Dept get(Long id)
	{
		return dao.findById(id);
	}

	@Override
	public List<Dept> list()
	{
		return dao.findAll();
	}

	@Override
	public List<Person> findPerson() {
		return personRepsotory.findAll();
	}

}
