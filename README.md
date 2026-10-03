# Tenant Management System

Android Kotlin project for the Tenant Management practical on View Binding and Data Binding.

Open this folder in Android Studio, sync Gradle, and run the app on Android 7.0 or newer. Use JDK 17 or newer and install Android SDK 36. Android Studio creates local.properties for your SDK location.

Build on Windows with `gradlew.bat assembleDebug`, or on macOS/Linux with `./gradlew assembleDebug`.

The screen uses ConstraintLayout and ActivityMainBinding. SAVE creates a Tenant and assigns it to the layout. The result uses `@{tenant.summary()}` and the large bold name uses `@{tenant.name}`.

All four extra exercises are included: an empty name shows an error, the summary says “Rent paid”, the saved name appears in bold, and the three input fields clear after saving. The saved result survives screen rotation.

Try John Kamau, 0712345678, and 25000. SAVE should show:

```text
John Kamau
Tenant: John Kamau
Phone: 0712345678
Rent paid: KSh 25000
```

Save Mary Wanjiku next to replace the result. Tap SAVE with an empty name to check validation.
