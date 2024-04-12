-- Löschen von Fremdschlüsselbeziehungen und Tabellen
--ALTER TABLE items DROP CONSTRAINT fk_subcategory_id;
--ALTER TABLE subcategories DROP CONSTRAINT fk_category_id;
DROP TABLE items;
DROP TABLE subcategories;
DROP TABLE categories;

-- Erstellen der Tabellen
CREATE TABLE categories (
    category_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE subcategories (
    subcategory_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category_id INT,
    CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

CREATE TABLE items (
    items_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    subcategory_id INT,
    CONSTRAINT fk_subcategory_id FOREIGN KEY (subcategory_id) REFERENCES subcategories(subcategory_id)
);

-- Einfügen von Daten
INSERT INTO categories (name) VALUES ('Kochen');
INSERT INTO categories (name) VALUES ('Haushalt');
INSERT INTO categories (name) VALUES ('Unterhaltung');

INSERT INTO subcategories (name, category_id) VALUES ('Obst/Gemüse', 1);
INSERT INTO subcategories (name, category_id) VALUES ('Brot/Gebäck', 1);
INSERT INTO subcategories (name, category_id) VALUES ('Milch/Käse', 1);
INSERT INTO subcategories (name, category_id) VALUES ('Fleisch/Fisch', 1);
INSERT INTO subcategories (name, category_id) VALUES ('Zutaten/Gewürze', 1);

INSERT INTO subcategories (name, category_id) VALUES ('Spiele', 3);
INSERT INTO subcategories (name, category_id) VALUES ('Bücher', 3);
INSERT INTO subcategories (name, category_id) VALUES ('Filme', 3);

INSERT INTO items (name, subcategory_id) VALUES ('Apfel', 1);
INSERT INTO items (name, subcategory_id) VALUES ('Banane', 1);
INSERT INTO items (name, subcategory_id) VALUES ('Tomate', 1);
INSERT INTO items (name, subcategory_id) VALUES ('Brot', 2);
INSERT INTO items (name, subcategory_id) VALUES ('Brötchen', 2);
INSERT INTO items (name, subcategory_id) VALUES ('Milch', 3);
INSERT INTO items (name, subcategory_id) VALUES ('Käse', 3);
INSERT INTO items (name, subcategory_id) VALUES ('Hähnchenbrust', 4);
INSERT INTO items (name, subcategory_id) VALUES ('Lachsfilet', 4);
INSERT INTO items (name, subcategory_id) VALUES ('Salz', 5);
INSERT INTO items (name, subcategory_id) VALUES ('Pfeffer', 5);
INSERT INTO items (name, subcategory_id) VALUES ('Monopoly', 6);
INSERT INTO items (name, subcategory_id) VALUES ('Schachbrett', 6);
INSERT INTO items (name, subcategory_id) VALUES ('Harry Potter', 7);
INSERT INTO items (name, subcategory_id) VALUES ('Der Herr der Ringe', 7);
INSERT INTO items (name, subcategory_id) VALUES ('Inception', 8);
INSERT INTO items (name, subcategory_id) VALUES ('Pulp Fiction', 8);
