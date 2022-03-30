.PHONY: build

build:
	./gradlew clean build --exclude-task test

clean:
	./gradlew clean
	docker-compose --file src/main/docker/postgresql.yml down --volumes

start: docker-db run-dev

run-dev:
	./gradlew run --args='--spring.profiles.active=dev'

docker-db:
	docker-compose --file src/main/docker/postgresql.yml up --detach --force-recreate

test:
	./gradlew test
