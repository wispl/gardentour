-- script to create places sqlite database from a json file
-- run using sqlite3 database.db < create.sql
-- TODO: Investigate if it is better to create a new database or drop and
-- modifiy it

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

-- TODO: Decide if we want to perform fts over description as well
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

