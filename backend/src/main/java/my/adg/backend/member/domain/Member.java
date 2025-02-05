package my.adg.backend.member.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.adg.backend.auth.domain.HistoryLogin;
import my.adg.backend.global.BaseEntity;
import my.adg.backend.member.domain.enums.AccountStatus;
import my.adg.backend.member.domain.enums.RoleType;
import my.adg.backend.member.dto.request.ProfileUpdateRequest;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	private String email;

	private String name;

	private String phoneNumber;

	private LocalDate birthday;

	private String password;

	@Embedded
	private EmbeddedAddress address;

	@Builder.Default
	private LocalDateTime passwordUpdatedDate = LocalDateTime.now();

	@Enumerated(EnumType.STRING)
	@Builder.Default
	private AccountStatus accountStatus = AccountStatus.INACTIVE;

	@Enumerated(EnumType.STRING)
	@Builder.Default
	private RoleType role = RoleType.ROLE_USER;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grade_id")
	private Grade grade;

	@OneToMany(mappedBy = "member")
	private List<HistoryLogin> historyLogins = new ArrayList<>();

	// password 업데이트
	public void updatePassword(String encNewPassword) {
		this.password = encNewPassword;
		this.passwordUpdatedDate = LocalDateTime.now();
	}

	public void updateProfile(ProfileUpdateRequest request) {
		this.address = new EmbeddedAddress(request.zipcode(), request.address1(), request.address2());
	}
}
