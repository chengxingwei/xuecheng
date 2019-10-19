package com.xuecheng.elasticjob.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DemoSimpleJob implements SimpleJob {

	private Logger log = LoggerFactory.getLogger(DemoSimpleJob.class);


	@Override
	public void execute(ShardingContext shardingContext) {
		if (shardingContext.getShardingItem() == 0){
			String jobParameter = shardingContext.getJobParameter();
			log.info("test--------------------{}", jobParameter);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(format.format(new Date()));
			System.out.println("执行定时任务0");
		}else if(shardingContext.getShardingItem() == 1){
			String jobParameter = shardingContext.getJobParameter();
			log.info("test--------------------{}", jobParameter);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(format.format(new Date()));
			System.out.println("执行定时任务1");
		}

	}

}
