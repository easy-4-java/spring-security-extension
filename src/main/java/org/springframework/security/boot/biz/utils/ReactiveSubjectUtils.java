package org.springframework.security.boot.biz.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import reactor.core.publisher.Mono;

/**
 * Utility class for accessing the current security context in reactive (WebFlux) applications.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 */
public class ReactiveSubjectUtils {

	public static Mono<SecurityContext> getSecurityContext(){
		return ReactiveSecurityContextHolder.getContext();
	}

	public static Mono<Authentication> getAuthentication(){
		return getSecurityContext()
				.switchIfEmpty(Mono.error(new IllegalStateException("ReactiveSecurityContext is empty")))
                .map(SecurityContext::getAuthentication);
	}

	public static <T> Mono<T> getPrincipal(Authentication authentication, Class<T> clazz){
		ReactiveSecurityContextHolder.withAuthentication(authentication);
		return getPrincipal(clazz);
	}

	public static <T> Mono<T> getPrincipal(Class<T> clazz){
		return getAuthentication()
				.switchIfEmpty(Mono.error(new IllegalStateException("Authentication is empty")))
				.map(Authentication::getPrincipal).cast(clazz);
	}

	public static Mono<Object> getPrincipal(){
		return getAuthentication()
				.map(Authentication::getPrincipal);
	}

	public static boolean isAuthenticated(){
		return getAuthentication()
				.map(Authentication::isAuthenticated)
				.block();
	}

		public static boolean isAssignableFrom(Class<?> target, Class<?> ... classes) {
		if(target != null && classes != null) {
			for (Class<?> clazz : classes) {
				/*
				 *	假设有两个类Class1和Class2。Class1.isAssignableFrom(Class2)表示:
    			 *	1、类Class1和Class2是否相同。
    			 *	2、Class1是否是Class2的父类或接口
				 */
				// clazz是否和target类型相同或者，clazz是否是target的父类或接口
				if(clazz != null && clazz.isAssignableFrom(target)) {
					return true;
				};
			}
		}
		return false;
	}

}
