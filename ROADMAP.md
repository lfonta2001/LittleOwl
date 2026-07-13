# The Owlery Bot - Development Roadmap

## Fase 1 — Infraestructura Base

**Objetivo:** terminar el esqueleto principal del framework del bot.

### Tareas

* [x] ISlashCommand
* [x] CommandDescriptor
* [x] CommandService
* [x] Registro automático de comandos mediante reflection
* [x] Registro automático de slash commands en Discord
* [x] SlashCommandListener
* [x] Ejecución básica de comandos desde JDA

### Resultado esperado

Agregar un comando nuevo debería requerir únicamente:

1. Crear una clase.
2. Implementar ISlashCommand.
3. Agregar la anotación correspondiente.

---

## Fase 2 — Context System

**Objetivo:** desacoplar la lógica de negocio de JDA.

### Tareas

* [x] CommandContext
* [x] CommandContextBuilder
* [ ] EventContext
* [ ] EventContextBuilder
* [x] Inyección de CommandContext en los comandos

### Resultado esperado

Los comandos trabajan con CommandContext y no directamente con objetos de JDA.

---

## Fase 3 — Exception System

**Objetivo:** centralizar el manejo de errores.

### Tareas

* [x] BotException
* [x] CommandException
* [x] PermissionException
* [x] CooldownException
* [x] NotGuildExecutedException
* [x] ExceptionHandler

### Resultado esperado

Los comandos no deberían contener bloques try/catch salvo casos excepcionales.

---

## Fase 4 — Logging

**Objetivo:** disponer de trazabilidad desde el inicio.

### Tareas

* [x] Configurar Logback
* [x] Logs por consola
* [x] Logs en archivos rotativos
* [x] Logger por clase
* [x] Logs de startup
* [x] Logs de ejecución de comandos
* [x] Logs de errores

### Resultado esperado

Toda actividad relevante queda registrada automáticamente.

---

## Fase 5 — Middleware System

**Objetivo:** implementar el pipeline de ejecución.

### Tareas

* [ ] IMiddleware
* [ ] MiddlewareHandler
* [ ] ContextMiddleware
* [ ] CooldownMiddleware
* [ ] PermissionMiddleware
* [ ] Hacer que los middlewares se ejecuten en todas las interacciones, incluidas las de los bots. Para el chequeo de spam mas que nada.

### Flujo esperado

```text
Interaction
    ↓
ContextMiddleware
    ↓
CooldownMiddleware
    ↓
PermissionMiddleware
    ↓
Command
```

### Resultado esperado

Toda validación previa a la ejecución de comandos pasa por middlewares.

---

## Fase 6 — Persistence Layer

**Objetivo:** preparar la capa de persistencia.

### Tareas

* [ ] PostgreSQL
* [ ] Pool de conexiones
* [ ] DatabaseConfig
* [ ] GuildRepository
* [ ] UserRepository
* [ ] Entidades básicas

### Resultado esperado

Acceso a datos desacoplado de la lógica de negocio.

---

## Fase 7 — Core Services

**Objetivo:** comenzar a mover lógica de negocio fuera de los comandos.

### Tareas

* [ ] GuildService
* [ ] UserService
* [ ] CooldownService
* [ ] PermissionService
* [ ] LocalizationService

### Resultado esperado

Los comandos actúan únicamente como capa de entrada.

---

## Fase 8 — Localization

**Objetivo:** soporte multiidioma.

### Tareas

* [x] Estructura de traducciones
* [x] Carga de archivos de idioma
* [x] Resolución por locale
* [x] Helpers de traducción

### Resultado esperado

Todos los textos se obtienen desde el sistema de localización.

---

## Fase 9 — Comandos Reales

**Objetivo:** validar la arquitectura con funcionalidades concretas.

### Primera tanda

* [ ] ping
* [ ] help
* [ ] avatar
* [ ] owl

### Segunda tanda

* [ ] admins
* [ ] notify-me
* [ ] request-role-change

### Resultado esperado

Comprobar que el flujo completo funciona correctamente.

---

## Fase 10 — Eventos

**Objetivo:** extender la arquitectura más allá de los slash commands.

### Tareas

* [x] ReadyListener
* [ ] GuildJoinListener
* [ ] GuildLeaveListener
* [ ] MessageCreateListener
* [ ] MessageDeleteListener

### Resultado esperado

Infraestructura de eventos completamente operativa.

---

## Fase 11 — Moderation Module

**Objetivo:** desarrollar el primer módulo complejo.

### Tareas

* [ ] ModerationService
* [ ] SpamService
* [ ] RaidProtector
* [ ] WarnSystem
* [ ] AntiSpamMiddleware
* [ ] AntiRaidMiddleware

### Resultado esperado

Sistema de moderación funcional y desacoplado.

---

## Fase 12 — Music Module

**Objetivo:** implementar el módulo más complejo del proyecto.

### Tareas

* [ ] MusicService
* [ ] GuildMusicPlayer
* [ ] Queue System
* [ ] Lavalink o Lavaplayer
* [ ] Comandos de música

### Resultado esperado

Gestión independiente de reproducción para cada guild.

---

## Fase 13 — Implementaciones extra

**Objetivo:** implementar funcionalidades adicionales.

* [ ] Plan premium
* [ ] Implementar blacklist de dueños de servidores
* [ ] Implementar sistema de recompensas

### Resultado esperado

Implementar funcionalidades adicionales.

## Fase 14 — Producción

**Objetivo:** preparar el proyecto para despliegue y crecimiento.

### Tareas

* [ ] Métricas
* [ ] Cache
* [ ] Auditoría
* [ ] Testing
* [ ] Docker
* [ ] CI/CD
* [ ] Dashboard Web (futuro)
* [ ] API REST (futuro)

### Resultado esperado

Proyecto preparado para operar a largo plazo.

---

# Prioridad Actual

```text
CommandService
    ↓
Registro automático de comandos
    ↓
CommandListener
    ↓
CommandContext
    ↓
ExceptionHandler
    ↓
Logback
    ↓
MiddlewareHandler
    ↓
Repositories
    ↓
Services
```

## Regla General

Hasta finalizar la Fase 5, evitar agregar funcionalidades complejas.

Primero construir y estabilizar el framework interno del bot.

Después construir módulos y comandos sobre esa base.
