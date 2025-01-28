/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

package ch.admin.bit.eid.datastore.did.authoring.model.entity;

import ch.admin.bit.eid.datastore.did.authoring.model.enums.DidTypeEnum;
import ch.admin.bit.eid.datastore.shared.model.entity.DatastoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "did_entity")
public class DidEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "base_id",
            referencedColumnName = "id"
    )
    private DatastoreEntity base;

    @Column(name = "file_type")
    @Enumerated(EnumType.STRING)
    private DidTypeEnum fileType;

    @Column(name = "content")
    private String content;

    @Column(name = "read_uri")
    private String readUri;
}
