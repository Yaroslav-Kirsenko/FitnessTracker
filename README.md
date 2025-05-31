Завдання 3: Веб-фітнес трекер (Spring Boot + DB)

Опис: Розробити повноцінний веб-додаток для фітнес-трекера з використанням фреймворку Spring Boot,
який надаватиме REST API і за бажанням базовий інтерфейс.

Технології 

Java 21
Spring Boot
Spring Security + JWT
PostgreSQL
Maven
Docker 

Структура проєкту 

Configs - Безпека, JWT фільтри
Controllers - REST API контролери
DTO - Data Transfer Object
Models - JPA сутності
Repositories - Spring Data репозиторії
Services - Бізнес логіка
FitnessTrackerApplication.java


Безпека

JWT авторизація реалізована через:
JwtAuthenticationFilter 
SecurityConfiguration

DTOs

WorkoutStatsDTO — агреговані дані про тренування.
GoalDTO - цілі у тренувань.
CaloriesProgressDTO - данні про прогрес в калоріях.
WorkoutDTO - створювання тренувань.
SignupRequest — реєстрація.
SigninRequest - вхід.
JwtAuthenticationResponse - отримання JWT токена.