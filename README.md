# QA Frontend Screenplay

Proyecto de automatización de pruebas E2E para frontend usando:

- Java
- Gradle
- Cucumber
- Serenity BDD
- Patrón Screenplay

El objetivo es validar flujos funcionales (por ejemplo, lectura y creación de Kudos) mediante pruebas automatizadas.

## Prerrequisitos

- Java 17 (o versión compatible con el proyecto)
- Git
- Docker y Docker Compose

## Dependencia externa para las pruebas

Antes de ejecutar los tests de este repositorio, debes tener disponible el MVP de soporte:

1. Clona el repositorio:

```bash
git clone https://github.com/ElyRiven/sofkianos-mvp
```

2. Entra al repositorio clonado y levanta los servicios:

```bash
cd sofkianos-mvp
docker compose up -d
```

Con esto ya puedes continuar con las pruebas y ejecución de este proyecto.

## Ejecución de pruebas

Desde la raíz de este proyecto (`qa-frontend-screenplay`):

### Linux / macOS

```bash
./gradlew clean test aggregate
```

### Windows (PowerShell)

```powershell
.\gradlew.bat clean test aggregate
```

## Ejecución por runner

Si quieres ejecutar específicamente el runner principal del proyecto (`TestRunnerKudos`):

### Linux / macOS

```bash
./gradlew clean test --tests com.frontendpom.runners.TestRunnerKudos aggregate
```

### Windows (PowerShell)

```powershell
.\gradlew.bat clean test --tests com.frontendpom.runners.TestRunnerKudos aggregate
```

## Configuración de URLs

Las URLs base usadas por las pruebas están definidas actualmente en:

- `src/test/java/com/frontendpom/util/Config.java`

Valores actuales:

- `BASE_URL = "http://localhost:5173"` (frontend)
- `API_BASE_URL = "http://localhost:8082/api/v1"` (API)

Si tu entorno corre en otros puertos o dominio, ajusta estos valores antes de ejecutar los tests.

## Reportes

Al finalizar, Serenity genera reportes HTML en:

- `target/site/serenity`

Puedes abrir `index.html` dentro de esa carpeta para revisar los resultados.
