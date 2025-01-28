/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

CREATE TABLE datastore_entity
(
    id     uuid NOT NULL,
    status VARCHAR(20),
    PRIMARY KEY (id)
);
