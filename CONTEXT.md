# The Owlery - Project Context

## Overview

The Owlery is a long-term Discord bot project written in Java.

The goal is **not** to create a simple Discord bot, but to build a scalable platform that can eventually support multiple frontends and execution environments while sharing the same business logic.

Discord is only the first consumer of the platform.

The project prioritizes:

- Clean Architecture principles.
- High modularity.
- Explicit dependency management.
- Testability.
- Separation of responsibilities.
- Maintainability over short-term simplicity.

Performance is important, but maintainability and extensibility take precedence unless a bottleneck is demonstrated.

---

# Long-term vision

The project is intentionally being designed to evolve beyond a Discord bot.

Future targets may include:

- Discord Bot
- REST API
- Web Dashboard
- Desktop tools
- Background workers
- Scheduled jobs
- CLI utilities

These should share the same domain logic whenever possible.

Because of this, business logic should never become tightly coupled to Discord (JDA) or any specific framework.

Discord is considered an adapter, not the application's core.

---

# Modularization Strategy

The repository is already organized as a Gradle multi-module project.

Current modules:

- bot
- common
- persistence

More modules are expected in the future, for example:

- api
- web
- music
- moderation
- localization
- security
- scheduler
- shared-events

The current package organization inside `bot` should not be interpreted as the final architecture.

As the project grows, functionality should migrate into dedicated modules rather than creating an increasingly large `bot` module.

Always favor moving responsibilities into reusable modules instead of increasing coupling.

---

# Dependency Philosophy

Dependencies should always point inward.

High-level modules should not depend on implementation details.

Business logic should depend on abstractions whenever practical.

Avoid static global access.

Avoid service locators outside infrastructure code.

Constructor injection is preferred.

---

# Dependency Injection

The project contains a lightweight custom Dependency Injection container.

This container exists because:

- It provides complete control.
- It avoids introducing unnecessary framework complexity.
- It is an educational exercise.
- It can evolve according to the project's needs.

This should not be interpreted as an attempt to recreate Spring.

The objective is simply to provide:

- Bean creation
- Dependency resolution
- Constructor injection
- Configuration classes
- Future lifecycle support if necessary

Framework features should only be implemented when the project actually benefits from them.

Avoid implementing features "because Spring has them".

---

# Framework Usage

External libraries should remain isolated whenever possible.

Examples:

- JDA should remain inside infrastructure layers.
- Dotenv should only be used during application startup.
- Persistence libraries should stay inside persistence modules.

Business services should never directly depend on framework-specific APIs unless absolutely necessary.

---

# Current Architecture

The bot currently contains packages such as:

- commands
- listeners
- services
- repositories
- modules
- middlewares
- localization
- cache
- contexts
- descriptors
- config

These packages represent responsibilities, not architectural layers.

They may eventually move into separate Gradle modules.

Do not assume the current package structure is permanent.

---

# Commands

Commands should remain as small as possible.

A command should primarily:

- validate input
- delegate work
- build responses

Business rules belong in services.

Commands should not accumulate application logic.

---

# Services

Services contain the application's business logic.

Services should be reusable by future interfaces (Discord, REST API, Web UI, etc.).

If logic could reasonably be reused outside Discord, it probably belongs inside a service.

---

# Repositories

Repositories abstract persistence.

Services should never know how data is stored.

Persistence implementations should be replaceable.

---

# Event Handling

Discord events should be translated into application events whenever appropriate.

The goal is to avoid spreading JDA-specific code throughout the project.

Listeners should remain thin.

---

# Middleware

The project uses a middleware pipeline.

Examples include:

- Permissions
- Cooldowns
- Anti-spam
- Anti-raid
- Context creation

Middleware should execute cross-cutting concerns.

Business logic should not migrate into middleware.

---

# Configuration

Configuration should remain centralized.

Environment variables, property files and future configuration providers should be abstracted from the rest of the application.

Avoid reading configuration directly throughout the codebase.

---

# Localization

Localization is a first-class feature.

Strings should not be hardcoded when they are user-facing.

Translation keys are preferred over literal text.

The localization system should remain reusable by future applications.

---

# Code Style

Prefer:

- Composition over inheritance.
- Constructor injection.
- Small classes with clear responsibilities.
- Immutable objects where practical.
- Explicit code over clever code.
- Readability over premature optimization.

Avoid:

- God classes.
- Hidden dependencies.
- Static mutable state.
- Excessive inheritance.
- Framework-specific code leaking into business logic.

---

# Design Decisions

When suggesting code changes:

- Prefer solutions that improve modularity.
- Consider future extraction into independent Gradle modules.
- Avoid coupling unrelated features.
- Avoid introducing unnecessary complexity.
- Do not recommend adding frameworks unless they solve an actual problem.

Always optimize for long-term maintainability rather than the quickest implementation.

---

# AI Guidance

When generating code:

- Respect the current architecture.
- Reuse existing services whenever possible.
- Avoid duplicating business logic.
- Keep Discord-specific code isolated.
- Prefer adding abstractions only when they provide clear value.
- Do not redesign large portions of the project unless explicitly requested.

When multiple valid solutions exist, prefer the one that keeps the architecture flexible for future modularization.