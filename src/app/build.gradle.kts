plugins {
    application
}

group = "com.daromi"

repositories {
    mavenCentral()
}

dependencies {}

application {
    mainClass = "$group.${rootProject.name}.Main"
}
