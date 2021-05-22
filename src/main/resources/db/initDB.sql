DROP TABLE url_dict IF EXISTS;

CREATE TABLE url_dict (
  id         INTEGER IDENTITY PRIMARY KEY,
  long_url VARCHAR(2048),
  short_code  VARCHAR(30),
  visit_count INTEGER
);
CREATE INDEX short_url_dict ON url_dict (short_code);
