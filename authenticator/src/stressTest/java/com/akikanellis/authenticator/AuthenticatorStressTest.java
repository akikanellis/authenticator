package com.akikanellis.authenticator;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class AuthenticatorStressTest {
    private Authenticator authenticator;

    @Before public void beforeEach() { authenticator = new Authenticator(100, MILLISECONDS); }

    @Test(timeout = 2500)
    public void generatingPasswordsInHighQuantity_forDifferentUsers_isFastAndDoesNotCrash() {
        IntStream.range(0, 1000000)
                .forEach(i -> authenticator.generatePassword("user-id" + i));
    }

    @Test(timeout = 200)
    public void generatingPasswordsInHighQuantity_forSameUser_isVeryFastAndDoesNotCrash() {
        IntStream.range(0, 1000000)
                .forEach(i -> authenticator.generatePassword("user-id"));
    }

    @Test(timeout = 4000)
    public void generatingAndValidatingInHighQuantity_doesNotLockUp() throws InterruptedException {
        List<String> firstRandomIds = generateRandomIds();
        List<String> secondRandomIds = generateRandomIds();
        Random random = new Random();
        Runnable generatingRunnable = () -> firstRandomIds.forEach(id -> authenticator.generatePassword(id));
        Runnable validatingRunnable = () ->
                secondRandomIds.forEach(id -> authenticator.isPasswordValid(id, random.nextInt()));
        Thread generatingThread = new Thread(generatingRunnable);
        Thread validatingThread = new Thread(validatingRunnable);

        generatingThread.start();
        validatingThread.start();

        generatingThread.join();
        validatingThread.join();
    }

    private List<String> generateRandomIds() {
        return generateShuffledIntegerList()
                .stream()
                .map(i -> "user-id" + i)
                .collect(Collectors.toList());
    }

    private List<Integer> generateShuffledIntegerList() {
        List<Integer> randomNumbers = IntStream.range(0, 1000000)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(randomNumbers);
        return randomNumbers;
    }
}
