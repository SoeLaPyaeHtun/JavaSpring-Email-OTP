package me.nothing.login_.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.OneToMany;
// import javax.persistence.Table;

// import org.apache.catalina.Manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Staff{
    @Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name="staff_id",nullable = false)
	private long id;

	@Column(nullable = false, length = 20)
	private String username;

	@Column(nullable = false, length = 64)
	private String password;

	@Column(nullable = false, unique = true, length = 45)
	private String email;

	@Column(name="role_id", nullable = false)
	private int roleId;

	@Column(name="job_title", nullable = false)
	private String title;

	private String firstname;

	private String lastname;

	private String status;


	@Column(name="one_time_password")
	private String otp;

	@Column(name="otp_requested_time")
	private Date otpReqTime;

	@Column(name="anual_leave_entitlemnt")
	private String anuLeave;

	//@Column(name="otp_requested_time", nullable = false)
    //private int mediLeave;

	//@Column(name="otp_requested_time", nullable = false)
    //private int compLeave;


	
	// @OneToMany(mappedBy="staff")
	// private List<Leave> leave;

	// @ManyToOne
	// @JoinColumn(name="manager_id")
	// private Manager manager;

	private static final long otpDuration = 3 * 6 * 1000;  //valid in 3 min 

	public boolean isOTPRequired() {
        if (this.getOtp() == null) {
            return false;
        }
         
        long currentTimeInMillis = System.currentTimeMillis();
        long otpRequestedTimeInMillis = this.otpReqTime.getTime();
         
        if (otpRequestedTimeInMillis + otpDuration < currentTimeInMillis) {
            // OTP expires
            return false;
        }
         
        return true;
    }
}


// @Data
// @Entity
// @Table(name = "users")
// public class User {
	
// 	@Id
// 	@GeneratedValue(strategy = GenerationType.IDENTITY)
// 	private Long id;
	
// 	@Column(nullable = false, unique = true, length = 45)
// 	private String email;
	
// 	@Column(nullable = false, length = 64)
// 	private String password;
	
// 	@Column(nullable = false, length = 20)
// 	private String username;

// 	@Column(name = "one_time_password")
// 	private String otp;

// 	@Column(name = "requested_time")
// 	private Date otptime;