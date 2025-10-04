


CREATE TYPE fee_level AS ENUM ('ECONOMIQUE', 'STANDARD', 'RAPIDE');

CREATE TYPE tx_status AS ENUM ('PENDING', 'CONFIRMED', 'REJECTED');

CREATE TYPE crypto_type AS ENUM ('BITCOIN', 'ETHEREUM');


CREATE TABLE wallets (
    id UUID PRIMARY KEY,
    address VARCHAR(50) NOT NULL UNIQUE,
    type crypto_type NOT NULL,
    owner VARCHAR(100) NOT NULL,
    balance NUMERIC(18, 8) DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE transactions (
    id UUID PRIMARY KEY,
    wallet_id UUID NOT NULL REFERENCES wallets(id) ON DELETE CASCADE,
    from_address VARCHAR(50) NOT NULL,
    to_address VARCHAR(50) NOT NULL,
    amount NUMERIC(18, 8) NOT NULL CHECK (amount > 0),
    fee NUMERIC(18, 8) NOT NULL CHECK (fee >= 0),
    fee_level fee_level NOT NULL,
    status tx_status DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE mempool (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    transaction_id UUID NOT NULL REFERENCES transactions(id) ON DELETE CASCADE,
    fee_level fee_level NOT NULL,
    position INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



INSERT INTO wallets (id, address, type, owner, balance)
VALUES
    (gen_random_uuid(), '1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa', 'BITCOIN', 'Alice', 1.5),
    (gen_random_uuid(), '0x742d35Cc6634C0532925a3b844Bc454e4438f44e', 'ETHEREUM', 'Bob', 10);

INSERT INTO transactions (id, wallet_id, from_address, to_address, amount, fee, fee_level, status)
SELECT
    gen_random_uuid(),
    w.id,
    w.address,
    CASE WHEN w.type='BITCOIN' THEN '1BoatSLRHtKNngkdXEeobR76b53LETtpyT'
         ELSE '0x5Aeda56215b167893e80B4fE645BA6d5Bab767DE' END,
    5, 0.0001, 'STANDARD', 'PENDING'
FROM wallets w
    LIMIT 5;
