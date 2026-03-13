# QA Frontend Screenplay

Proyecto de automatización de pruebas E2E para frontend usando el patrón **Screenplay** con **Serenity BDD** y **Cucumber**.

## Tecnologías

- Java 17
- Gradle
- Serenity BDD
- Cucumber
- JUnit 5

## Prerrequisitos

- JDK 17 instalado
- Git
- Docker y Docker Compose

## Preparación del entorno

Antes de ejecutar las pruebas, necesitas levantar el entorno del MVP:

1. Clona el repositorio del MVP:

```bash
git clone https://github.com/ElyRiven/sofkianos-mvp
```

2. Levanta los servicios:

```bash
cd sofkianos-mvp
docker compose up -d
```

## Ejecución de pruebas

Desde la raíz del proyecto:

### Linux / macOS

```bash
./gradlew clean test aggregate
```

### Windows (PowerShell)

```powershell
.\gradlew.bat clean test aggregate
```

## Ver Reporte de Pruebas

Una vez finalizada la ejecución, el reporte detallado se genera en la ruta:

- **Reporte principal:** `target/site/serenity/index.html`
- **Resumen:** `target/site/serenity/serenity-summary.html`


### Ejecutar un runner específico

```bash
./gradlew clean test --tests com.frontendpom.runners.TestRunnerKudos aggregate
```

## Configuración

Las URLs base están configuradas en:

- `src/test/java/com/frontendpom/util/Config.java`

| Variable | Valor por defecto |
|----------|-------------------|
| BASE_URL | http://localhost:5173 |
| API_BASE_URL | http://localhost:8082/api/v1 |

## Ver reporte

Luego de la ejecución, abre el reporte HTML en:

- `target/site/serenity/index.html`
