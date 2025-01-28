<!--
SPDX-FileCopyrightText: 2025 Swiss Confederation

SPDX-License-Identifier: MIT
-->

![Public Beta banner](https://github.com/e-id-admin/eidch-public-beta/blob/main/assets/github-banner-publicbeta.jpg)

# Datastore Services and Libraries

The datastore services are the backbone of the swiss SSI inspired ecosystem.
This repository does contain the current implementation for the base registry. The data service is a readonly service.

The Base registry contains the ability to create and update DIDs required in the swiss ecosystem.

## Table of Contents

- [Overview](#Overview)
- [Installation](#installation)
- [Usage](#usage)
- [Contributions and feedback](#contributions-and-feedback)
- [License](#license)

## Overview

For a general overview of the public beta environment and its components, please check
the [Public Beta context](https://github.com/e-id-admin/eidch-public-beta).

A datastore service always includes 2 sub services:

1. The authoring service, which essentially provides all the write operations.  
   Those should only be available to specified authorized systems like the controller system provided by the swiss gov.
2. The data service, which provides all the protocol conform read operations.
   Therefore we do require a strict separation of write operations in the code, so while the data models are shared
   through
   the shared libraries services and repositories are not shared.

## Installation

> [!NOTE]
> Starting the service with the local profile does not connect the data and authoring services. It only serves to start this service locally.

To install docker please follow the instructions on the respective pages.

In order to start the service locally, run:

```shell
docker compose up
```

Then run:

```shell
./mvnw spring-boot:run -Dspring-boot.run.profiles=local -pl base-registry-data-service
```

The openapi definition can then be found [here](http://localhost:8190/swagger-ui.html)

## Usage

### Auth

The data service is always unprotected as all data on the registers should be readable by everyone.  
The authoring services do need protection, which is as of now handled by the infrastructure.  
We do recommend to add mTLS authentication to your infrastructure endpoints.

### Maven module overview

```
@startuml Maven Module overview

skinparam defaultTextAlignment center

[<<library>>\nshared] as baseRegistryDataShared
[<<library>>\ndid] as baseRegistryDataDidStore
[<<service>>\nbase registry data] as serviceBaseRegistryData


baseRegistryDataDidStore -u-> baseRegistryDataShared : use
serviceBaseRegistryData -u-> baseRegistryDataDidStore : use

@enduml
```

## Contributions and feedback

The code for this repository is developed privately and will be released after each sprint. The published code can therefore only be a snapshot of the current development and not a thoroughly tested version. However, we welcome any feedback on the code regarding both the implementation and security aspects. Please follow the guidelines for contributing found in [CONTRIBUTING.md](/CONTRIBUTING.md).

## License

This project is licensed under the terms of the MIT license. See the [LICENSE](/LICENSE) file for details.
