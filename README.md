# Tenant Management System

Android Kotlin project for the View Binding, Data Binding, and Intents practicals.

Open this folder in Android Studio, sync Gradle, and run on an Android emulator or phone. Use JDK 17 or newer and Android SDK 36. Build with `gradlew.bat assembleDebug` on Windows or `./gradlew assembleDebug` on macOS/Linux.

Login opens first. Any nonempty email and password work for this practical; registration demonstrates navigation and email passing, without storing accounts. Register returns to Login with the email filled in. Login passes EMAIL to MainActivity, which displays "Logged in as ..." in a Toast.

The Add Tenant screen uses View Binding for inputs and Data Binding for the saved Tenant. All three empty fields show "Required" before saving. SAVE clears the inputs and displays the tenant, with "Rent paid" in the summary and the name in bold. The saved tenant survives rotation.

CALL TENANT opens the dialer. SHARE opens the Android chooser with ACTION_SEND, text/plain, and tenant.summary() as EXTRA_TEXT. Both require a saved tenant. The help link opens the Strathmore website.

Try registering, logging in, saving John Kamau / 0712345678 / 25000, and tapping SHARE. Try each empty input to check validation.

## Lab 6: Lifecycles and Fragments

MainActivity, LoginActivity, and RegisterActivity log all seven Activity lifecycle callbacks with the TENANT_LIFECYCLE tag. In Logcat, filter with `tag:TENANT_LIFECYCLE`.

MainActivity saves the tenant fields and SAVE tap count in onSaveInstanceState and restores them in onCreate. Save a tenant, rotate, and check that the summary, CALL, and SHARE still use that tenant. A Toast shows the restored SAVE tap count, including unsuccessful taps.

The project includes DashboardFragment, AddTenantFragment, ViewTenantsFragment, their layouts, TenantRepository, and nav_graph.xml. Tenant implements Serializable. Safe Args and both Navigation libraries use version 2.9.8.

As required at the end of this practical, Login still opens the existing Add Tenant Activity. The graph and fragments are prepared for Practical 3; they are not connected to a NavHost yet. The dashboard reads repository totals, View Tenants shows the repository or its empty message, and every fragment clears its binding in onDestroyView. Saving and navigation from the new fragment screens belong to Practical 3.

Lifecycle checks:

- Login opens MainActivity and is destroyed because finish() runs.
- Register's Log in link finishes Register and reveals Login.
- Home stops MainActivity; returning normally calls onRestart, onStart, then onResume.
- Rotation saves state and destroys/recreates MainActivity.
- Back finishes MainActivity rather than restoring it.

The optional Navigation Editor exploration adds an arrow from View Tenants to Dashboard temporarily, then removes it; the final graph keeps only the three specified actions.
