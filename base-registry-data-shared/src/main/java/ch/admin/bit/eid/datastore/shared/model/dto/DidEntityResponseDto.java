/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

package ch.admin.bit.eid.datastore.shared.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DidEntityResponseDto {

    private String readUri;
    private Boolean isConfigured;
}
