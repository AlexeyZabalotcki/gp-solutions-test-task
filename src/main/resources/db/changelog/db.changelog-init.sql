-- Create Address table
CREATE TABLE address (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    house_number INT,
    street VARCHAR(255),
    city VARCHAR(255),
    country VARCHAR(255),
    post_code VARCHAR(10)
);

-- Create Contacts table
CREATE TABLE contacts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    phone VARCHAR(20),
    email VARCHAR(255)
);

-- Create ArrivalTime table
CREATE TABLE arrival_time (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    check_in VARCHAR(5),
    check_out VARCHAR(5)
);

-- Create Hotels table
CREATE TABLE hotels (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
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

-- Create Amenities table with foreign key to hotels
CREATE TABLE amenities (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    hotel_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT fk_hotel_amenities FOREIGN KEY (hotel_id) REFERENCES hotels(id)
);