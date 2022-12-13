package me.nothing.login_.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import me.nothing.login_.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
	@Query("SELECT u FROM Staff u WHERE u.username = :username")
	public Staff findByUsername(@Param("username") String uername);
	
}
