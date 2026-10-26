# Tenant Management System

Android Kotlin project for the View Binding, Data Binding, and Intents practicals.

Open this folder in Android Studio, sync Gradle, and run on an Android emulator or phone. Use JDK 17 or newer and Android SDK 36. Build with `gradlew.bat assembleDebug` on Windows or `./gradlew assembleDebug` on macOS/Linux.

Login opens first. Any nonempty email and password work for this practical; registration demonstrates navigation and email passing, without storing accounts. Register returns to Login with the email filled in. Login passes EMAIL to MainActivity, which displays "Logged in as ..." in a Toast.

The Add Tenant form uses View Binding for inputs. View Tenants uses Data Binding for the new tenant and displays all repository records. All three empty fields show "Required" before saving.

CALL TENANT opens the dialer. SHARE opens the Android chooser with ACTION_SEND, text/plain, and tenant.summary() as EXTRA_TEXT. Both require a saved tenant. The help link opens the Strathmore website.

Try registering, logging in, saving John Kamau / 0712345678 / 25000, and tapping SHARE. Try each empty input to check validation.

## Lab 7: Navigation, Safe Args, and the Drawer

Login and Register remain separate Activities. Login passes EMAIL to MainActivity, which now hosts the navigation graph and toolbar. The drawer header displays the email.

Dashboard shows the in-memory tenant count and total rent. ADD TENANT and VIEW TENANTS navigate using generated Safe Args directions. Saving validates all three fields, rejects phone numbers with fewer than 10 digits, adds the tenant to TenantRepository, and passes it to ViewTenantsFragment through Safe Args. The Just added section uses Data Binding and survives rotation through Fragment arguments.

CALL TENANT opens the dialer without placing a call. SHARE opens the Android text share chooser. The Just added section and its buttons are hidden when View Tenants opens from Dashboard or the menu without a new tenant.

The save action uses popUpTo Dashboard, so Back returns to updated metrics rather than the form. Dashboard actions use the Navigation library animations. The toolbar overflow menu opens View Tenants or logs out. The drawer offers Dashboard, Add Tenant, View Tenants, About, and Logout. Logout clears the task so Back cannot return to the signed-in screens.

MainActivity, LoginActivity, RegisterActivity, and the three main fragments log lifecycle callbacks with TENANT_LIFECYCLE. Filter Logcat with `tag:TENANT_LIFECYCLE`. Watch Dashboard onDestroyView when navigating away, then onCreateView when returning; rotation recreates both Activity and Fragment.

The extra exercises include Add Tenant lifecycle logs, phone validation, About, and drawer logout. The final app uses the requested drawer; the bottom navigation exercise describes an alternative layout.

TenantRepository is intentionally in-memory for this lab. Records survive navigation and rotation within the same process, but are not stored permanently.

Run-through: log in, check the drawer email, add John Kamau / 0712345678 / 25000, rotate View Tenants, try CALL and SHARE, press Back, verify 1 tenant and KSh 25,000, open View Tenants from the menu, then log out and verify Back closes Login.
