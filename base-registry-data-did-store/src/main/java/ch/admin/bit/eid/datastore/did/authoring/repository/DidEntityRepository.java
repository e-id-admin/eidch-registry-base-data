/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

package ch.admin.bit.eid.datastore.did.authoring.repository;

import ch.admin.bit.eid.datastore.did.authoring.model.entity.DidEntity;
import ch.admin.bit.eid.datastore.did.authoring.model.enums.DidTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DidEntityRepository extends JpaRepository<DidEntity, Long> {
    List<DidEntity> findByBase_Id(UUID baseId);

    Optional<DidEntity> findByBase_IdAndFileType(UUID baseId, DidTypeEnum fileType);
}
