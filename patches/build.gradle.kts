group = "app.rjx"

patches {
    about {
        name = "RJX Patches"
        description = "Community-made patches for Android apps supported by Morphe."
        source = "https://github.com/visionxstream99-mod/rjx-patches"
        author = "RJ"
        contact = "https://github.com/visionxstream99-mod/rjx-patches/issues"
        website = "https://github.com/visionxstream99-mod/rjx-patches"
        license = "GPLv3"
    }
}

val patchListGeneratorClasspath = configurations.create("patchListGeneratorClasspath")

dependencies {
    compileOnly(libs.gson)
    patchListGeneratorClasspath(libs.gson)
}

tasks {
    register<JavaExec>("generatePatchesList") {
        description = "Build patch with patch list"
        dependsOn(build)
        classpath = sourceSets["main"].runtimeClasspath + patchListGeneratorClasspath
        mainClass.set("util.PatchListGeneratorKt")
    }

    publish {
        dependsOn("generatePatchesList")
    }
}
