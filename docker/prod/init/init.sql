CREATE USER bfa_factory WITH PASSWORD '44wpBegekphV1vf0YC';
CREATE DATABASE bfa_factory_db;
GRANT ALL PRIVILEGES ON DATABASE bfa_factory_db TO bfa_factory;

CREATE USER bfa_api WITH PASSWORD '44wpBegekphV1vf0YC';
CREATE DATABASE bfa_api_db;
GRANT ALL PRIVILEGES ON DATABASE bfa_api_db TO bfa_api;

CREATE USER bfa_storage WITH PASSWORD '44wpBegekphV1vf0YC';
CREATE DATABASE bfa_storage_db;
GRANT ALL PRIVILEGES ON DATABASE bfa_storage_db TO bfa_storage;

CREATE USER bfa_statistics WITH PASSWORD '44wpBegekphV1vf0YC';
CREATE DATABASE bfa_statistics_db;
GRANT ALL PRIVILEGES ON DATABASE bfa_statistics_db TO bfa_statistics;

CREATE USER bfa_prediction WITH PASSWORD '44wpBegekphV1vf0YC';
CREATE DATABASE bfa_prediction_db;
GRANT ALL PRIVILEGES ON DATABASE bfa_prediction_db TO bfa_prediction;

CREATE USER bfa_tracker WITH PASSWORD '44wpBegekphV1vf0YC';
CREATE DATABASE bfa_tracker_db;
GRANT ALL PRIVILEGES ON DATABASE bfa_tracker_db TO bfa_tracker;

CREATE USER bfa_notification WITH PASSWORD '44wpBegekphV1vf0YC';
CREATE DATABASE bfa_notification_db;
GRANT ALL PRIVILEGES ON DATABASE bfa_notification_db TO bfa_notification;