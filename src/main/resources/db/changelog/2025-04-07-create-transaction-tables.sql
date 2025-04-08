CREATE TABLE limits (
    id UUID PRIMARY KEY,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    currency VARCHAR(3) NOT NULL,
    expense_category VARCHAR(10) NOT NULL
);

CREATE TABLE transactions (
    id UUID PRIMARY KEY,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    account_from VARCHAR(10) NOT NULL,
    account_to VARCHAR(10) NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    currency VARCHAR(3) NOT NULL,
    expense_category VARCHAR(10) NOT NULL,
    limit_id UUID REFERENCES limits (id),
    limit_exceeded BOOLEAN NOT NULL,
    amount_exceeded DECIMAL(15, 2)
);

CREATE TABLE exchange_rates (
    id UUID PRIMARY KEY,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    currency_from VARCHAR(3) NOT NULL,
    currency_to VARCHAR(3) NOT NULL,
    rate DECIMAL(15, 8) NOT NULL
);