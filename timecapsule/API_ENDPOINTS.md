# API Endpoints

## Auth
- POST /auth/register
- POST /auth/login

## Capsule
- POST /capsules/create
- GET /capsules/my
- GET /capsules/{id}
- GET /capsules/{id}/content
- DELETE /capsules/{id}

## Recipient
- POST /capsules/{id}/recipient
- GET /capsules/{id}/recipients

## Media
- POST /media/upload
- GET /media/{capsuleId}

## Notification
- GET /notifications/my

## Access Log
- GET /logs/capsule/{id}
- GET /logs/user/{userId}
