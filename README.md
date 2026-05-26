# FoodTracker

FoodTracker is a Spring Boot web application for searching foods, logging meals, and reviewing nutrition summaries. It uses Thymeleaf for server-rendered pages, Spring Security for form and Google OAuth2 login, MySQL for persistence, and the FatSecret API for food search and nutrition data.

## Tech Stack

- Java 17
- Spring Boot 3.4.3
- Spring MVC, Thymeleaf, Spring Security, OAuth2 Client
- Spring JDBC and Spring Data JPA
- MySQL
- Maven Wrapper

## Features

- Register and sign in with email/password.
- Sign in with Google OAuth2.
- Search foods through FatSecret.
- Log foods by meal type for the authenticated user.
- Retrieve food log history.
- View macro summaries over a date range.
- Navigate server-rendered pages for dashboard, recommendations, log retrieval, registration, login, and about.

## Project Structure

```text
src/main/java/com/example/foodtracker
+-- API/            External API clients
+-- Controller/     MVC and REST controllers
+-- Model/          Domain models
+-- Repository/     Database access
+-- Security/       Spring Security configuration
+-- config/         Web and OAuth client configuration
+-- dto/            Request and response DTOs
+-- service/        Business logic
```

Templates live in `src/main/resources/templates`.

## Prerequisites

- JDK 17 or newer
- MySQL running locally
- FatSecret API credentials
- Google OAuth2 client credentials for Google login
- Optional: Spoonacular and USDA API keys if those integrations are enabled later

## Configuration

The app reads its main settings from `src/main/resources/application.properties`. By default it connects to:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/foodtracker
```

Create the local database before starting the app:

```sql
CREATE DATABASE foodtracker;
```

Secrets are imported from `src/main/resources/application-secrets.properties`. Keep real secrets out of commits; this file is listed in `.gitignore` and should stay local. Use `src/main/resources/application-secrets.example.properties` as a template and create your own local `application-secrets.properties` with values for the placeholders used in `application.properties`, for example:

```properties
DB_USERNAME=your_mysql_username
DB_PASSWORD=your_mysql_password

GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret

fatsecret_api_client-id=your_fatsecret_client_id
fatsecret_api_client-secret=your_fatsecret_client_secret

SPOONACULAR_API_KEY=your_spoonacular_api_key
usda_api_key=your_usda_api_key
```

The current repository does not include database migration scripts. The application expects tables for users and food logs, including `users` and `food_log`.

## Run Locally

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

On macOS or Linux:

```bash
./mvnw spring-boot:run
```

After startup, open:

```text
http://localhost:8080
```

Common pages include:

- `/login`
- `/register`
- `/dashboard`
- `/logRetrieval`
- `/recommendations`
- `/about`

## Useful Endpoints

| Method | Path | Description |
| --- | --- | --- |
| `GET` | `/test` | Basic API health check |
| `GET` | `/getByUserEmail?email=...` | Look up a user by email |
| `GET` | `/searchFat?query=...` | Search foods with FatSecret |
| `GET` | `/getFatNutrition?foodId=...` | Fetch FatSecret nutrition details |
| `POST` | `/api/log` | Log a food for the signed-in user |
| `GET` | `/logsRetrieve` | Retrieve food logs for the signed-in user |
| `GET` | `/macroSummaryChart?start=YYYY-MM-DD&end=YYYY-MM-DD` | Get macro summary for a date range |

Example food log request:

```json
{
  "foodId": 12345,
  "mealType": "breakfast"
}
```

## Testing Endpoints With Postman

Postman can be used to manually test the REST endpoints while the app is running locally.

1. Start the application with `.\mvnw.cmd spring-boot:run`.
2. Use `http://localhost:8080` as the base URL.
3. Test open endpoints first, such as `GET /test`.
4. For protected endpoints, sign in through the browser at `/login`, then reuse the session cookie in Postman or configure Postman authentication as needed.
5. For `POST /api/log`, set the request body type to raw JSON and use the example payload above.

Useful Postman requests:

| Method | URL |
| --- | --- |
| `GET` | `http://localhost:8080/test` |
| `GET` | `http://localhost:8080/searchFat?query=apple` |
| `GET` | `http://localhost:8080/getFatNutrition?foodId=12345` |
| `POST` | `http://localhost:8080/api/log` |
| `GET` | `http://localhost:8080/logsRetrieve` |
| `GET` | `http://localhost:8080/macroSummaryChart?start=2026-05-01&end=2026-05-25` |

## Run Tests

On Windows:

```powershell
.\mvnw.cmd test
```

On macOS or Linux:

```bash
./mvnw test
```

## Build

On Windows:

```powershell
.\mvnw.cmd clean package
```

On macOS or Linux:

```bash
./mvnw clean package
```
