/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

package ch.admin.bit.eid.base_registry.data_service.controller;

import ch.admin.bit.eid.datastore.did.authoring.model.entity.DidEntity;
import ch.admin.bit.eid.datastore.did.authoring.model.enums.DidTypeEnum;
import ch.admin.bit.eid.datastore.did.authoring.service.DidEntityService;
import ch.admin.bit.eid.datastore.shared.exceptions.ApiError;
import ch.admin.bit.eid.datastore.shared.model.entity.DatastoreEntity;
import ch.admin.bit.eid.datastore.shared.service.DataStoreEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/did")
@AllArgsConstructor
@Tag(name = "DID Controller", description = "Retrieves DID entries from the datastore.")
public class DidController {

    private final DataStoreEntityService dataStoreEntityService;
    private final DidEntityService dataStoreFileEntityService;

    @GetMapping(value = "/{id}/did.json", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Get a specific file stored for a entry in the datastore.",
            description = "Get a specific file stored for a entry in the datastore."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "425", description = "Too Early, Resource cannot be shown.",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public Object getDidWebFile(
            @Valid @PathVariable UUID id
    ) {
        DatastoreEntity entity = this.dataStoreEntityService.getDataStoreEntity(id);
        this.dataStoreEntityService.checkCanShow(entity);
        DidEntity file = this.dataStoreFileEntityService.getDataStoreFileEntity(
                id, DidTypeEnum.DID_WEB
        );
        return file.getContent();
    }

    @GetMapping(value = "/{id}/did.jsonl", produces = "application/jsonl+json")
    @Operation(
            summary = "Get a specific file stored for a entry in the datastore.",
            description = "Get a specific file stored for a entry in the datastore."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "425", description = "Too Early, Resource cannot be shown.",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public Object getDidTdwFile(
            @Valid @PathVariable UUID id
    ) {
        DatastoreEntity entity = this.dataStoreEntityService.getDataStoreEntity(id);
        this.dataStoreEntityService.checkCanShow(entity);
        DidEntity file = this.dataStoreFileEntityService.getDataStoreFileEntity(
                id, DidTypeEnum.DID_TDW
        );
        return file.getContent();
    }
}
