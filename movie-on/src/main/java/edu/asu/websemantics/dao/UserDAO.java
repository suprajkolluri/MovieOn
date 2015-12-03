package edu.asu.websemantics.dao;

import edu.asu.websemantics.model.User;

public interface UserDAO {

	public boolean insertUserRegisterationDetails(User user);

	public User findUserByName(String userName);
}
