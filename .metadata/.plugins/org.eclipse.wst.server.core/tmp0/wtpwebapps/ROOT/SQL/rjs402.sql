CREATE DATABASE IF NOT EXISTS rjs402
DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE rjs402;

CREATE TABLE IF NOT EXISTS user(
	id VARCHAR(128) PRIMARY KEY, -- "email"
	-- password VARCHAR(32),
	-- name VARCHAR(32),
	-- ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	jsonstr VARCHAR(1024)
);

CREATE TABLE IF NOT EXISTS feed(
no INT UNSIGNED PRIMARY KEY,
id VARCHAR(128), -- "email"
-- content VARCHAR(4096),
-- images VARCHAR(1024), -- "url"
-- ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP
jsonstr VARCHAR(8192)
);

CREATE TABLE IF NOT EXISTS notice(
no INT UNSIGNED PRIMARY KEY,
id VARCHAR(128),
jsonstr VARCHAR(8192)
);

CREATE TABLE IF NOT EXISTS friend(
id VARCHAR(128), -- "email"
frid VARCHAR(128), -- "email“
INDEX idx1(id)
);

CREATE TABLE IF NOT EXISTS top(
no INT UNSIGNED PRIMARY KEY,
id VARCHAR(128), -- "email"
-- content VARCHAR(4096),
-- images VARCHAR(1024), -- "url"
-- ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP
jsonstr VARCHAR(8192)
);

CREATE TABLE IF NOT EXISTS bottom(
no INT UNSIGNED PRIMARY KEY,
id VARCHAR(128), -- "email"
-- content VARCHAR(4096),
-- images VARCHAR(1024), -- "url"
-- ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP
jsonstr VARCHAR(8192)
);
