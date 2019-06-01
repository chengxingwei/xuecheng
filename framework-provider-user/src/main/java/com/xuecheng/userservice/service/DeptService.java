package com.xuecheng.userservice.service;

import com.xuecheng.entities.Dept;
import com.xuecheng.entities.Person;

import java.util.List;


public interface DeptService
{
	public boolean add(Dept dept);

	public Dept get(Long id);

	public List<Dept> list();


	public List<Person> findPerson();
}
