package com.xuecheng.userservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xuecheng.entities.Customer;
import com.xuecheng.entities.Dept;
import com.xuecheng.entities.Person;
import com.xuecheng.userservice.cloudstream.StreamClient;
import com.xuecheng.userservice.dao.CustomerRepository;
import com.xuecheng.userservice.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;


@RestController
public class DeptController
{
	@Autowired
	private DeptService service;
	@Autowired
	private DiscoveryClient client;

	@Autowired
	private StreamClient streamClient;


	@Autowired
	private EntityManager entityManager;

	@Autowired
	private CustomerRepository customerRepository;

	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public boolean add(@RequestBody Dept dept)
	{
		return service.add(dept);
	}

	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "processHystrix_Get")
	public Dept get(@PathVariable("id") Long id)
	{
		Dept dept = this.service.get(id);

		if (null == dept) {
			throw new RuntimeException("该ID：" + id + "没有没有对应的信息");
		}
		return dept;
	}

	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list()
	{
		return service.list();
	}


	@RequestMapping(value = "/customer/list", method = RequestMethod.GET)
	public List<Customer> getList(){
		Customer customer = new Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("cc@163.com");
		customer.setLastName("CC");
		Customer customer2 = entityManager.merge(customer);
		return customerRepository.findAll();
	}

	
//	@Autowired
//	private DiscoveryClient client;
	@RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
	public Object discovery()
	{
		List<String> list = client.getServices();
		System.out.println("**********" + list);

		List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
		for (ServiceInstance element : srvList) {
			System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
					+ element.getUri());
		}
		return this.client;
	}


	@RequestMapping(value = "/dept/send", method = RequestMethod.GET)
	public void send(Object payload)
	{
		System.out.println("payload:"+payload);
		streamClient.output().send(MessageBuilder.withPayload("cxwer").build());
	}


	public Dept processHystrix_Get(@PathVariable("id") Long id)
	{
		return new Dept().setDeptno(id).setDname("该ID：" + id + "没有没有对应的信息,null--@HystrixCommand")
				.setDb_source("no this database in MySQL");
	}


	@RequestMapping(value = "/person/list", method = RequestMethod.GET)
	public List<Person> getPerson(){
		return service.findPerson();
	}
}
