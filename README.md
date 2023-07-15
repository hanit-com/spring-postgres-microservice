# Spring Boot Microservice

An example project that demonstrates a Spring microservice that performs CRUD operations on Postgres relational database running on Docker.

#### Environment

Create and start postgres container:

```bash
docker run --name postgres-spring -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres:alpine
```

For testing and DB configurations, connect to the container`s shell:

```bash
docker exec -it <container_id> bin/bash
```

Start Postgres CLI:

```bash
psql -U postgres
```

Create the database:

```bash
CREATE DATABASE demodb;
```

Connect:

```bash
\c demodb
```

Add the UUID creation extension:

```bash
CREATE EXTENSION "uuid-ossp";
```

