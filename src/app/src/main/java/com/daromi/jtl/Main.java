package com.daromi.jtl;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

final class Main {

  public static void main(final String[] args) {
    final var threadCount = new AtomicInteger(1);

    try {
      while (true) {
        Thread.ofPlatform()
            .start(
                () -> {
                  threadCount.incrementAndGet();
                  LockSupport.park();
                });
      }
    } catch (final OutOfMemoryError e) {
      System.err.printf("Reached thread limit %d\n", threadCount.get());
      e.printStackTrace();
    }
  }
}
