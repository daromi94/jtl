plugins {
    application
}

group = "com.daromi"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

application {
    mainClass = "com.daromi.jtl.ThreadLimitBenchmark"
}
