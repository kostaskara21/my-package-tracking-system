# Package Tracking System

A Spring Boot service that consumes package tracking events from Kafka, stores the package state in PostgreSQL, and emails the recipient when a package is delivered.

## How it works

1. A `TrackingEvent` (JSON) is published to the Kafka topic `example.send.message.topic`.
2. The consumer picks a parsing strategy based on the event `type` and parses the XML `payload`.
3. The package is created or updated in the `package` table.
4. A scheduled job (every minute) finds packages with status `PACKAGE_DELIVERED`, emails the recipient, and sets the status to `NOTIFIED`.

## Tech stack

- Java 17, Spring Boot 3.1.5, Maven
- Kafka + Zookeeper, Kafka UI
- PostgreSQL 15, Liquibase
- db-scheduler, Spring Mail (Gmail SMTP or MailHog)

## Prerequisites

- Java 17
- Maven
- Docker and Docker Compose

## Run the infrastructure with Docker

Start everything (Postgres, Liquibase migrations, Zookeeper, Kafka, Kafka UI):

```bash
docker compose up -d
```

Stop everything:

```bash
docker compose down
```

Stop everything and delete the database data:

```bash
docker compose down -v
```

| Service    | Address                 |
|------------|-------------------------|
| App        | `http://localhost:8081` |
| Kafka UI   | `http://localhost:8086` |
| MailHog UI | `http://localhost:8025` (only if enabled) |

## Email setup

There are two ways to send emails. Pick one.

### Option 1: Gmail (default)

1. Turn on 2-Step Verification in your Google account.
2. Create an App Password at <https://myaccount.google.com/apppasswords> and copy the 16-character password.
3. Create a `.env` file in the project root:

```properties
EMAIL_USER=your.address@gmail.com
EMAIL_PASS=your16charapppassword
```

4. Load the variables and start the app:

```bash
set -a; source .env; set +a
mvn spring-boot:run
```

If you run from IntelliJ, add `EMAIL_USER` and `EMAIL_PASS` to the environment variables of the run configuration instead.

> Never commit the `.env` file. It is already listed in `.gitignore`.

### Option 2: MailHog (local, no real emails)

MailHog catches every email locally, so you do not need a Gmail account or a `.env` file.

1. In `docker-compose.yaml`, uncomment the `mailhog` service:

```yaml
  mailhog:
    image: mailhog/mailhog
    container_name: mailhog
    ports:
      - "1025:1025"
      - "8025:8025"
```

2. In `src/main/resources/application.properties`, comment out the Gmail lines and uncomment the MailHog lines:

```properties
#spring.mail.host=smtp.gmail.com
#spring.mail.port=587
#spring.mail.username=${EMAIL_USER}
#spring.mail.password=${EMAIL_PASS}
#spring.mail.properties.mail.smtp.auth=true
#spring.mail.properties.mail.smtp.starttls.enable=true

spring.mail.host=localhost
spring.mail.port=1025
spring.mail.username=123
spring.mail.password=123
spring.mail.properties.mail.smtp.auth=false
spring.mail.properties.mail.smtp.starttls.enable=false
```

3. Restart the containers and start the app:

```bash
docker compose down
docker compose up -d
mvn spring-boot:run
```

4. Open `http://localhost:8025` to see the emails.

## Sending events

Open Kafka UI at `http://localhost:8086`, go to **Topics** → `example.send.message.topic` → **Produce Message**, and paste one of the events below as the message value.

Each event is a JSON object with these fields:

| Field     | Description |
|-----------|-------------|
| `id`      | Event id |
| `type`    | `PACKAGE_CREATED`, `PACKAGE_PICKED_UP`, `PACKAGE_IN_TRANSIT` or `PACKAGE_DELIVERED` |
| `authId`  | User id for the auth service (the auth check is currently disabled) |
| `payload` | The package data as an XML string |

Send `PACKAGE_CREATED` first. The other events are rejected if the package does not exist yet.

### PACKAGE_CREATED

```json
{
  "id": "event-001",
  "type": "PACKAGE_CREATED",
  "payload": "<PackageCreated><packageId>PKG-005</packageId><orderId>ORD-12345</orderId><sender>John Doe</sender><recipient>Jane Smith</recipient><origin>Athens</origin><destination>Thessaloniki</destination><weightKg>2.5</weightKg><priority>HIGH</priority><courierId>COURIER-123</courierId><timestamp>2026-05-31T14:30:00Z</timestamp><status>PACKAGE_CREATED</status></PackageCreated>",
  "authId": "event-001"
}
```

### PACKAGE_PICKED_UP

```json
{
  "id": "event-102",
  "type": "PACKAGE_PICKED_UP",
  "payload": "<PackagePickedUp><packageId>PKG-004</packageId><courierId>COURIER-789</courierId><orderId>ORD-123</orderId><location>Athens Central Hub</location><destination>Thessaloniki Port</destination><priority>URGENT</priority><timestamp>2026-05-31T14:30:00Z</timestamp><status>PACKAGE_PICKED_UP</status></PackagePickedUp>",
  "authId": "courier-user-001"
}
```

### PACKAGE_IN_TRANSIT

```json
{
  "id": "event-002",
  "type": "PACKAGE_IN_TRANSIT",
  "payload": "<PackageInTransit><packageId>PKG-004</packageId><courierId>COURIER-123</courierId><orderId>ORD-123</orderId><location>Larissa Distribution Center</location><timestamp>2026-05-31T14:30:00Z</timestamp><status>PACKAGE_IN_TRANSIT</status></PackageInTransit>",
  "authId": "event-002"
}
```

### PACKAGE_DELIVERED

```json
{
  "id": "event-002",
  "type": "PACKAGE_DELIVERED",
  "payload": "<PackageDelivered><packageId>PKG-007</packageId><courierId>COURIER-123</courierId><orderId>ORD-123</orderId><location>Larissa Distribution Center</location><timestamp>2026-05-31T14:30:00Z</timestamp><status>PACKAGE_DELIVERED</status><recipientEmail>kostas.kara213@gmail.com</recipientEmail></PackageDelivered>",
  "authId": "event-002"
}
```

Within a minute of this event the scheduler sends the "package has arrived" email to the recipient and changes the package status to `NOTIFIED`.

## Configuration

The main settings are in `src/main/resources/application.properties`:

| Property | Default | Description |
|----------|---------|-------------|
| `server.port` | `8081` | Application port |
| `spring.kafka.topic.name` | `example.send.message.topic` | Topic the app consumes |
| `spring.scheduler.EmailNotify.enabled` | `true` | Turns the email job on or off |
| `spring.scheduler.EmailNotify.cron` | `0 * * * * ?` | How often the email job runs |
| `auth.base.url` | `http://localhost:8080` | Auth service URL |
