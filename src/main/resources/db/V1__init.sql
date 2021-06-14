CREATE TABLE account
(
    id    BIGINT(30)                              NOT NULL AUTO_INCREMENT,
    name  VARCHAR(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    email VARCHAR(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    PRIMARY KEY (id)
)
