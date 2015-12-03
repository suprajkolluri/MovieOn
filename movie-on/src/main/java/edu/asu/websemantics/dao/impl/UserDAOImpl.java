package edu.asu.websemantics.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import edu.asu.websemantics.dao.UserDAO;
import edu.asu.websemantics.model.User;

@Repository
@PropertySource("classpath:application-${env}.properties")
public class UserDAOImpl implements UserDAO {

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	Environment env;


	@Override
	public boolean insertUserRegisterationDetails(User user) {
		user.setEnabled(true);
		user.setRole("ROLE_USER");
		User availableUser = findUserByName(user.getUsername());
		if (availableUser != null) {
			return false;
		}
		mongoTemplate.insert(user, env.getProperty("collectionName"));
		return true;
	}

	@Override
	public User findUserByName(String userName) {
		Query query = new Query(Criteria.where("username").is(userName));
		return mongoTemplate.findOne(query, User.class, env.getProperty("collectionName"));
	}

}
