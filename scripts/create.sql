-- script to create places sqlite database from a json file
-- run using sqlite3 database.db < create.sql

-- Places Table

CREATE TABLE places(
	id INTEGER PRIMARY KEY NOT NULL,
	name TEXT NOT NULL,
	description TEXT NOT NULL,
	address TEXT NOT NULL,
	city TEXT NOT NULL,
	image TEXT NOT NULL,
	image_credit TEXT NOT NULL,
	types TEXT NOT NULL,
	website TEXT NOT NULL,
	hours TEXT NOT NULL,
	price TEXT NOT NULL
);

CREATE VIRTUAL TABLE places_fts USING fts4(
	name NOT NULL,
	address NOT NULL,
	city NOT NULL
);

CREATE TRIGGER place_ai AFTER INSERT ON places
	BEGIN
		INSERT INTO places_fts (name, address, city)
		VALUES (new.name, new.address, new.city);
	END;

INSERT INTO places(
	name,
	description,
	address,
	city,
	image,
	image_credit,
	types,
	website,
	hours,
	price
) SELECT
	json_extract(value, '$.name'),
	json_extract(value, '$.description'),
	json_extract(value, '$.address'),
	json_extract(value, '$.city'),
	json_extract(value, '$.image'),
	json_extract(value, '$.image_credit'),
	json_extract(value, '$.types'),
	json_extract(value, '$.website'),
	json_extract(value, '$.hours'),
	json_extract(value, '$.price')
FROM json_each(readfile('places.json'));

DROP TRIGGER place_ai;

-- Cities Table

CREATE TABLE cities(
	id INTEGER PRIMARY KEY NOT NULL,
	name TEXT NOT NULL,
	description TEXT NOT NULL,
	location TEXT NOT NULL,
	image TEXT NOT NULL,
	image_credit TEXT NOT NULL,
	website TEXT NOT NULL,
	best_time TEXT NOT NULL,
	best_time_reason TEXT NOT NULL,
	events TEXT NOT NULL,
	more_info TEXT NOT NULL,
	info_credit TEXT NOT NULL
);

INSERT INTO cities(
	name,
	description,
	location,
	image,
	image_credit,
	website,
	best_time,
	best_time_reason,
	events,
	more_info,
	info_credit
) SELECT
	json_extract(value, '$.name'),
	json_extract(value, '$.description'),
	json_extract(value, '$.location'),
	json_extract(value, '$.image'),
	json_extract(value, '$.image_credit'),
	json_extract(value, '$.website'),
	json_extract(value, '$.best_time'),
	json_extract(value, '$.best_time_reason'),
	json_extract(value, '$.events'),
	json_extract(value, '$.more_info'),
	json_extract(value, '$.info_credit')
FROM json_each(readfile('cities.json'));
