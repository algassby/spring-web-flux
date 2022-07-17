/**
 * 
 */
package org.barry.reactive.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;


import reactor.core.publisher.Mono;

/**
 * @author algas
 *
 */
@Component
public class NumHandlerImpl implements NumHandler {

	
	@Override
	public Mono<ServerResponse> num(ServerRequest serverRequest) {
		return ServerResponse.ok().body(Mono.just(100), Integer.class);
	}

}
