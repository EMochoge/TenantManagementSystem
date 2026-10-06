# Tenant Management System

Android Kotlin project for the View Binding, Data Binding, and Intents practicals.

Open this folder in Android Studio, sync Gradle, and run on an Android emulator or phone. Use JDK 17 or newer and Android SDK 36. Build with `gradlew.bat assembleDebug` on Windows or `./gradlew assembleDebug` on macOS/Linux.

Login opens first. Any nonempty email and password work for this practical; registration demonstrates navigation and email passing, without storing accounts. Register returns to Login with the email filled in. Login passes EMAIL to MainActivity, which displays "Logged in as ..." in a Toast.

The Add Tenant screen uses View Binding for inputs and Data Binding for the saved Tenant. All three empty fields show "Required" before saving. SAVE clears the inputs and displays the tenant, with "Rent paid" in the summary and the name in bold. The saved tenant survives rotation.

CALL TENANT opens the dialer. SHARE opens the Android chooser with ACTION_SEND, text/plain, and tenant.summary() as EXTRA_TEXT. Both require a saved tenant. The help link opens the Strathmore website.

Try registering, logging in, saving John Kamau / 0712345678 / 25000, and tapping SHARE. Try each empty input to check validation.
