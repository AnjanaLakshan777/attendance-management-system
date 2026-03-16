# Student Attendance Management System

Simple JavaFX desktop application for managing courses, subjects, lecturers, students, class schedules, and attendance.

## Stack

- Java 21
- JavaFX 21
- Maven
- Hibernate ORM
- MySQL
- BCrypt for password hashing

## Project Structure

- `src/main/java` - application source code
- `src/main/resources/view` - JavaFX FXML views
- `src/main/resources/hibernate.cfg.xml` - database configuration
- `Dump20260316.sql` - database dump

## Prerequisites

- JDK 21
- MySQL Server
- Maven wrapper included in the project

## Database Setup

1. Create a MySQL database named `attendance_management`.
2. Import `Dump20260316.sql`.
3. Update database credentials in `src/main/resources/hibernate.cfg.xml` if your local MySQL username or password is different.

Current default configuration in the project points to:

- URL: `jdbc:mysql://localhost:3306/attendance_management`
- Username: `root`
- Password: `1234`

## Run the Application

From the project root on Windows:

```powershell
./mvnw.cmd javafx:run
```

If `JAVA_HOME` is not configured, set it first:

```powershell
$env:JAVA_HOME='C:\Program Files\Java\jdk-21'
./mvnw.cmd javafx:run
```

## Login and Roles

The login screen supports these roles:

- Admin
- Lecturer

Lecturer accounts are created through the application and their passwords are stored as BCrypt hashes.

## Admin Account Mechanism

There is no admin registration flow in the UI.

Admin access works only through a record that already exists in the `user` table. The application checks:

1. `user_id`
2. BCrypt password hash
3. role value

For admin login, only a row in the `user` table is required. No separate admin profile table is used.

### How Admin Login Is Validated

The application logic does the following:

- loads the user by `user_id`
- verifies the plain text password against the stored BCrypt hash
- checks whether the stored role matches `Admin`

Because of this, the password saved in the database must be a BCrypt hash, not plain text.

### How To Create the First Admin

1. Choose an admin user ID, for example `admin`.
2. Generate a BCrypt hash for the password you want to use.
3. Insert the record manually into the `user` table.

Example SQL:

```sql
INSERT INTO `user` (`user_id`, `password`, `role`)
VALUES ('admin', '<bcrypt_hash_here>', 'Admin');
```

### How To Generate the BCrypt Hash

This project already uses BCrypt in the codebase. The same mechanism can be used for admin passwords:

```java
String hashedPassword = BCrypt.withDefaults().hashToString(12, "admin123".toCharArray());
```

Use the generated hash value in the SQL insert.

Example flow:

1. Generate a hash for `admin123`.
2. Copy the generated value.
3. Insert it into the `user` table.
4. Log in with:
   - User ID: `admin`
   - Password: `admin123`
   - Role: `Admin`

## Notes

- Do not store plain text passwords in the database.
- If you manually create more users, keep the role values consistent with the login form.
- Lecturer passwords are also hashed before being stored.
