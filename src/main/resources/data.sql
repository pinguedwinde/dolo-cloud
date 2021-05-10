DELETE FROM ingredients_ref;
DELETE FROM dolos;
DELETE FROM dolo_orders;
DELETE FROM ingredients;

INSERT INTO ingredients (id, name, type) VALUES ('RAMI', 'Raan Miisga', 'SOUR');
INSERT INTO ingredients (id, name, type) VALUES ('RMPA', 'Raan Miisg Paale', 'SOUR');
INSERT INTO ingredients (id, name, type) VALUES ('RMMA', 'Raan Miisg Maasga', 'SOUR');
INSERT INTO ingredients (id, name, type) VALUES ('RANO', 'Raan Noodo', 'SUGAR');
INSERT INTO ingredients (id, name, type) VALUES ('RNPA', 'Raan Nood Paale', 'SUGAR');
INSERT INTO ingredients (id, name, type) VALUES ('RNMA', 'Raan Nood Maasga', 'SUGAR');
INSERT INTO ingredients (id, name, type) VALUES ('RATO', 'Raan Toodo', 'ALCOHOLIC');
INSERT INTO ingredients (id, name, type) VALUES ('RTPA', 'Raan Tood Paale', 'ALCOHOLIC');
INSERT INTO ingredients (id, name, type) VALUES ('RTMA', 'Raan Tood Maasga', 'ALCOHOLIC');
INSERT INTO ingredients (id, name, type) VALUES ('RAKO', 'Raan Koom', 'NON_ALCOHOLIC');
INSERT INTO ingredients (id, name, type) VALUES ('RKPA', 'Raan Koom Paale', 'NON_ALCOHOLIC');
INSERT INTO ingredients (id, name, type) VALUES ('RKMA', 'Raan Koom Maasga', 'NON_ALCOHOLIC');