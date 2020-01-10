package com.example.dubboiot;

import com.example.dubboiot.kafka.provider.Provider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class DubboProducerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(DubboProducerApplication.class, args);

		// 这里通过容器获取，正常使用情况下，可以直接使用 Autowired 注入
		Provider bean = run.getBean(Provider.class);
		while (true){
			//调用消息发送类中的消息发送方法
			bean.send();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
