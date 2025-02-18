package my.adg.backend.member.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.adg.backend.authentication.resolver.LoginMember;
import my.adg.backend.global.exception.BalanceTalkException;
import my.adg.backend.global.exception.ErrorCode;
import my.adg.backend.member.domain.Member;
import my.adg.backend.member.dto.request.MemberJoinRequest;
import my.adg.backend.member.dto.request.ProfileUpdateRequest;
import my.adg.backend.member.dto.request.UpdatePasswordRequest;
import my.adg.backend.member.dto.response.MemberFindResponse;
import my.adg.backend.member.repository.GradeRepository;
import my.adg.backend.member.repository.MemberRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final GradeRepository gradeRepository;

	public Member getMemberById(Long memberId) {
		return memberRepository.findById(memberId)
			.orElseThrow(() -> new BalanceTalkException(ErrorCode.NOT_FOUND_MEMBER));
	}

	public Member getMemberByEmail(String email) {
		return memberRepository.findByEmail(email)
			.orElseThrow(() -> new BalanceTalkException(ErrorCode.NOT_FOUND_MEMBER));
	}

	// TODO : Page로 변경 예정
	public List<MemberFindResponse> getAllMembers(String type, String name) {
		return memberRepository.findAll().stream()
			.map(m -> new MemberFindResponse(m))
			.toList();
	}

	public MemberFindResponse getMember(Long id) {
		Member member = getMemberById(id);
		return new MemberFindResponse(member);
	}

	// 회원가입
	@Transactional
	public MemberFindResponse joinMember(MemberJoinRequest request) {
		
		// 이메일 중복 검사
		duplicateEmail(request.email());

		String password = request.password();
		String generatePassword = generatePassword(password);

		Member entity = request.toEntity(request, generatePassword);
		Member save = memberRepository.save(entity);

		// TODO : 인증번호 이메일 전송
		
		return new MemberFindResponse(save);
	}

	// 이메일 중복 검사
	public void duplicateEmail(String email) {
		boolean exists = memberRepository.findByEmail(email).isPresent();

		if (exists) {
			throw new BalanceTalkException(ErrorCode.ALREADY_REGISTERED_EMAIL);
		}
	}

	// 비밀번호 암호화
	private String generatePassword(String request) {
		String rawPassword = request;
		return passwordEncoder.encode(rawPassword);
	}

	@Transactional
	public void updateProfile(LoginMember loginMember, ProfileUpdateRequest request) {
		Member member = getMemberById(loginMember.id());

		member.updateProfile(request);

	}

	@Transactional
	public void updatePassword(LoginMember loginMember, UpdatePasswordRequest request) {

		Member member = getMemberById(loginMember.id());

		validatePasswordChange(
			member.getPassword(),
			request.currentPassword(),
			request.newPassword(),
			request.confirmPassword()
		);

		String encodedNewPassword = generatePassword(request.newPassword());

		member.updatePassword(encodedNewPassword);

	}

	private void validatePasswordChange(String originalPassword, String currentPassword,
		String newPassword, String confirmPassword) {

		// 현재 비밀번호가 맞는지 검사
		if (!passwordMatches(currentPassword, originalPassword)) {
			throw new BalanceTalkException(ErrorCode.CURRENT_PASSWORD_MISMATCH);
		}

		// 새 비밀번호가 현재 비밀번호와 다른지 검사
		if (passwordMatches(currentPassword, generatePassword(newPassword))) {
			throw new BalanceTalkException(ErrorCode.NEW_CURRENT_PASSWORD_MISMATCH);
		}

		// 새 비밀번호와 확인 비밀번호가 일치하는지 검사
		if (!passwordMatches(confirmPassword, generatePassword(newPassword))) {
			throw new BalanceTalkException(ErrorCode.NEW_PASSWORD_MISMATCH);
		}
	}

	private boolean passwordMatches(String rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
}
