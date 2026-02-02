# Online Exam - Android Application

A comprehensive Android application for conducting online examinations with separate portals for students and administrators.

## Features

### Student Module
- **Student Login**: Secure authentication for students
- **Exam Dashboard**: View available exams
- **Take Exams**: Interactive exam interface with timer
- **View Results**: Check exam scores and performance

### Admin Module
- **Admin Login**: Secure authentication for administrators
- **Exam Management**: Create and manage exams
- **Question Management**: Add, edit, and delete exam questions
- **Results Tracking**: View all student results and performance analytics

### Common Features
- **Local Database**: SQLite database for offline functionality
- **Session Management**: Persistent login sessions
- **RESTful API Support**: Ready for backend integration
- **Material Design**: Modern and intuitive UI

## Technology Stack

- **Language**: Java
- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)
- **Architecture**: MVC Pattern
- **Database**: SQLite with Room (ready)
- **Networking**: Retrofit + OkHttp
- **UI**: Material Components, CardView, RecyclerView

## Project Structure

```
app/
├── src/main/
│   ├── java/com/example/onlineexam/
│   │   ├── admin/              # Admin module activities
│   │   │   ├── AdminLoginActivity.java
│   │   │   ├── AdminDashboardActivity.java
│   │   │   ├── ManageExamsActivity.java
│   │   │   ├── CreateExamActivity.java
│   │   │   ├── ManageQuestionsActivity.java
│   │   │   └── ViewStudentResultsActivity.java
│   │   ├── student/            # Student module activities
│   │   │   ├── StudentLoginActivity.java
│   │   │   ├── StudentDashboardActivity.java
│   │   │   ├── ExamListActivity.java
│   │   │   ├── TakeExamActivity.java
│   │   │   └── ViewResultsActivity.java
│   │   ├── common/             # Common utilities
│   │   │   ├── AuthManager.java
│   │   │   └── ApiClient.java
│   │   ├── models/             # Data models
│   │   │   ├── User.java
│   │   │   ├── Exam.java
│   │   │   ├── Question.java
│   │   │   ├── Answer.java
│   │   │   └── ExamResult.java
│   │   ├── utils/              # Utility classes
│   │   │   ├── DatabaseHelper.java
│   │   │   ├── SessionManager.java
│   │   │   └── Constants.java
│   │   └── MainActivity.java   # Entry point
│   ├── res/
│   │   ├── layout/             # XML layouts
│   │   ├── values/             # Strings, colors, themes
│   │   ├── drawable/           # Images and icons
│   │   └── menu/               # Menu resources
│   └── AndroidManifest.xml
├── build.gradle                # App-level build configuration
└── proguard-rules.pro          # ProGuard rules
```

## Setup Instructions

### Prerequisites
- Android Studio (Arctic Fox or later)
- JDK 8 or higher
- Android SDK with API level 34
- Gradle 8.1+

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd hms
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to the project directory
   - Wait for Gradle sync to complete

3. **Build the project**
   ```bash
   ./gradlew build
   ```

4. **Run on emulator or device**
   - Connect an Android device or start an emulator
   - Click the "Run" button in Android Studio
   - Or use command line:
     ```bash
     ./gradlew installDebug
     ```

## Default Credentials

### Admin Login
- **Username**: admin
- **Password**: admin123

### Student Login
- **Username**: student
- **Password**: student123

## Database Schema

The app uses SQLite database with the following tables:

- **users**: User accounts (students and admins)
- **exams**: Exam information
- **questions**: Exam questions with multiple choice options
- **answers**: Student answers to questions
- **exam_results**: Final exam results and scores

## API Integration

The app is configured to work with a backend API. Update the base URL in:
```java
utils/Constants.java
```

Default API endpoint: `http://10.0.2.2:8080/api/`

## Key Dependencies

```gradle
// AndroidX libraries
androidx.appcompat:appcompat:1.6.1
com.google.android.material:material:1.10.0
androidx.constraintlayout:constraintlayout:2.1.4
androidx.cardview:cardview:1.0.0
androidx.recyclerview:recyclerview:1.3.2

// Room Database
androidx.room:room-runtime:2.6.0

// Retrofit for API calls
com.squareup.retrofit2:retrofit:2.9.0
com.squareup.retrofit2:converter-gson:2.9.0

// Gson for JSON parsing
com.google.code.gson:gson:2.10.1
```

## Features to Implement

- [ ] Question bank with different question types
- [ ] Real-time exam monitoring
- [ ] Push notifications for exam reminders
- [ ] Analytics dashboard for admin
- [ ] Export results to PDF/Excel
- [ ] Offline exam support with sync
- [ ] Multi-language support
- [ ] Dark mode theme

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For issues, questions, or contributions, please open an issue in the repository.