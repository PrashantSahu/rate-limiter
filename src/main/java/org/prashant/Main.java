package org.prashant;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        RateLimitManager manager = new RateLimitManager();
        manager.registerClient("c1", 2);


        RateLimiter rateLimiter = manager.getRateLimiter("c1");
        CompletableFuture<Boolean> future1 = CompletableFuture.supplyAsync(() -> rateLimiter.limit());
        CompletableFuture<Boolean> future2 = CompletableFuture.supplyAsync(() -> rateLimiter.limit());
        CompletableFuture<Boolean> future3 = CompletableFuture.supplyAsync(() -> rateLimiter.limit());

        CompletableFuture<Boolean> future4 = CompletableFuture.supplyAsync(() -> rateLimiter.limit());

        // Combine and wait for all to complete
        CompletableFuture.allOf(future1, future2, future3, future4).join();

        // Print results
        System.out.println(future1.get());
        System.out.println(future2.get());
        System.out.println(future3.get());

        System.out.println(future4.get());


//
//        System.out.println(rateLimiter.limit());
//        System.out.println(rateLimiter.limit());
//        System.out.println(rateLimiter.limit());
//        System.out.println(rateLimiter.limit());
    }
}
