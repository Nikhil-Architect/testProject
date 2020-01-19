package com.raghav.springmicro;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

import org.springframework.stereotype.Component;


@Component
public class UserDaoImpl {
	
	private static List<User>  users = new ArrayList<User>();
	
	static{
		users.add(new User("Nikhil",1,new Date()));
		users.add(new User("Raghav",2,new Date()));
		users.add(new User("Kumar",3,new Date()));
		
	}
	
	 public User getUser(int userId){
		 try{
		 Optional<User> findFirst = users.stream().filter(user -> user.getId()==userId).findFirst();
		 User orElse = findFirst.orElse(null);
		 return orElse;
		 }
		 catch(Exception e){
			 e.printStackTrace();
			 return null;
		 }
	 }
	 
	 public boolean deleteUser(Integer userID){
		 Iterator<User> iterator = users.iterator();
		 while(iterator.hasNext()){
			 User user = iterator.next();
			 if(user.getId() == userID){
				 iterator.remove();
				 return Boolean.TRUE;
			 }
		 }
		 return Boolean.FALSE;
	 }
	 
	 public List<User>  getAllUsers(){
		 return users;
	 }
	 
	 public void addUser(User user){
		 users.add(user);
	 }
	 
	 public static void main(String[] args) {
		 UserDaoImpl userDaoImpl = new UserDaoImpl();
		 User user = userDaoImpl.getUser(1);
		 System.out.println(user.getId());
	}
	

}
