/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

DROP INDEX idx_did_search_data;
ALTER TABLE did_entity
    DROP COLUMN scid;
