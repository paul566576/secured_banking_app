package com.banking.secured_banking_app.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>>
{
	@Override
	public Collection<GrantedAuthority> convert(final Jwt source)
	{
		final List<String> roles = (List<String>) source.getClaims().get("roles");

		if (roles == null || roles.isEmpty())
		{
			return new ArrayList<>();
		}

		return roles.stream()
				.map(roleName -> "ROLE_" + roleName)
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}
}
