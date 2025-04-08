CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
INSERT INTO limits (id, created_at, amount, currency, expense_category)
VALUES (uuid_generate_v4(), now(), 1000, 'USD', 'PRODUCT'),
       (uuid_generate_v4(), now(), 1000, 'USD', 'SERVICE');