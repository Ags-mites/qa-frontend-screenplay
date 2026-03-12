# QA Frontend Screenplay - Plan de Refactorización

## Estado del Proyecto

Proyecto de automatización de pruebas UI/API usando **Screenplay Pattern** con Serenity BDD y Cucumber.

---

## 🔴 Problemas Críticos (Alta Prioridad)

### 1. Hooks Duplicados
- **Ubicación**: `CommonHooks.java` y `stepdefinitions/hooks/Hooks.java`
- **Problema**: Dos archivos que inicializan el stage de la misma forma
- **Acción**: Eliminar uno de los dos

### 2. Lógica Duplicada - Conversión Title Case
- **Ubicación**: 
  - `PostKudo.java:34-36`
  - `ReadKudoStepDefinitions.java:66`
- **Problema**: Misma lógica de conversión de categoría repetida
- **Acción**: Extraer a método utilitario en clase `CategoryUtils`

### 3. Magic Numbers - Timeouts
- **Ubicación**: Múltiples archivos
  - `FilterKudos.java:25` → 10 segundos
  - `SearchRecipient.java:27` → 10 segundos
  - `KudoTimestampCheck.java:19` → 10 segundos
  - `KudoAuthorVisible.java:19` → 10 segundos
  - `KudoDisplayedMessage.java:19` → 10 segundos
  - `SendKudoStepDefinitions.java:35` → 20 segundos
  - `SendKudoStepDefinitions.java:80` → 15 segundos
- **Problema**: Tiempos hardcodeados dispersos
- **Acción**: Crear constantes en clase `Config` o `serenity.properties`

### 4. Questions con Estructura Duplicada
- **Ubicación**: 
  - `KudoTimestampCheck.java`
  - `KudoAuthorVisible.java`
  - `KudoDisplayedMessage.java`
- **Problema**: Tres questions con mismo patrón (wait + get text)
- **Acción**: Crear clase base abstracta o método factory

### 5. Page Objects Inconsistentes
- **Ubicación**: `ui/KudosListPage` (interfaz) vs `ui/KudosCreatePage` (clase)
- **Problema**: Mezcla de tipos inconsistente
- **Acción**: Estandarizar ambos como clases

---

## 🟠 Malas Prácticas (Media Prioridad)

### 6. Localizadores Frágiles (XPath complejos)
- **Ubicación**: `KudosCreatePage.java` líneas 8-26
- **Problema**: XPath con múltiples contains y translate, difícil de mantener
- **Acción**: Simplificar usando data-testid o IDs estables

### 7. Sin Manejo de Excepciones
- **Ubicación**: 
  - `SlideRight.java`
  - `SuccessMessage.java`
  - `FormCleaned.java`
- **Problema**: Puede fallar silenciosamente si elemento no existe
- **Acción**: Agregar waits explícitos y manejo de excepciones

### 8. Configuración Hardcodeada
- **Ubicación**: `Config.java`
- **Problema**: URLs y actor name en código
- **Acción**: Mover a `serenity.properties` o variables de entorno

### 9. Questions Sin Wait
- **Ubicación**: 
  - `SuccessMessage.java`
  - `FormCleaned.java`
- **Problema**: No esperan elemento antes de obtener valor
- **Acción**: Agregar `WaitUntil` antes de `resolveFor`

### 10. JavaScript Invasivo
- **Ubicación**: `SendKudoStepDefinitions.java:94-107`
- **Problema**: Uso de JavascriptExecutor para settear textarea
- **Acción**: Investigar alternativa con Actions o verificar si UI tiene bug

---

## 🟡 Code Smells (Baja Prioridad)

### 11. Import No Usado
- **Ubicación**: `SearchRecipient.java:3`
- **Problema**: Import sin usar

### 12. Constante No Usada
- **Ubicación**: `Config.java:5` - `API_BASE_URL`
- **Problema**: Definida pero nunca utilizada

### 13. Línea Innecesaria
- **Ubicación**: `SendKudoStepDefinitions.java:37`
- **Problema**: `OnStage.theActorInTheSpotlight()` sin asignación

### 14. XPath Frágil
- **Ubicación**: `SlideRight.java:25`
- **Problema**: Depende de clase CSS `cursor-pointer`

### 15. Mezcla de Concerns
- **Ubicación**: `PostKudo.java`
- **Problema**: Task hace request + validación de response
- **Acción**: Separar en task (request) y question (validation)

---

## 📊 Métricas

| Métrica | Valor |
|---------|-------|
| Líneas duplicadas | ~30-40 |
| Archivos redundantes | 1 |
| Code smells menores | 4-5 |
| Total archivos Java | 18 |

---

## ✅ Checklist de Corrección

- [ ] Eliminar Hooks duplicado
- [ ] Crear `CategoryUtils.convertToTitleCase()`
- [ ] Agregar constantes de timeout a `Config`
- [ ] Crear `BaseQuestion` abstracto
- [ ] Estandarizar Page Objects como clases
- [ ] Simplificar localizadores XPath
- [ ] Mover configuración a properties
- [ ] Agregar waits a Questions
- [ ] Eliminar imports no usados
- [ ] Remover constantes no usadas
