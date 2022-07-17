/**
 * 
 */
package org.barry.reactive.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

/**
 * @author algas
 *
 */
public interface NumHandler {
	
	Mono<ServerResponse> num(ServerRequest serverRequest);
	
}
