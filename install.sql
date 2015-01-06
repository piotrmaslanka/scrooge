-- Table: asset_notes

-- DROP TABLE asset_notes;

CREATE TABLE asset_notes
(
  id_asset character varying(30), -- ID środka
  when_created timestamp without time zone, -- Kiedy złożono informację
  is_damaged boolean NOT NULL, -- Czy środek został uszkodzony?
  is_lost boolean NOT NULL, -- Czy środek został zagubiony?
  reason text, -- Powód zgłoszenia
  id serial NOT NULL, -- ID zgłoszenia
  who_reported character varying(256), -- Kto zgłosił
  is_solved boolean NOT NULL DEFAULT false, -- Czy nota została załatwiona
  CONSTRAINT pk_asset_notes PRIMARY KEY (id),
  CONSTRAINT fk_asset_notes_assets FOREIGN KEY (id_asset)
      REFERENCES assets (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_asset_notes_users FOREIGN KEY (who_reported)
      REFERENCES users (login) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE asset_notes
  OWNER TO l4_common;
COMMENT ON TABLE asset_notes
  IS 'Informacje do administratora o stanie przedmiotu';
COMMENT ON COLUMN asset_notes.id_asset IS 'ID środka';
COMMENT ON COLUMN asset_notes.when_created IS 'Kiedy złożono informację';
COMMENT ON COLUMN asset_notes.is_damaged IS 'Czy środek został uszkodzony?';
COMMENT ON COLUMN asset_notes.is_lost IS 'Czy środek został zagubiony?';
COMMENT ON COLUMN asset_notes.reason IS 'Powód zgłoszenia';
COMMENT ON COLUMN asset_notes.id IS 'ID zgłoszenia';
COMMENT ON COLUMN asset_notes.who_reported IS 'Kto zgłosił';
COMMENT ON COLUMN asset_notes.is_solved IS 'Czy nota została załatwiona';


-- Index: fki_asset_notes_users

-- DROP INDEX fki_asset_notes_users;

CREATE INDEX fki_asset_notes_users
  ON asset_notes
  USING btree
  (who_reported COLLATE pg_catalog."default");



-- Table: assets

-- DROP TABLE assets;

CREATE TABLE assets
(
  id character varying(30) NOT NULL, -- Nr ewidencyjny środka
  location character varying(10), -- Pełna nazwa sali (położenie środka)
  responsibler character varying(256), -- Uzytkownik odpowiedzialny za środek
  purchase_date date NOT NULL, -- Data zakupu środka
  description text, -- Wyjaśnienie czym jest środek
  extra_info text, -- Dodatkowe informacje eksploatacyjno-ewidencyjne (np. ilość sztuk, uszkodzenia).
  is_lendable boolean NOT NULL, -- Czy środek podlega wypożyczeniu
  is_material boolean NOT NULL, -- Czy jest to środek materialny
  is_damaged boolean NOT NULL DEFAULT false, -- Czy jest uszkodzony
  is_lost boolean NOT NULL DEFAULT false, -- Czy środek jest zagubiony/skradziony
  CONSTRAINT pk_assets PRIMARY KEY (id),
  CONSTRAINT fk_assets_location FOREIGN KEY (location)
      REFERENCES location (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_assets_users FOREIGN KEY (responsibler)
      REFERENCES users (login) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE assets
  OWNER TO l4_common;
COMMENT ON TABLE assets
  IS 'Tabela środków';
COMMENT ON COLUMN assets.id IS 'Nr ewidencyjny środka';
COMMENT ON COLUMN assets.location IS 'Pełna nazwa sali (położenie środka)';
COMMENT ON COLUMN assets.responsibler IS 'Uzytkownik odpowiedzialny za środek';
COMMENT ON COLUMN assets.purchase_date IS 'Data zakupu środka';
COMMENT ON COLUMN assets.description IS 'Wyjaśnienie czym jest środek';
COMMENT ON COLUMN assets.extra_info IS 'Dodatkowe informacje eksploatacyjno-ewidencyjne (np. ilość sztuk, uszkodzenia).';
COMMENT ON COLUMN assets.is_lendable IS 'Czy środek podlega wypożyczeniu';
COMMENT ON COLUMN assets.is_material IS 'Czy jest to środek materialny';
COMMENT ON COLUMN assets.is_damaged IS 'Czy jest uszkodzony';
COMMENT ON COLUMN assets.is_lost IS 'Czy środek jest zagubiony/skradziony';


-- Index: fki_assets_location

-- DROP INDEX fki_assets_location;

CREATE INDEX fki_assets_location
  ON assets
  USING btree
  (location COLLATE pg_catalog."default");

-- Index: fki_assets_users

-- DROP INDEX fki_assets_users;

CREATE INDEX fki_assets_users
  ON assets
  USING btree
  (responsibler COLLATE pg_catalog."default");



-- Table: lends

-- DROP TABLE lends;

CREATE TABLE lends
(
  id_asset character varying(30) NOT NULL, -- ID środka
  id serial NOT NULL, -- ID wypożyczenia
  lend_from timestamp without time zone NOT NULL, -- Odkąd wypożyczono
  lend_to timestamp without time zone NOT NULL, -- Do kiedy wypożyczono
  who_lended character varying(256) NOT NULL, -- Kto wypożyczył
  purpose text NOT NULL, -- Cel wypożyczenia
  extra_info text, -- Dodatkowe informacje
  CONSTRAINT pk_lends PRIMARY KEY (id),
  CONSTRAINT fk_lends_assets FOREIGN KEY (id_asset)
      REFERENCES assets (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_lends_users FOREIGN KEY (who_lended)
      REFERENCES users (login) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE lends
  OWNER TO l4_common;
COMMENT ON TABLE lends
  IS 'Wypożyczenia środków';
COMMENT ON COLUMN lends.id_asset IS 'ID środka';
COMMENT ON COLUMN lends.id IS 'ID wypożyczenia';
COMMENT ON COLUMN lends.lend_from IS 'Odkąd wypożyczono';
COMMENT ON COLUMN lends.lend_to IS 'Do kiedy wypożyczono';
COMMENT ON COLUMN lends.who_lended IS 'Kto wypożyczył';
COMMENT ON COLUMN lends.purpose IS 'Cel wypożyczenia';
COMMENT ON COLUMN lends.extra_info IS 'Dodatkowe informacje';


-- Index: fki_lends_users

-- DROP INDEX fki_lends_users;

CREATE INDEX fki_lends_users
  ON lends
  USING btree
  (who_lended COLLATE pg_catalog."default");



-- Table: location

-- DROP TABLE location;

CREATE TABLE location
(
  id character varying(10) NOT NULL, -- Nazwa sali
  CONSTRAINT pk_location PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE location
  OWNER TO l4_common;
GRANT ALL ON TABLE location TO l4_common;
GRANT INSERT ON TABLE location TO public;
COMMENT ON TABLE location
  IS 'Sale';
COMMENT ON COLUMN location.id IS 'Nazwa sali';



-- Table: report

-- DROP TABLE report;

CREATE TABLE report
(
  id serial NOT NULL, -- ID raportu
  location character varying(10) NOT NULL, -- Pełna nazwa sali
  when_done timestamp without time zone NOT NULL, -- Kiedy zamknięto raport
  extra_info text, -- Informacje dodatkowe
  CONSTRAINT pk_report PRIMARY KEY (id),
  CONSTRAINT fk_report_location FOREIGN KEY (location)
      REFERENCES location (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE report
  OWNER TO l4_common;
COMMENT ON TABLE report
  IS 'Raport z ewidencji pojedynczej sali';
COMMENT ON COLUMN report.id IS 'ID raportu';
COMMENT ON COLUMN report.location IS 'Pełna nazwa sali';
COMMENT ON COLUMN report.when_done IS 'Kiedy zamknięto raport';
COMMENT ON COLUMN report.extra_info IS 'Informacje dodatkowe';


-- Index: fki_report_location

-- DROP INDEX fki_report_location;

CREATE INDEX fki_report_location
  ON report
  USING btree
  (location COLLATE pg_catalog."default");



-- Table: report_item

-- DROP TABLE report_item;

CREATE TABLE report_item
(
  id serial NOT NULL,
  id_report integer NOT NULL, -- ID powiązanego raportu
  id_asset character varying(30) NOT NULL, -- Nr ewidencyjny środka
  extra_info text, -- Informacje dodatkowe
  is_damaged boolean NOT NULL, -- Czy środek jest uszkodzony
  is_lost boolean NOT NULL, -- Czy środek jest zagubiony
  CONSTRAINT pk_report_item PRIMARY KEY (id),
  CONSTRAINT fk_report_items_assets FOREIGN KEY (id_asset)
      REFERENCES assets (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_report_items_report FOREIGN KEY (id_report)
      REFERENCES report (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE report_item
  OWNER TO l4_common;
COMMENT ON TABLE report_item
  IS 'Operacja ewidencji przedmiotu w ramach raportu';
COMMENT ON COLUMN report_item.id_report IS 'ID powiązanego raportu';
COMMENT ON COLUMN report_item.id_asset IS 'Nr ewidencyjny środka';
COMMENT ON COLUMN report_item.extra_info IS 'Informacje dodatkowe';
COMMENT ON COLUMN report_item.is_damaged IS 'Czy środek jest uszkodzony';
COMMENT ON COLUMN report_item.is_lost IS 'Czy środek jest zagubiony';



-- Table: users

-- DROP TABLE users;

CREATE TABLE users
(
  login character varying(256) NOT NULL,
  name character varying(30) NOT NULL, -- Imię
  surname character varying(30),
  pesel character(11) NOT NULL, -- PESEL
  birthdate date NOT NULL, -- Data urodzenia
  password character varying(50) NOT NULL, -- Hasło
  is_admin boolean NOT NULL DEFAULT false,
  CONSTRAINT pk_users PRIMARY KEY (login)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users
  OWNER TO l4_common;
GRANT ALL ON TABLE users TO l4_common;
GRANT INSERT ON TABLE users TO public;
COMMENT ON TABLE users
  IS 'Użytkownicy/pracownicy katedry';
COMMENT ON COLUMN users.name IS 'Imię';
COMMENT ON COLUMN users.pesel IS 'PESEL';
COMMENT ON COLUMN users.birthdate IS 'Data urodzenia';
COMMENT ON COLUMN users.password IS 'Hasło';

