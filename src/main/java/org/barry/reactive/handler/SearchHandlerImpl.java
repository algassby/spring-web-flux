/**
 * 
 */
package org.barry.reactive.handler;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

/**
 * @author algas
 *
 */
@Component
@Slf4j
public class SearchHandlerImpl implements SearchHandler {

	public static final Random random= new Random();
	@Override
	public Mono<ServerResponse> search(ServerRequest serverRequest) {
		Flux<String> results =  Flux.merge(
				searchSupplier("Supplier 1"),
				searchSupplier("Supplier 2"),
				searchSupplier("Supplier 3"),
				searchSupplier("Supplier 4"),
				searchSupplier("Supplier 5")
				);
		log.info("--- search handler ---");
		return ServerResponse.ok().header("Access-control-Allow-origin", "*")
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(results, String.class);
	}
	
	public Flux<String> searchSupplier(String supplierName){
		List<String> results = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			double randomValue = 10+(99-10) * random.nextDouble();
			BigDecimal value = BigDecimal.valueOf(randomValue).setScale(2, BigDecimal.ROUND_HALF_UP);
			results.add(value+"-"+supplierName);
			log.info(supplierName);
		}
		return  Flux.interval(Duration.ofMillis(random.nextInt(300)))
				.zipWithIterable(results)
				.map(Tuple2::getT2);
	}

}
