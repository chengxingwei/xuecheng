package com.xuecheng.activitiservice;


import com.xuecheng.activitiservice.service.TestService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivitiTest {

    @Autowired
    TestService testService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Test//流程部署
    public void  test01(){
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("processes/test.bpmn").deploy();
        System.out.println(deployment.getId());
    }

    @Test//获取流程定义列表
    public void test02(){
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition definition : list
             ) {
            System.out.println(definition.getId());
        }
        System.out.println("流程定义列表："+list.size());
    }

    @Test//启动一个流程实例
    public void test03(){
        ProcessInstance instance = runtimeService.startProcessInstanceById("myProcess_1:2:15004");
        System.out.println("流程实例ID:"+instance.getId());
    }


    @Test//获取待办任务
    public void test04(){
        List<Task> list = taskService.createTaskQuery().list();
        for (Task task:list
             ) {
            taskService.complete(task.getId());
        }
        System.out.println("任务列表:"+list.size());
    }

}
