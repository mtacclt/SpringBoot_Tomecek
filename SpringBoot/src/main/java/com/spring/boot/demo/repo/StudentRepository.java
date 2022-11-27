package com.spring.boot.demo.repo;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import com.spring.boot.demo.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {


}
