package me.nothing.login_.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import me.nothing.login_.model.Staff;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface StaffRepository extends JpaRepository<Staff, Long> {
	@Query("SELECT s FROM Staff s WHERE s.username = :username")
	public Staff findByUsername(@Param("username") String uername);


	@Query("UPDATE Staff SET failedAttempt = :failedAttempt WHERE username = :username ")
	@Modifying
	public void updateFailedAttempt(@Param("failedAttempt")int failedAttempt,@Param("username") String username);
	
}
