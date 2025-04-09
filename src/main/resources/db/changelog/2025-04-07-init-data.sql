CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO limits (id, created_at, amount, currency, expense_category)
VALUES (uuid_generate_v4(), now(), 1000, 'USD', 'PRODUCT'),
       (uuid_generate_v4(), now(), 1000, 'USD', 'SERVICE');

INSERT INTO exchange_rates (id, created_at, currency_from, currency_to, rate)
VALUES (uuid_generate_v4(), now(),  'USD', 'USD', 1),
       (uuid_generate_v4(), now(),  'KZT', 'USD', 0.00193268),
       (uuid_generate_v4(), now(),  'RUB', 'USD', 0.01164646),
       (uuid_generate_v4(), now(),  'EUR', 'USD', 1.09146500);