package org.prashant;

import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiter implements IRateLimiter {
    String clientId;
    int rate;
    long windowSizeInMillis;
    long windowStartTime;
    AtomicInteger requestCount;

    public RateLimiter(String clientId, int rate) {
        this.clientId = clientId;
        this.rate = rate;
        windowSizeInMillis = 1000;
        requestCount = new AtomicInteger(0);
        windowStartTime = 0;

    }

   public boolean limit() {
        //TODO write logic for rate limiting-

        long currTime = System.currentTimeMillis();

        if(currTime-windowStartTime > windowSizeInMillis) {
            //TODO: use synchronised here
            requestCount.set(0);
            windowStartTime = currTime;
        }

        int requests = requestCount.incrementAndGet();
        return requests <= rate;
   }
}
