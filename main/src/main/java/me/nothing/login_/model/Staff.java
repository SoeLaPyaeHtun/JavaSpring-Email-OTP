package me.nothing.login_.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

// import org.apache.catalina.Manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "staffs")
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "staff_id", nullable = false)
	private long id;

	@Column(nullable = false, length = 20)
	private String username;

	@Column(nullable = false, length = 64)
	private String password;

	@Column(nullable = false, unique = true, length = 45)
	private String email;

	@Column(name = "job_title", nullable = false)
	private String title;

	private String firstname;

	private String lastname;

	private String status;

	@Column(name = "one_time_password")
	private String otp;

	@Column(name = "otp_requested_time")
	private Date otpReqTime;

	// @Column(name="anual_leave_entitlemnt")
	// private int anuLeave;

	// @Column(name="medi_requested_entitlement")
	// private int mediLeave;

	// @Column(name="comp_requested_entitlement")
	// private int compLeave;

	@Column(name = "failed_attempt", nullable = false)
	private int failedAttempt;

	// @ManyToMany(targetEntity = LeaveType.class,cascade = {CascadeType.ALL,
	// CascadeType.PERSIST}, fetch=FetchType.EAGER)
	// @JoinTable(name = "StaffAndLeaveType", joinColumns = {
	// @JoinColumn(name="staff_id", referencedColumnName = "staff_id")},
	// inverseJoinColumns = { @JoinColumn(name="LTid", referencedColumnName = "id")}
	// )
	// private List<LeaveType> LTSet;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "staff_role", joinColumns = @JoinColumn(name = "staff_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	private static final long otpDuration = 3 * 6 * 1000; // valid in 3 min

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

	public boolean hasRole(String roleName) {
		Iterator<Role> iterator = roles.iterator();

		while (iterator.hasNext()) {
			Role role = iterator.next();
			if (role.getName().equals(roleName)) {
				return true;
			}
		}
		return false;
	}

}
