/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

package ch.admin.bit.eid.datastore.did.authoring.service;

import ch.admin.bit.eid.datastore.did.authoring.config.ConfigProperties;
import ch.admin.bit.eid.datastore.did.authoring.model.entity.DidEntity;
import ch.admin.bit.eid.datastore.did.authoring.model.enums.DidTypeEnum;
import ch.admin.bit.eid.datastore.did.authoring.model.mapper.DidEntityMapper;
import ch.admin.bit.eid.datastore.did.authoring.repository.DidEntityRepository;
import ch.admin.bit.eid.datastore.shared.exceptions.ResourceNotFoundException;
import ch.admin.bit.eid.datastore.shared.exceptions.ResourceNotReadyException;
import ch.admin.bit.eid.datastore.shared.model.dto.DidEntityResponseDto;
import ch.admin.bit.eid.datastore.shared.model.entity.DatastoreEntity;
import ch.admin.bit.eid.datastore.shared.service.DataStoreEntityService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class DidEntityService {


    private final DataStoreEntityService dataStoreEntityService;
    private final DidEntityRepository dataStoreFileEntityRepository;
    private final ConfigProperties configProperties;


    public DidEntity getDataStoreFileEntity(UUID id, DidTypeEnum fileType) {
        return this.dataStoreFileEntityRepository.findByBase_IdAndFileType(id, fileType)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString(), DatastoreEntity.class));
    }

    public DidEntity.DidEntityBuilder buildEmptyDidEntity(
            DatastoreEntity base,
            DidTypeEnum fileType
    ) {
        String readURI;
        switch (fileType) {
            case DID_WEB:
                readURI = String.format("did:web:%s:api:v1:did:%s",
                        this.configProperties.getDidRoute(),
                        base.getId()
                );
                break;
            case DID_TDW:
                readURI = String.format("%s/api/v1/did/%s",
                        this.configProperties.getHttpRoute(),
                        base.getId()
                );
                break;
            default:
                throw new NotImplementedException(
                        String.format("Case %s is not implemented for identifier mapping.",
                                fileType
                        ));
        }
        return DidEntity.builder()
                .fileType(fileType)
                .readUri(readURI)
                .base(base);
    }


    public Map<String, DidEntityResponseDto> getAllDataStoreFileEntity(UUID id) {

        DatastoreEntity base = this.dataStoreEntityService.getDataStoreEntity(id);

        List<DidEntity> presentFiles = this.dataStoreFileEntityRepository.findByBase_Id(id);

        List<DidEntity> result = new ArrayList<>(presentFiles);

        for (DidTypeEnum fileType : DidTypeEnum.values()) {
            if (presentFiles.stream().anyMatch(o -> o.getFileType() == fileType))
                continue;
            result.add(this.buildEmptyDidEntity(base, fileType).build());
        }
        HashMap<String, DidEntityResponseDto> files = new HashMap<>();
        result.forEach(file -> files.put(
                file.getFileType().name(),
                DidEntityMapper.entityToDidEntityResponseDto(file))
        );
        return files;
    }


    @Transactional
    public void saveDataStoreFileEntity(UUID datastoreEntityId, DidEntity content) throws ResourceNotReadyException {
        DatastoreEntity base = this.dataStoreEntityService.getDataStoreEntity(datastoreEntityId);

        this.dataStoreEntityService.checkCanEdit(base);

        content.setBase(base);

        Optional<DidEntity> existing = this.dataStoreFileEntityRepository.findByBase_IdAndFileType(
                base.getId(),
                content.getFileType()
        );
        existing.ifPresent(datastoreFileEntity ->
                content.setId(datastoreFileEntity.getId())
        );
        this.dataStoreFileEntityRepository.save(content);


        this.dataStoreEntityService.setActive(base);
    }


}
