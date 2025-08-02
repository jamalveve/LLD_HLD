package src.ProxyDesignpattern;

import java.util.*;
import java.util.concurrent.*;

interface UserService {
    List<String> getUsers(String country);

    int getAccessCount();
}

class UserServiceImpl implements UserService {
    private final Map<String, List<String>> users = Map.of(
            "us", List.of("user1", "user2"),
            "en", List.of("user3", "user4", "user5"));
    private int count = 0;

    @Override
    public List<String> getUsers(String country) {
        count++;
        return users.get(country);
    }

    @Override
    public int getAccessCount() {
        return count;
    }
}

class CachingUserServiceProxy implements UserService {
    private final UserService userService;
    private final ConcurrentMap<String, List<String>> cache = new ConcurrentHashMap<>();
    private final Object writeLock = new Object();

    public CachingUserServiceProxy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<String> getUsers(String country) {
        if (!cache.containsKey(country)) {
            synchronized (writeLock) {
                if (!cache.containsKey(country)) {
                    List<String> users = userService.getUsers(country);
                    cache.put(country, users);
                }
            }
        }
        return cache.get(country);
    }

    @Override
    public int getAccessCount() {
        return userService.getAccessCount();
    }
}

public class Cacheing {

    public static void main(String[] args) {
        UserService cachingProxy = new CachingUserServiceProxy(new UserServiceImpl());

        System.out.println("US Users (1st Fetch): " + cachingProxy.getUsers("us")); // Fetches from real service
        System.out.println("US Users (2nd Fetch): " + cachingProxy.getUsers("us")); // Returns cached
        System.out.println("EN Users (1st Fetch): " + cachingProxy.getUsers("en")); // Fetches from real service
        System.out.println("EN Users (2nd Fetch): " + cachingProxy.getUsers("en")); // Returns cached

        System.out.println("Access count: " + cachingProxy.getAccessCount());
    }

}
