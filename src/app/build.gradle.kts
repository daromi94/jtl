plugins {
    application
}

group = "com.daromi"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "com.daromi.jtl.ThreadLimitBenchmark"
}
