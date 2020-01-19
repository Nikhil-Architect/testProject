package com.raghav.springmicro;

import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
public class SpringRestController {
	
	
	@Autowired
	UserDaoImpl userDaoImpl;
	
	@Autowired
	MessageSource messageSouce;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	
	
	
	
	
	@RequestMapping(method = RequestMethod.GET,path = "/exeJar")
	public String exeJar(){
		 Process proc = null;
		 byte b[] =null;
			try {
				proc = Runtime.getRuntime().exec("java -jar C://Users//nikhil.g.kumar//Desktop//RunnaJar-0.0.1-SNAPSHOT-jar-with-dependencies.jar");
				proc.waitFor();
			    InputStream in = proc.getInputStream();
			    InputStream err = proc.getErrorStream();
				b = new byte[in.available()];
				in.read(b,0,b.length);
			    System.out.println(new String(b));
			    byte c[]=new byte[err.available()];
			    err.read(c,0,c.length);
			    System.out.println(new String(c));
		}
			catch(Exception e){
				e.printStackTrace();
			}
			return new String(b);
			
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST,path = "/jpa/addUser")
	public Integer addJpaUser(@Valid @RequestBody User user){
		User save = userRepository.save(user);
		return save.getId();
	}

	@RequestMapping(method = RequestMethod.GET,path = "/exeSapper")
	public String exeSapper(){
		 Process proc = null;
		 byte b[] =null;
			try {
				proc = Runtime.getRuntime().exec("java -jar C://Users//nikhil.g.kumar//Desktop//SAPPER-1.0.6.jar");
				proc.waitFor();
			    InputStream in = proc.getInputStream();
			    InputStream err = proc.getErrorStream();
				b = new byte[in.available()];
				in.read(b,0,b.length);
			    System.out.println(new String(b));
			    byte c[]=new byte[err.available()];
			    err.read(c,0,c.length);
			    System.out.println(new String(c));
		}
			catch(Exception e){
				e.printStackTrace();
			}
			return new String(b);
			
	}
	
	@RequestMapping(method = RequestMethod.GET,path = "/getName")
	public FirstBean getName(){
		return new FirstBean("Your name is nikhil raghav");
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET,path = "/jpa/allUser")
	public List<User> getAllJPAUsers(){
		return userRepository.findAll();
		
	}
	
	
	@RequestMapping(method = RequestMethod.GET,path = "/getFilterBean")
	public FilterBean  getFIlterBean(){
		return new FilterBean("Raghav","Rajput","male");
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET,path = "/getUser/{userId}")
	public User getSpecificUsers(@PathVariable String userId){
		 User user = userDaoImpl.getUser(Integer.parseInt(userId));
		 if(user == null){
			 throw new UserNotFoundException("User in not available for given id");
		 }
		 return user;
	}
	
	
	@RequestMapping(method = RequestMethod.GET,path = "/getposts/{userId}/user")
	public List<Post> getAllPostsOfUsers(@PathVariable int userId){
		Optional<User> findById = userRepository.findById(userId);
		 if(!findById.isPresent()){
			 throw new UserNotFoundException("User in not available for given id");
		 }
		 return findById.get().getPosts();
	}
	
	
	@RequestMapping(method = RequestMethod.POST,path = "/savePosts/{userId}/user")
	public ResponseEntity<Post> savePostsOfUsers(@PathVariable int userId,@RequestBody Post post){
		Optional<User> findById = userRepository.findById(userId);
		 if(!findById.isPresent()){
			 throw new UserNotFoundException("User in not available for given id");
		 }
		 
		 User user = findById.get();
		 post.setUser(user);
		 postRepository.save(post);
		 
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}").buildAndExpand(post.getId()).toUri();
		 return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.GET,path = "/allUser")
	public List<User> getAllUsers(){
		return userDaoImpl.getAllUsers();
	}
	@RequestMapping(method = RequestMethod.POST,path = "/addUser")
	public void getAllUsers(@Valid @RequestBody User user){
		userDaoImpl.addUser(user);
	}
	

	@RequestMapping(method = RequestMethod.DELETE,path = "/deleteUser/{userId}")
	public void  deleteSpecificUsers(@PathVariable String userId){
		  boolean value =  userDaoImpl.deleteUser(Integer.parseInt(userId));
		 if(!value){
			 throw new UserNotFoundException("User in not available for deletion for given id");
		 }
		 
	}

	
	@RequestMapping(method = RequestMethod.GET,path = "/getMessageFromInt")
	public String getInternational(@RequestHeader(name = "Accept-Language") Locale locale){
		return messageSouce.getMessage("message", null, locale);
	

}
}
