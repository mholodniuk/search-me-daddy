CREATE TYPE "access_privilege" AS ENUM ('VIEWER', 'MODIFIER', 'OTHER');

-- todo: define on delete cascade

CREATE TABLE "rooms"
(
    "id"          SERIAL PRIMARY KEY,
    "name"        TEXT      NOT NULL,
    "private"     boolean   NOT NULL,
    "owner_id"    INTEGER   NOT NULL,
    "created_at"  TIMESTAMP NOT NULL,
    "modified_at" TIMESTAMP
);

-- ^ todo owner_id + name => unique

CREATE TABLE "access_keys"
(
    "id"             UUID PRIMARY KEY,
    "name"           TEXT,
    "rights"         access_privilege NOT NULL,
    "valid_from"     TIMESTAMP        NOT NULL,
    "valid_to"       TIMESTAMP,
    "room_id"        INTEGER          NOT NULL,
    "issuer_id"      INTEGER          NOT NULL,
    "participant_id" INTEGER          NOT NULL
);

CREATE TABLE "documents"
(
    "id"               UUID PRIMARY KEY,
    "name"             TEXT      NOT NULL,
    "tags"             TEXT[],
    "content_type"     TEXT      NOT NULL,
    "room_id"          INTEGER   NOT NULL,
    "owner_id"         INTEGER   NOT NULL,
    "file_location_id" INTEGER   NOT NULL,
    "uploaded_at"      TIMESTAMP NOT NULL
);

CREATE TABLE "file_locations"
(
    "id"           SERIAL PRIMARY KEY,
    "storage_type" TEXT NOT NULL,
    "file_path"    TEXT NOT NULL
);

CREATE TABLE "users"
(
    "id"           SERIAL PRIMARY KEY,
    "username"     TEXT NOT NULL UNIQUE,
    "display_name" TEXT NOT NULL,
    "email"        TEXT NOT NULL UNIQUE,
    "password"     TEXT NOT NULL
);

ALTER TABLE
    "rooms"
    ADD
        FOREIGN KEY ("owner_id") REFERENCES "users" ("id");

ALTER TABLE
    "documents"
    ADD
        FOREIGN KEY ("owner_id") REFERENCES "users" ("id");

ALTER TABLE
    "documents"
    ADD
        FOREIGN KEY ("room_id") REFERENCES "rooms" ("id");

ALTER TABLE
    "access_keys"
    ADD
        FOREIGN KEY ("issuer_id") REFERENCES "users" ("id");

ALTER TABLE
    "access_keys"
    ADD
        FOREIGN KEY ("participant_id") REFERENCES "users" ("id");

ALTER TABLE
    "access_keys"
    ADD
        FOREIGN KEY ("room_id") REFERENCES "rooms" ("id");

ALTER TABLE
    "documents"
    ADD
        FOREIGN KEY ("file_location_id") REFERENCES "file_locations" ("id");