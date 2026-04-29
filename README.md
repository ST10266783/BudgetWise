 BudgetWise 

A personal budget tracker Android app built with Kotlin, Room DB, and MVVM architecture.

 Features
-  Secure login & registration with SHA-256 password hashing
-  Custom spending categories with colour coding and monthly limits
-  Expense entries with date, start/end time, description, optional receipt photo
-  Monthly minimum and maximum spending goals
-  Filterable expense history with date range and search
-  Receipt photo capture (camera or gallery) with full-screen viewer
-  Spending-by-category pie chart and tabular report
-  Full offline support via Room (SQLite) — no internet required

 Tech Stack
| Layer | Technology |
|---|---|
| Language | Kotlin |
| Architecture | MVVM + Repository |
| Database | Room (SQLite) |
| UI | Navigation Component + Material 3 |
| Charts | MPAndroidChart |
| Images | Glide |
| Async | Coroutines + LiveData |

 Project Structure

com.budgetwise.app/
├── data/
│   ├── db/         → DAOs + AppDatabase
│   ├── entities/   → Room @Entity data classes
│   └── repository/ → Repository classes
├── ui/
│   ├── auth/       → Login & Register
│   ├── dashboard/  → Home screen
│   ├── expenses/   → Expense list, add/edit, receipt viewer
│   ├── categories/ → Category list, add/edit
│   ├── goals/      → Monthly goal setting
│   └── reports/    → Pie chart + category breakdown
└── utils/          → SessionManager, DateUtils, ImageUtils, PasswordUtils


 Setup
1. Clone: `git clone https://github.com/ST10266783/BudgetWise.git`
2. Open in **Android Studio Hedgehog** or later
3. Sync Gradle (it will download all dependencies automatically)
4. Run on an emulator or physical device (API 24+)

 Running Tests

./gradlew test


 CI/CD
GitHub Actions runs unit tests and builds the APK on every push to `main`.

 Default Categories
On first registration, 8 default categories are automatically created:
Uncategorized · Groceries · Dining Out · Transport · Entertainment · Utilities · Rent/Mortgage · Shopping


YouTube Demonstration link:

https://youtu.be/e3FxU7jhmgI?si=kUdLOERZ34YS5BJs
