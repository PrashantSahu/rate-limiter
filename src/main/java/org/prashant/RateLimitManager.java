package org.prashant;

import java.util.HashMap;
import java.util.Map;

//TODO: Make this class singleton
public class RateLimitManager {


    Map<String, RateLimiter> clients;

    public RateLimitManager() {
        clients = new HashMap<>();
    }

    public boolean registerClient(String clientId, int rate) {

        if(clients.containsKey(clientId)) return false;

        clients.put(clientId, new RateLimiter(clientId, rate));

        return true;

    }

    public RateLimiter getRateLimiter(String clientId) {
        return clients.get(clientId);
    }

    public String limit() {

        return "";
    }
}
