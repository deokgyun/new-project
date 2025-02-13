package my.adg.backend.auth.infrastructure;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.adg.backend.member.repository.MemberRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return memberRepository.findByEmail(username)
			.map(CustomUserDetails::new)
			.orElseThrow(() -> new UsernameNotFoundException(username));
	}

	public UserDetails loadUserById(Long id) throws UsernameNotFoundException {

		return memberRepository.findById(id)
			.map(CustomUserDetails::new)
			.orElseThrow(() -> new UsernameNotFoundException(String.valueOf(id)));
	}
}
