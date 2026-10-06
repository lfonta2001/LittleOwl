BEGIN;

CREATE OR REPLACE FUNCTION set_updated_at()
RETURNS trigger
LANGUAGE plpgsql AS $$
BEGIN
    NEW.updated_at = now();
    RETURN NEW;
END;
$$;

CREATE TABLE IF NOT EXISTS users (
    user_id     BIGINT  PRIMARY KEY,
    user_name   TEXT    NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS guilds (
    guild_id    BIGINT      PRIMARY KEY,
    owner_id    BIGINT      NOT NULL,
    guild_name  TEXT        NOT NULL,
    bot_present BOOLEAN     NOT NULL DEFAULT TRUE,
    added_at    TIMESTAMPTZ NOT NULL DEFAULT now(),
    removed_at  TIMESTAMPTZ,

    CONSTRAINT guild_owner_fk
        FOREIGN KEY (owner_id) REFERENCES users (user_id) ON DELETE RESTRICT
);

CREATE INDEX IF NOT EXISTS idx_guilds_owner_id ON guilds (owner_id);
CREATE INDEX IF NOT EXISTS idx_guilds_removed_at
    ON guilds (removed_at) WHERE removed_at IS NOT NULL;

CREATE TABLE IF NOT EXISTS guild_users (
    guild_id    BIGINT      NOT NULL,
    user_id     BIGINT      NOT NULL,
    joined_at   TIMESTAMPTZ NOT NULL DEFAULT now(),
    is_admin    BOOLEAN     NOT NULL DEFAULT FALSE,

    PRIMARY KEY (guild_id, user_id),
    FOREIGN KEY (guild_id) REFERENCES guilds (guild_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id)  REFERENCES users  (user_id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_guild_users_user_id ON guild_users (user_id);
CREATE INDEX IF NOT EXISTS idx_guild_users_admins
    ON guild_users (guild_id) WHERE is_admin;

CREATE TABLE IF NOT EXISTS guild_settings (
    guild_id            BIGINT      PRIMARY KEY,
    point_system_active BOOLEAN     NOT NULL DEFAULT FALSE,
    bot_language        TEXT        NOT NULL DEFAULT 'es_ES',
    default_role_id     BIGINT,
    default_channel_id  BIGINT      NOT NULL,
    mod_channel_id      BIGINT,
    alert_channel_id    BIGINT,
    updated_at          TIMESTAMPTZ NOT NULL DEFAULT now(),

    FOREIGN KEY (guild_id) REFERENCES guilds (guild_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS guild_spam_settings (
    guild_id            BIGINT      PRIMARY KEY,
    spam_checker_active BOOLEAN     NOT NULL DEFAULT FALSE,
    spam_message_limit  INTEGER CHECK (spam_message_limit IS NULL OR spam_message_limit > 0),
    spam_time_limit_ms  INTEGER CHECK (spam_time_limit_ms IS NULL OR spam_time_limit_ms > 0),
    updated_at          TIMESTAMPTZ NOT NULL DEFAULT now(),

    FOREIGN KEY (guild_id) REFERENCES guilds (guild_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS guild_greetings_settings (
    guild_id            BIGINT      PRIMARY KEY,
    welcome_enabled     BOOLEAN     NOT NULL DEFAULT FALSE,
    welcome_channel_id  BIGINT,
    welcome_message     TEXT,
    goodbye_enabled     BOOLEAN     NOT NULL DEAFULT FALSE,
    goodbye_channel_id  BIGINT,
    goodbye_message     TEXT,
    updated_at          TIMESTAMPTZ NOT NULL DEFAULT now(),

    FOREIGN KEY (guild_id) REFERENCES guilds (guild_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS guild_command_config (
    guild_id            BIGINT      NOT NULL,
    command_id          TEXT        NOT NULL,
    active              BOOLEAN     NOT NULL DEFAULT TRUE,
    permissions_needed  BIGINT,
    cooldown            INTEGER     CHECK (cooldown          IS NULL OR cooldown          >= 0),
    timeout_per_abuse   INTEGER     CHECK (timeout_per_abuse IS NULL OR timeout_per_abuse >= 0),
    abuse_limit         INTEGER     CHECK (abuse_limit       IS NULL OR abuse_limit       >  0),

    PRIMARY KEY (guild_id, command_id),
    FOREIGN KEY (guild_id) REFERENCES guilds (guild_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS songs (
    id              INTEGER     GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name            TEXT        NOT NULL,
    duration        INTEGER     NOT NULL CHECK (duration >= 0),
    yt_link         TEXT,
    spotify_link    TEXT,
    apple_link      TEXT,
    soundcloud_link TEXT
);

CREATE UNIQUE INDEX IF NOT EXISTS uq_songs_yt_link          ON songs (yt_link)          WHERE yt_link           IS NOT NULL;
CREATE UNIQUE INDEX IF NOT EXISTS uq_songs_spotify_link     ON songs (spotify_link)     WHERE spotify_link      IS NOT NULL;
CREATE UNIQUE INDEX IF NOT EXISTS uq_songs_apple_link       ON songs (apple_link)       WHERE apple_link        IS NOT NULL;
CREATE UNIQUE INDEX IF NOT EXISTS uq_songs_soundcloud_link  ON songs (soundcloud_link)  WHERE soundcloud_link   IS NOT NULL;

CREATE TABLE IF NOT EXISTS playlists (
    id          INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    guild_id    BIGINT      NOT NULL,
    name        TEXT        NOT NULL,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT now(),

    FOREIGN KEY (guild_id) REFERENCES guilds (guild_id) ON DELETE CASCADE,
    CONSTRAINT uq_playlists_guild_name UNIQUE (guild_id, name)
);

CREATE TABLE IF NOT EXISTS playlist_songs (
    playlist_id INTEGER NOT NULL,
    song_id     INTEGER NOT NULL,
    position    INTEGER NOT NULL CHECK (position >= 0),

    PRIMARY KEY (playlist_id, song_id),
    FOREIGN KEY (playlist_id) REFERENCES playlists (id) ON DELETE CASCADE,
    FOREIGN KEY (song_id)     REFERENCES songs     (id) ON DELETE RESTRICT,

    CONSTRAINT uq_playlist_song_position
        UNIQUE (playlist_id, position) DEFERRABLE INITIALLY DEFERRED
);

CREATE INDEX IF NOT EXISTS idx_playlist_songs_song_id ON playlist_songs (song_id);

CREATE TABLE IF NOT EXISTS music_queue (
    id          INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    guild_id    BIGINT      NOT NULL,
    position    INTEGER     NOT NULL CHECK (position >= 0),
    song_id     INTEGER     NOT NULL,
    added_by_id BIGINT      NOT NULL,
    added_at    TIMESTAMPTZ NOT NULL DEFAULT now(),

    FOREIGN KEY (guild_id)      REFERENCES guilds (guild_id) ON DELETE CASCADE,
    FOREIGN KEY (song_id)       REFERENCES songs  (id)       ON DELETE RESTRICT,
    FOREIGN KEY (added_by_id)   REFERENCES users  (user_id)  ON DELETE RESTRICT,

    CONSTRAINT uq_music_queue_position
        UNIQUE (guild_id, position) DEFERRABLE INITIALLY DEFERRED
);

CREATE INDEX IF NOT EXISTS idx_music_queue_song_id      ON music_queue (song_id);
CREATE INDEX IF NOT EXISTS idx_music_queue_added_by_id  ON music_queue (added_by_id);

CREATE TABLE IF NOT EXISTS guild_command_usage (
    guild_id                BIGINT      NOT NULL,
    command                 TEXT        NOT NULL,
    used                    INTEGER     NOT NULL DEFAULT 0 CHECK (used >= 0),
    used_at_first_of_month  INTEGER     NOT NULL DEFAULT 0 CHECK (used_at_first_of_mont >= 0 AND used_at_first_of_mont <= used),
    last_used               TIMESTAMPTZ NOT NULL,
    last_used_by_id         BIGINT      NOT NULL,

    PRIMARY KEY (guild_id, command),
    FOREIGN KEY (guild_id) REFERENCES guilds (guild_id) ON DELETE CASCADE,
    FOREIGN KEY (last_user_by_id) REFERENCES users (user_id) ON DELETE RESTRICT
);

CREATE INDEX IF NOT EXISTS idx_guild_command_usage_last_used    ON guild_command_usage (last_used);
CREATE INDEX IF NOT EXISTS idx_guild_command_usage_last_used_by ON guild_command_usage (last_used_by_id);

DO $$
DECLARE
   t text;
BEGIN
    FOREACH t IN ARRAY ARRAY[
            'guild_settings',
            'guild_spam_settings',
            'guild_greetings_settings',
            'guild_music_settings'
    ]
    LOOP
        EXECUTE format('DROP TRIGGER IF EXISTS trg_%I_updated_at ON %I', t, t);
        EXECUTE format(
                'CREATE TRIGGER trg_%I_updated_at BEFORE UPDATE ON %I' ||
                'FOR EACH ROW EXECUTE FUNCTION set_updated_at()', t, t);
    END LOOP;
END;
$$;

COMMIT;
