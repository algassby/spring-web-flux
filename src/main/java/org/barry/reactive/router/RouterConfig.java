/**
 * 
 */
package org.barry.reactive.router;

import org.barry.reactive.handler.NumHandler;
import org.barry.reactive.handler.SearchHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.extern.slf4j.Slf4j;


/**
 * @author algas
 *
 */
@Configuration
@Slf4j
public class RouterConfig  {
	
	private  NumHandler handler;
	private SearchHandler searchHandler;
	
	/**
	 * @param handler
	 */
	public RouterConfig(NumHandler handler, SearchHandler searchHandler) {
		super();
		this.handler = handler;
		this.searchHandler = searchHandler;
	}


	@Bean
	public RouterFunction<ServerResponse> routerFunction(){
		log.info("--- beign router config ---");
		return RouterFunctions.route(RequestPredicates.GET("/num"), 
				handler::num)
				.andRoute(RequestPredicates.GET("/search"), searchHandler::search);
	}
	
}
