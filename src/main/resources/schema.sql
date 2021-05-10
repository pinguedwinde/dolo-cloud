CREATE TABLE IF NOT EXISTS dolo_orders (
    id IDENTITY,
    delivery_name VARCHAR(50) NOT NULL,
    delivery_street VARCHAR(50) NOT NULL,
    delivery_city VARCHAR(50) NOT NULL,
    delivery_state VARCHAR(2) NOT NULL,
    delivery_zip VARCHAR(10) NOT NULL,
    cc_number VARCHAR(16) NOT NULL,
    cc_expiration VARCHAR(5) NOT NULL,
    cc_cvv VARCHAR(3) NOT NULL,
    placed_at TIMESTAMP NOT NULL
);
CREATE TABLE IF NOT EXISTS dolos (
    id IDENTITY,
    name VARCHAR(50) NOT NULL,
    dolo_order BIGINT NOT NULL,
    dolo_order_key BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL
);
CREATE TABLE IF NOT EXISTS ingredients_ref (
    ingredient VARCHAR(4) NOT NULL,
    dolo BIGINT NOT NULL,
    dolo_key BIGINT NOT NULL
);
CREATE TABLE IF NOT EXISTS ingredients (
    id VARCHAR(4) NOT NULL,
    name VARCHAR(25) NOT NULL,
    type VARCHAR(25) NOT NULL
);
alter TABLE dolos
    add foreign key (dolo_order) references dolo_orders(id);
alter TABLE ingredients_ref
    add foreign key (ingredient) references ingredients(id);