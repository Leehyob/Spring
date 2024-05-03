package org.zerock.domain;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class MemberVO implements UserDetails{

	private String userid;
	private String userpw;
	private String name;
	private boolean enabled;
	private Date regdate;
	private Date updateDate;
	private List<AuthVO> authList;
	
	@Override			// GrantedAutority이거나 자손들만 return 가능
	public Collection<? extends GrantedAuthority> getAuthorities() {
			//authList의 타입과 일치하지 않으므로 타입을 변환시켜줌											//Collectors(Collection) -> list의 최상위 조상
		return authList.stream().map(auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList());
			// 								GrantedAuthority의 자손인 얘를 사용해서 auth의 값을 얻어와서 타입을 변환하길 반복해 리스트로 만듦
	}
	
	@Override
	public String getUsername() {
		return userid;
	}
	
	@Override
	public String getPassword() {
		return userpw;
	}
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
}
