CREATE TABLE subscription (
    id SERIAL PRIMARY KEY ,
    user_id INTEGER NOT NULL,
    subscription VARCHAR NOT NULL,
    subscribed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT unique_user_subscription UNIQUE (user_id, subscription)
)