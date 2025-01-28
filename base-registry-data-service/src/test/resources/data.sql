/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

--  Good entry, not initialized 00000000-0000-0000-0000-000000000000
INSERT INTO datastore_entity (id, status)
VALUES ('00000000-0000-0000-0000-000000000000', 'SETUP');
--  Good entry, initialized did:tdw
INSERT INTO datastore_entity (id, status)
VALUES ('00000000-0000-0000-0000-000000000001', 'ACTIVE');
INSERT INTO did_entity (base_id, file_type,  read_uri, content)
VALUES ('00000000-0000-0000-0000-000000000001', 'DID_TDW', 'TEST_READ', 'dummy_json_log');
--  Good entry, initialized did:web
INSERT INTO datastore_entity (id, status)
VALUES ('00000000-0000-0000-0000-000000000002', 'ACTIVE');
INSERT INTO did_entity (base_id, file_type, read_uri, content)
VALUES ('00000000-0000-0000-0000-000000000002', 'DID_WEB', 'TEST_READ', '{"json":"code"}');
--  Good entry, initialized did:tdw and did:web
INSERT INTO datastore_entity (id, status)
VALUES ('00000000-0000-0000-0000-000000000003', 'ACTIVE');
INSERT INTO did_entity (base_id, file_type, read_uri, content)
VALUES ('00000000-0000-0000-0000-000000000003', 'DID_TDW', 'TEST_READ', 'dummy_json_log');
INSERT INTO did_entity (base_id, file_type, read_uri, content)
VALUES ('00000000-0000-0000-0000-000000000003', 'DID_WEB', 'TEST_READ', '{"json":"code"}');

--  Disabled entry
INSERT INTO datastore_entity (id, status)
VALUES ('00000000-0000-0000-0000-000000000004', 'DISABLED');

--  Deactivated entry
INSERT INTO datastore_entity (id, status)
VALUES ('00000000-0000-0000-0000-000000000005', 'DEACTIVATED');
