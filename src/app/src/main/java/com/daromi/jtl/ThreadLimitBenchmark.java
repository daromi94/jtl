package com.daromi.jtl;

import java.util.concurrent.locks.LockSupport;

final class ThreadLimitBenchmark {

  private static final int LOG_INTERVAL = 500;

  public static void main(final String[] args) {
    var count = 0;

    try {
      while (true) {
        Thread.ofPlatform()
            .daemon() // JVM kills these on exit; prevents zombie on OOM
            .start(
                () -> {
                  while (true) {
                    // consumes memory, not CPU; loop handles spurious wakeups
                    LockSupport.park();
                  }
                });

        count++;

        if (count % LOG_INTERVAL == 0) {
          System.out.printf("%d\n", count);
        }
      }
    } catch (final OutOfMemoryError e) {
      System.err.printf("limit: %d\n", count);
      System.err.printf("error: %s\n", e.getMessage());
      System.exit(1);
    }
  }
}
