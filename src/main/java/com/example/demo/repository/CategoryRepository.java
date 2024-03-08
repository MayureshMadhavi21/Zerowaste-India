package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Category;



/*The Spring @Repository annotation is a specialization of the @Component 
annotation which indicates that an annotated class is a “Repository”, which 
can be used as a mechanism for encapsulating storage and 
search behavior which a collection of objects*/ 


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	Category findByName( String name);
}
