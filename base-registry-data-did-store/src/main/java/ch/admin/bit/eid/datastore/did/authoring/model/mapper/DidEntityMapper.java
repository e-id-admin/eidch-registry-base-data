/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

package ch.admin.bit.eid.datastore.did.authoring.model.mapper;

import ch.admin.bit.eid.datastore.did.authoring.model.entity.DidEntity;
import ch.admin.bit.eid.datastore.shared.model.dto.DidEntityResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DidEntityMapper {


    public static DidEntityResponseDto entityToDidEntityResponseDto(DidEntity entity) {

        return DidEntityResponseDto.builder()
                .readUri(entity.getReadUri())
                .isConfigured(entity.getContent() != null)
                .build();
    }
}
