--
-- Table structure for table myapps_myapps_parameter
--

DROP TABLE IF EXISTS myapps_myapps_parameter;
CREATE TABLE myapps_myapps_parameter (
  parameter_key varchar(100) NOT NULL,
	parameter_value varchar(100) NOT NULL,
	PRIMARY KEY (parameter_key)
);
