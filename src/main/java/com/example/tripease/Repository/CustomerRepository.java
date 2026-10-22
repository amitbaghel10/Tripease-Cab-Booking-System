package com.example.tripease.Repository;

import com.example.tripease.Enum.Gender;
import com.example.tripease.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer>  findByGender(Gender gender);


    List<Customer> findByGenderAndAge(Gender gender, int age);

    Optional<Customer> findByEmailId(String emailId);

    /*
    @Query("select c from Customer c where c.gender = :gender and c.age > :age")// HQL Hibernate Query Language
    List<Customer> findByGenderAndAgeGreater(@Param("gender") Gender gender,
                                             @Param("age") int age);
    */



    //SQL Method
    /*
    the native SQL query isn't operating on the Java Gender enum itself;
    it operates on the value stored in the gender database column.
    Hibernate's entity mapping is what connects the Java enum to that database representation.

    JPQL/HQL works with your Java entity model and understands the Java Gender enum.
    Native SQL works with the actual database table and column values,
     so you generally pass whatever representation of the enum is actually stored in the database.
     */
    @Query(value = "select * from customer where gender = :gender and age > :age",
            nativeQuery = true)                                    // SQL Structured Query Language
    List<Customer> findByGenderAndAgeGreater(@Param("gender") String gender,
                                             @Param("age") int age);


}
