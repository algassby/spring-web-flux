/**
 * 
 */
package org.barry.reactive.controller;

import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author algas
 *
 */
@RestController
@RequestMapping("/api")
public class NumberRestController {

	@GetMapping("/number")
	public Integer getNumber() {
		return 10;
	}
	
	@GetMapping("/rective-number")
	public Mono<Integer> getReactiveNumber(){
		return Mono.just(20);
	}
	
	@GetMapping("/numbers")
	public Integer [] numbers() throws InterruptedException {
		Integer [] numbers = new Integer [10];
		for (int i = 0; i < 10; i++) {
			numbers [i] = i + 1;
			Thread.sleep(500);
		}
		return numbers;
	}
	@GetMapping(value =  "/reactive-numbers", produces = "text/event-stream")
	public Flux<Integer> reactiveNumbers()  {
		return Flux.create(fluxInt->{
			for (int i = 0; i < 10; i++) {
				fluxInt.next(i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			fluxInt.complete();
		});
	}
}
