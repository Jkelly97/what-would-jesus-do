## Data model data definition language (DDL)

```sqlite
CREATE TABLE IF NOT EXISTS `User`
(
    `user_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `oauth`   TEXT
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_User_oauth` ON `User` (`oauth`);

CREATE TABLE IF NOT EXISTS `Passage`
(
    `passage_id`     INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `book`           TEXT,
    `chapter`        INTEGER                           NOT NULL,
    `starting_verse` INTEGER                           NOT NULL,
    `ending_verse`   INTEGER                           NOT NULL,
    `user_id`        INTEGER                           NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS `index_Passage_book_chapter_starting_verse_ending_verse` ON `Passage` (`book`, `chapter`, `starting_verse`, `ending_verse`);

CREATE INDEX IF NOT EXISTS `index_Passage_passage_id` ON `Passage` (`passage_id`);

CREATE INDEX IF NOT EXISTS `index_Passage_user_id` ON `Passage` (`user_id`);
```

[`ddl.sql`](sql/ddl.sql)