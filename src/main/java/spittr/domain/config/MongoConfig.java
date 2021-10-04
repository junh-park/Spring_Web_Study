package spittr.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages = "orders.db")
public class MongoConfig {
	@Bean
	public MongoClientFactoryBean mongo() {
		MongoClientFactoryBean mongo = new MongoClientFactoryBean();
		mongo.setHost("localhost");
		return mongo;
	}
	
	@Bean
	public MongoOperations mongoTemplate() {
		return new MongoTemplate((MongoClient) mongo(), "OrdersDB");
	}
}
