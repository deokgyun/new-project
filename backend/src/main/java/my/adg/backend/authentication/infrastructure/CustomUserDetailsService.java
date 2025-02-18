package my.adg.backend.authentication.infrastructure;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.adg.backend.global.exception.BalanceTalkException;
import my.adg.backend.global.exception.ErrorCode;
import my.adg.backend.member.repository.MemberRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername {}", username);

		return memberRepository.findByEmail(username)
			.map(CustomUserDetails::new)
			.orElseThrow(() -> new BalanceTalkException(ErrorCode.NOT_FOUND_MEMBER));
	}

	public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
		log.info("loadUserByUsername {}", id);

		return memberRepository.findById(id)
			.map(CustomUserDetails::new)
			.orElseThrow(() -> new BalanceTalkException(ErrorCode.NOT_FOUND_MEMBER));
	}
}
