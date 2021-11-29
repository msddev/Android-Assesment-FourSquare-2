import dependencies.DomainDep

plugins {
    id(Config.Plugins.javaLibrary)
    id(Config.Plugins.kotlin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(DomainDep.kotlin)
    implementation(DomainDep.coroutineCore)

    // JavaX
    implementation(DomainDep.javax)

    // Test Dependencies
    testImplementation(DomainDep.Test.junit)
    testImplementation(DomainDep.Test.assertJ)
    testImplementation(DomainDep.Test.mockitoKotlin)
    testImplementation(DomainDep.Test.mockitoInline)
    testImplementation(DomainDep.Test.coroutines)
}