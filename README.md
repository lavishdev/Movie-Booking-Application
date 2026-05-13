# 🎬 Movie Booking Application

A full-featured **RESTful Movie Booking Backend** built with **Spring Boot**, secured with **JWT-based authentication**, and backed by a **PostgreSQL** database. This application enables users to browse movies, book tickets, and manage reservations through a clean REST API.

---

## 🚀 Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 25 |
| Framework | Spring Boot 4.0.6 |
| Security | Spring Security + JWT (JJWT 0.12.6) |
| Persistence | Spring Data JPA + Hibernate |
| Database | PostgreSQL |
| Build Tool | Maven (Maven Wrapper included) |
| Utilities | Lombok |

---

## ✨ Features

- 🔐 **JWT Authentication** — Secure user registration and login with token-based auth
- 🎥 **Movie Management** — Add, update, delete, and search movies by genre, language, or title
- 🏛️ **Theatre Management** — Add and manage theatres with location-based search
- 🎟️ **Ticket Booking** — Create, confirm, cancel, and query bookings by user, show, or status
- 🛡️ **Role-based Access** — Separate Admin and User roles enforced via Spring Security (`@PreAuthorize`)

---

## 📋 Prerequisites

Make sure you have the following installed before running the project:

- **Java 25** or higher
- **Maven 3.8+** (or use the included `mvnw` wrapper)
- **PostgreSQL** (running locally or via Docker)

---

## ⚙️ Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/lavishdev/Movie-Booking-Application.git
cd Movie-Booking-Application
```

### 2. Configure the Database

Create a PostgreSQL database and update `src/main/resources/application.properties` (or `application.yml`) with your credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/movie_booking_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Configure JWT Secret

Add a JWT secret key to your application properties:

```properties
jwt.secret=your_secret_key_here
jwt.expiration=86400000
```

### 4. Build and Run

Using Maven Wrapper (recommended):

```bash
# On Linux/macOS
./mvnw spring-boot:run

# On Windows
mvnw.cmd spring-boot:run
```

Or build a JAR and run it:

```bash
./mvnw clean package
java -jar target/MovieBookingApplication-0.0.1-SNAPSHOT.jar
```

The application will start at `http://localhost:8080`.

---

## 📁 Project Structure

```
Movie-Booking-Application/
├── src/
│   ├── main/
│   │   ├── java/com/lavish/MovieBookingApplication/
│   │   │   ├── controller/       # REST API controllers
│   │   │   ├── service/          # Business logic layer
│   │   │   ├── repository/       # JPA repositories
│   │   │   ├── model/            # Entity classes
│   │   │   ├── dto/              # Data Transfer Objects
│   │   │   ├── security/         # JWT & Spring Security config
│   │   │   └── MovieBookingApplicationApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/                     # Unit and integration tests
├── .mvn/wrapper/
├── pom.xml
├── mvnw
└── mvnw.cmd
```

---

## 🔑 API Endpoints

### 🔐 Auth — `/api/auth`
| Method | Endpoint | Access | Description |
|---|---|---|---|
| `POST` | `/api/auth/registernormaluser` | Public | Register a new normal user |
| `POST` | `/api/auth/login` | Public | Login and receive JWT token |

### 🛡️ Admin — `/api/admin`
| Method | Endpoint | Access | Description |
|---|---|---|---|
| `POST` | `/api/admin/register` | Admin only | Register a new admin user |

### 🎥 Movies — `/api/movies`
| Method | Endpoint | Access | Description |
|---|---|---|---|
| `POST` | `/api/movies/addmovie` | Admin only | Add a new movie |
| `GET` | `/api/movies/getallmovies` | Public | Get all movies |
| `GET` | `/api/movies/getmoviesbygenre?genre=` | Public | Filter movies by genre |
| `GET` | `/api/movies/getmoviesbylanguage?language=` | Public | Filter movies by language |
| `GET` | `/api/movies/getmoviesbytitle?title=` | Public | Search movies by title |
| `PUT` | `/api/movies/updatemovie/{id}` | Admin only | Update a movie |
| `DELETE` | `/api/movies/deletemovie/{id}` | Admin only | Delete a movie |

### 🎟️ Bookings — `/api/booking`
| Method | Endpoint | Access | Description |
|---|---|---|---|
| `POST` | `/api/booking/createbooking` | Authenticated | Create a new booking |
| `GET` | `/api/booking/getuserbooking/{id}` | Authenticated | Get all bookings for a user |
| `GET` | `/api/booking/getshowbooking/{id}` | Authenticated | Get all bookings for a show |
| `GET` | `/api/booking/getbookingbystats/{bookingStatus}` | Authenticated | Get bookings by status |
| `PUT` | `/api/booking/{id}/confirm` | Authenticated | Confirm a booking |
| `PUT` | `/api/booking/{id}/cancel` | Authenticated | Cancel a booking |

### 🏛️ Theatre — `/api/theatre`
| Method | Endpoint | Access | Description |
|---|---|---|---|
| `POST` | `/api/theatre/addtheatre` | Admin only | Add a new theatre |
| `GET` | `/api/theatre/gettheatrebylocation?theatreLocation=` | Public | Get theatres by location |
| `PUT` | `/api/theatre/updatetheatre/{id}` | Admin only | Update a theatre |
| `DELETE` | `/api/theatre/deletetheatre/{id}` | Admin only | Delete a theatre |

> Secured endpoints require the `Authorization: Bearer <token>` header.

---

## 🧪 Running Tests

```bash
./mvnw test
```

---

## 🐳 Running with Docker (Optional)

You can quickly spin up a PostgreSQL instance using Docker:

```bash
docker run --name movie-booking-db \
  -e POSTGRES_USER=admin \
  -e POSTGRES_PASSWORD=admin \
  -e POSTGRES_DB=movie_booking_db \
  -p 5432:5432 \
  -d postgres
```

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -m 'Add your feature'`)
4. Push to the branch (`git push origin feature/your-feature`)
5. Open a Pull Request

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).

---

## 👤 Author

**Lavish Dev**  
GitHub: [@lavishdev](https://github.com/lavishdev)
