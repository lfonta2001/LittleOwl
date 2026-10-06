CREATE TABLE IF NOT EXISTS users (
    id              UUID        PRIMARY KEY DEFAULT uuidv7(),
    username        TEXT        NOT NULL,
    global_name     TEXT,
    avatar_hash     TEXT,
    created_at      TIMESTAMPTZ NOT NULL DEFAULT now(),
    last_seen_at    TIMESTAMPTZ
);

CREATE TABLE IF NOT EXISTS sessions (
    id                  UUID        PRIMARY KEY DEFAULT uuidv7(),
    user_id             UUID        NOT NULL REFERENCES users (id) ON DELETE CASCADE,

    refresh_token_hash  TEXT        NOT NULL UNIQUE,
    user_agent          TEXT,
    ip_address          INET,
    created_at          TIMESTAMPTZ NOT NULL DEFAULT now(),
    last_used_at        TIMESTAMPTZ NOT NULL DEFAULT now(),
    expires_at          TIMESTAMPTZ NOT NULL,

    revoked_at          TIMESTAMPTZ,

    CONSTRAINT sessions_expiry_after_creation CHECK (expires_at > created_at)
);

CREATE INDEX idx_sessions_user_id    ON sessions (user_id);
CREATE INDEX idx_sessions_expires_at ON sessions (expires_at);