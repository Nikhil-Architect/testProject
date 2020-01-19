package com.raghav.springmicro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raghav.springmicro.Post;



@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

}
