package my.adg.backend.authentication.infrastructure;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.RequiredArgsConstructor;
import my.adg.backend.member.domain.Member;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

	private final Member member;

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		authorities.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return String.valueOf(member.getRole());
			}
		});

		return authorities;
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getEmail();
	}

	public Long getId() {
		return member.getId();
	}

}
