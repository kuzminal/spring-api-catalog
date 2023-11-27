INSERT INTO currency(id, code, decimal_places, description, enabled, created_on, updated_on)
VALUES (nextval('currency_seq'), 'USD', 2, 'Dollar', true, now(), now());