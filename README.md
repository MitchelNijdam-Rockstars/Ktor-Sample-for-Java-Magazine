# Ktor Sample for Java Magazine

This project contains working examples from the article 

>Ktor: de gouden combinatie van Kotlin coroutines en DSL voor server-side applicaties


in the Dutch Java Magazine by Mitchel Nijdam.

## Creation
This project is generated by IntelliJ IDEA Ultimate using the Ktor plugin, but can also be created with https://start.ktor.io/

## Build
`gradle clean build`

## Run
`gradle run`

Or run via your favorite IDE (IntelliJ IDEA has good Ktor support).

## Contents

| **File**         | **Contents**                                                                                           |
|------------------|--------------------------------------------------------------------------------------------------------|
| `Application.kt` | Main function that starts Netty Engine. Module that configures Routing and Security example endpoints. |
| `Routing.kt`     | Contains a simple endpoint `/` that returns some text.                                                 |
| `Security.kt`    | Endpoint secured by Basic Auth.                                                                        |
