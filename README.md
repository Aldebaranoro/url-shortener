# Url Shortener

This application created for create short and customized links.

## Development

Before you can build this project, you must install and configure the following dependencies on your machine:

1. Java 17
2. Docker, Docker Compose

### Build

```bash
make build
```

## Testing

To launch your application's tests, run:

```bash
make test
```

## Using Docker to simplify development (optional)

For example, to start a postgresql database in a docker container, run:

```bash
make docker-db
```

## Run application with database in docker

```bash
make start
```

## Clear config (database, app build)

```bash
make clean
```

## API calls

For shorten long url:

```plaintext
POST http://localhost:8080/api/shorten
Content-Type: application/json

{
    "longUrl": "https://very-long-url.com/",
    "customLinkName": "my-link-name"
}
```

For redirect to long url:

```plaintext
GET http://localhost:8080/{my-link-name}
```