package com.daromi.jtl;

import java.util.concurrent.locks.LockSupport;

final class Main {

  public static void main(final String[] args) {
    var threadCount = 0;

    try {
      while (true) {
        Thread.ofPlatform()
            // Standard "User Threads" prevent the JVM from exiting until they finish.
            // "Daemon Threads" depend on the Main thread. If Main crashes (OOM),
            // the JVM kills all Daemon threads instantly, preventing a zombie process.
            .daemon()
            // Puts the thread in a WAITING state, removing it from the OS scheduler.
            // This ensures we consume RAM (Stack Memory) to hit the limit,
            // but consume 0% CPU while waiting for that limit.
            .start(LockSupport::park);

        threadCount++;

        if (threadCount % 500 == 0) {
          System.out.printf("Spawned: %d threads...\n", threadCount);
        }
      }
    } catch (final OutOfMemoryError e) {
      System.err.printf("Reached Limit: %d threads\n", threadCount);
      System.err.printf("JVM Error: %s\n", e.getMessage());
    }
  }
}
