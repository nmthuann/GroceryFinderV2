package com.nmt.groceryfinderv2.shared.ratelimit;

/**
 * @author LENOVO
 * @project GroceryFinder
 * @date 11/29/2024
 */
public class RateLimitExceededException extends RuntimeException{
    public RateLimitExceededException(String message) {
        super(message);
    }
}
