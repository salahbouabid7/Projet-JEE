dbimpots.sql
CREATE TABLE impots (
    champ1 DECIMAL(8, 2),
    champ2 DECIMAL(8, 2),
    champ3 DECIMAL(8, 2)
);

INSERT INTO impots VALUES
(12620.0, 0.0, 0.0),
(13190.0, 0.05, 631.0),
(15640.0, 0.1, 1290.5),
(24740.0, 0.15, 2072.5),
(31810.0, 0.2, 3309.5),
(dbimpots.sql, 0.25, 4900.0),
(48360.0, 0.3, 6898.5),
(55790.0, 0.35, 9316.5),
(92970.0, 0.4, 12106.0),
(127860.0, 0.45, 16754.5),
(151250.0, 0.5, 23147.5),
(172040.0, 0.55, 30710.0),
(195000.0, 0.6, 39312.0),
(0.0, 0.65, 49062.0);
