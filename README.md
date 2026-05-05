# Digital Capsule

## Project Structure (High Level)

- demo/src/main/java/com/example/demo/controller: REST controllers (API entry points)
- demo/src/main/java/com/example/demo/entity: JPA entities
- demo/src/main/java/com/example/demo/repository: Spring Data repositories
- demo/src/main/java/com/example/demo/service: Business logic
- demo/src/main/java/com/example/demo/security: JWT filter and security config
- demo/src/main/resources: application config

AUTH
- POST /auth/register
- POST /auth/login

CAPSULE
- POST /capsules/create
- GET /capsules/my
- GET /capsules/{id}
- GET /capsules/{id}/content
- DELETE /capsules/{id}

RECIPIENT
- POST /capsules/{id}/recipients
- GET /capsules/{id}/recipients

MEDIA
- POST /media/upload
- GET /media/{capsuleId}

NOTIFICATION
- GET /notifications/my

ACCESS LOG
- GET /logs/capsule/{id}
- GET /logs/user/{userId}

