# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview
THT (Topic Hot Today's) is a dating application connecting people through daily hot topics and shared interests. The project uses a Spring Boot-based multi-module architecture with comprehensive testing and CI/CD capabilities.

## Architecture & Structure

### Multi-Module Organization
```
THT-Server/
├── tht-apis/           # Main REST API server for client applications
├── tht-admin/          # Administrative interface and management tools
├── tht-common-utils/   # Shared utilities and cross-cutting concerns
├── tht-core/
│   ├── domain/         # Core business domain logic and data access
│   └── enums/          # Centralized enum definitions and converters
```

### Key Technologies
- **Java 21** (APIs module), **Java 17** (Admin module)
- **Spring Boot 3.4.1** with Security, JPA, MongoDB
- **Kotlin 2.1.0** (mixed language support)
- **MySQL** (primary database) + **MongoDB** (chat functionality)
- **QueryDSL** for type-safe queries
- **JWT** authentication with RS256 algorithm
- **Spring REST Docs** + **Swagger UI** for API documentation

### Database Architecture
- **Primary**: MySQL via Spring Data JPA with QueryDSL
- **Secondary**: MongoDB for chat messages and real-time data
- **Testing**: H2 in-memory for unit tests, dedicated test DBs for integration
- **Time Handling**: Uses `Instant` for timezone-safe timestamps (recently migrated from `LocalDateTime`)

## Development Commands

### Build & Test
```bash
# Run all tests
./gradlew test

# Run tests for specific modules
./gradlew tht-apis:test
./gradlew tht-admin:test
./gradlew tht-core:domain:test

# Build specific modules
./gradlew tht-apis:build
./gradlew tht-admin:build

# Clean and build
./gradlew clean build
```

### Documentation Generation
```bash
# Generate OpenAPI documentation (runs automatically with tests)
./gradlew tht-apis:openapi3
./gradlew tht-admin:openapi3

# Access Swagger UI
# APIs: http://localhost:8080/swagger-ui/index.html
# Admin: http://localhost:8080/swagger-ui/index.html (admin server)
```

### Module-Specific Commands
```bash
# APIs module (Java 21)
./gradlew tht-apis:bootRun

# Admin module (Java 17)
./gradlew tht-admin:bootRun

# Run single test classes
./gradlew tht-apis:test --tests "com.tht.thtapis.acceptance.*"
./gradlew tht-core:domain:test --tests "com.tht.domain.entity.*"
```

## Testing Strategy

### Test Types & Patterns
- **Unit Tests**: Standard JUnit 5 with MockMvc
- **Integration Tests**: `@SpringBootTest` with test profiles
- **Acceptance Tests**: RestAssured with custom `@AcceptanceTest` annotation
- **MongoDB Tests**: In-memory MongoDB server with `@AcceptanceTestWithMongo`

### Test Configuration
- **Test Profiles**: `application-test.yaml` in each module
- **Database Cleanup**: `DatabaseCleanup` class for test isolation
- **Fixtures**: Comprehensive test data builders in `fixture/` packages
- **Custom Annotations**: `@AcceptanceTest`, `@WithCustomMockUser`

### Test Data Management
- **Test Fixtures**: Located in `src/testFixtures/java/fixture/`
- **Test Configuration**: `*TestConfiguration` classes for test-specific beans
- **Mock Security**: Custom security context for authentication testing

## Architecture Patterns

### Domain-Driven Design
- **Rich Domain Models**: Entities with business logic encapsulation
- **Aggregate Roots**: User, Chat, DailyFalling as primary aggregates
- **Domain Services**: Complex business operations in service layer
- **Repository Pattern**: Custom QueryDSL implementations for complex queries

### Facade Pattern
- **Custom `@Facade` Annotation**: Extends `@Service` for business logic encapsulation
- **Controller-Facade-Service-Repository**: Clear layering with facade as business entry point
- **Response/Request DTOs**: Clean API contract separation

### Security Architecture
- **JWT Authentication**: Separate token handling for API and Admin
- **Role-Based Access**: `UserRole` enum with converter
- **Security Filters**: `JwtFilter` and `ExceptionHandlerFilter`
- **Token Management**: `TokenProvider` with RS256 algorithm

## CI/CD Pipeline

### GitHub Actions Workflows
- **Label-Based Deployment**: PR labels (`app`, `admin`) trigger specific workflows
- **Dual Pipeline**: Separate workflows for API (Java 21) and Admin (Java 17)
- **AWS CodeDeploy**: Automated deployment to EC2 instances

### Deployment Process
1. **Test Execution**: Comprehensive test suite validation
2. **Build**: Module-specific build with dependency resolution
3. **Artifact Creation**: ZIP packaging for AWS deployment
4. **S3 Upload**: Deployment artifacts stored in S3
5. **CodeDeploy**: Blue-green deployment with rollback capability

### Deployment Commands
```bash
# Triggered by PR merge with appropriate labels
# No manual deployment commands - all automated via GitHub Actions
```

## Code Quality & Standards

### Time Handling (Recent Migration)
- **Standard**: Use `Instant` for all timestamp fields (migrated from `LocalDateTime`)
- **Conversion**: `Instant` → `ZonedDateTime` → String for API responses
- **Unix Timestamps**: `Instant.getEpochSecond()` for client compatibility
- **Timezone**: Explicit `ZoneId.systemDefault()` for consistency

### Entity Patterns
- **Auditable Base**: `@CreatedDate` and `@LastModifiedDate` with `Instant`
- **Enum Converters**: JPA attribute converters for type-safe enum persistence
- **State Management**: `EntityState` enum for soft delete patterns

### API Response Standards
- **JSON Serialization**: Spring Boot default ISO-8601 format for `Instant`
- **Error Responses**: Standardized `ErrorResponse` with timestamp
- **Documentation**: Test-driven API documentation with Spring REST Docs

## Key Development Notes

### Database Queries
- **QueryDSL**: Prefer for complex queries over native JPQL
- **Custom Repositories**: Implement `*CustomRepository` interfaces for domain-specific queries
- **Pagination**: Use Spring Data pagination with QueryDSL integration

### Security Implementation
- **JWT Tokens**: Separate validity periods for different user types
- **Password Handling**: BCrypt encryption with Spring Security
- **CORS Configuration**: Environment-specific CORS settings

### Testing Guidelines
- **Test Isolation**: Use `@Transactional` with rollback for database tests
- **Mock Strategy**: Prefer `@MockBean` for Spring context, `@Mock` for unit tests
- **Acceptance Testing**: Use RestAssured for end-to-end API testing

### Performance Considerations
- **Connection Pooling**: Separate pools for MySQL and MongoDB
- **Query Optimization**: P6Spy integration for SQL monitoring
- **Caching**: Gradle build caching in CI/CD pipeline

## Module-Specific Notes

### tht-apis Module
- **JWT Security**: Full authentication and authorization
- **Chat Functionality**: Real-time messaging with MongoDB
- **Daily Falling**: Topic-based matching system

### tht-admin Module  
- **Admin Authentication**: Separate JWT token management
- **User Management**: Administrative operations and reporting
- **Simpler Architecture**: Focused on CRUD operations

### tht-core:domain Module
- **Entity Definitions**: All JPA entities with business logic
- **Repository Implementations**: QueryDSL custom repositories
- **Domain Services**: Complex business operations

### tht-core:enums Module
- **Centralized Enums**: All domain enums with JPA converters
- **Documentation Utils**: Enum documentation generation utilities

### tht-common-utils Module
- **Time Utilities**: `UnixTimeUtils` for timestamp conversion
- **Logging**: P6Spy SQL formatter for database monitoring
- **Random Data**: Test data generation utilities

This architecture provides a solid foundation for a production-ready dating application with comprehensive testing, documentation, and deployment automation.