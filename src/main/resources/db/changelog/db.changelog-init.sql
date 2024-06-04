DROP TABLE IF EXISTS address;
DROP SEQUENCE IF EXISTS address_seq;

CREATE SEQUENCE address_seq start 1 increment 1;


-- Create Address table
CREATE TABLE address (
    id BIGSERIAL PRIMARY KEY,
    house_number INT,
    street VARCHAR(255),
    city VARCHAR(255),
    country VARCHAR(255),
    post_code VARCHAR(10)
);

DROP TABLE IF EXISTS contacts;
DROP SEQUENCE IF EXISTS contacts_seq;

CREATE SEQUENCE contacts_seq start 1 increment 1;

-- Create Contacts table
CREATE TABLE contacts (
    id BIGSERIAL PRIMARY KEY,
    phone VARCHAR(20),
    email VARCHAR(255)
);

DROP TABLE IF EXISTS arrival_time;
DROP SEQUENCE IF EXISTS arrival_time_seq;

CREATE SEQUENCE arrival_time_seq start 1 increment 1;

-- Create ArrivalTime table
CREATE TABLE arrival_time (
    id BIGSERIAL PRIMARY KEY,
    check_in time(5),
    check_out time(5)
--    USING check_in::time without time zone,
--    USING check_out::time without time zone;
);

DROP TABLE IF EXISTS hotels;
DROP SEQUENCE IF EXISTS hotels_seq;

CREATE SEQUENCE hotels_seq start 1 increment 1;

-- Create Hotels table
CREATE TABLE hotels (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    brand VARCHAR(255) NOT NULL,
    address_id BIGINT,
    contacts_id BIGINT,
    arrival_time_id BIGINT,
    CONSTRAINT fk_address FOREIGN KEY (address_id) REFERENCES address(id),
    CONSTRAINT fk_contacts FOREIGN KEY (contacts_id) REFERENCES contacts(id),
    CONSTRAINT fk_arrival_time FOREIGN KEY (arrival_time_id) REFERENCES arrival_time(id)
);

DROP TABLE IF EXISTS amenities;
DROP SEQUENCE IF EXISTS amenities_seq;

CREATE SEQUENCE amenities_seq start 1 increment 1;

-- Create Amenities table with foreign key to hotels
-- Create Amenities table with foreign key to hotels
CREATE TABLE amenities (
    id BIGSERIAL PRIMARY KEY,
    hotel_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT fk_hotel_amenities FOREIGN KEY (hotel_id) REFERENCES hotels(id)
);

