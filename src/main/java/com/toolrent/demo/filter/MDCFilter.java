package com.toolrent.demo.filter;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MDCFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			String correlationId = request.getHeader("correlation-id");
			if (!StringUtils.hasText(correlationId)) {
				correlationId = UUID.randomUUID().toString();
			}
			MDC.put("correlationId", correlationId);
			filterChain.doFilter(request, response);
		} finally {
			MDC.clear();
		}
	}

}