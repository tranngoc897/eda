postgres.execute("CREATE TABLE IF NOT EXISTS players"
                + "(id bigint PRIMARY KEY, name varchar(50) NOT NULL, created timestamp NOT NULL)");

public void insertPlayer(Integer playerId, String name) {
        postgres.update("INSERT INTO players VALUES (?, ?, ?) ON CONFLICT DO NOTHING",
                playerId, name, new Timestamp(System.currentTimeMillis()));
}