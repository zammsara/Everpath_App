<h1 align="center">Everpath</h1>

<p align="center">
  Convierte tus metas en un camino visual de crecimiento personal.
</p>

<p align="center">
  Kotlin • Jetpack Compose • Room • Retrofit • Spring Boot • PostgreSQL
</p>

---

## ¿Qué es Everpath?

Everpath es una aplicación Android diseñada para ayudar a las personas a planificar, organizar y dar seguimiento a sus objetivos personales mediante una experiencia moderna e intuitiva.

La aplicación permite estructurar metas, registrar actividades, visualizar progreso y mantener un historial de evolución personal dentro de un único entorno. Más que un simple gestor de tareas, Everpath busca ofrecer una representación clara del camino que sigue cada usuario para alcanzar sus objetivos.

El proyecto fue desarrollado aplicando principios de arquitectura limpia, separación de responsabilidades y buenas prácticas de desarrollo móvil, integrando persistencia local, sincronización remota y una interfaz construida completamente con Jetpack Compose.

---

## Características

- Gestión de metas personales.
- Organización de objetivos mediante nodos.
- Registro y seguimiento de actividades.
- Dashboard con resumen de progreso.
- Historial de eventos y actividad.
- Sistema de logros.
- Persistencia local mediante Room.
- Sincronización con backend REST.
- Arquitectura escalable basada en MVVM.
- Interfaz desarrollada con Jetpack Compose.

---

## Tecnologías Utilizadas

### Android

| Tecnología | Uso |
|------------|-----|
| Kotlin | Lenguaje principal |
| Jetpack Compose | Interfaz de usuario |
| Material Design 3 | Sistema visual |
| Room | Persistencia local |
| Retrofit | Consumo de API REST |
| Coroutines | Operaciones asíncronas |
| Flow | Observación reactiva de datos |
| Navigation Compose | Navegación entre pantallas |

### Backend

| Tecnología | Uso |
|------------|-----|
| Spring Boot | API REST |
| PostgreSQL | Base de datos |
| Spring Data JPA | Persistencia |
| Spring Security | Seguridad |
| Swagger/OpenAPI | Documentación de API |

---

## Arquitectura

Everpath implementa una arquitectura basada en **Clean Architecture** y el patrón **MVVM (Model - View - ViewModel)**.

Esta organización permite mantener una separación clara entre la interfaz de usuario, la lógica de negocio y las fuentes de datos, facilitando el mantenimiento y la escalabilidad del proyecto.

```text
┌─────────────────────┐
│   Jetpack Compose   │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│      ViewModel      │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│      Use Cases      │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│     Repository      │
└───────┬─────┬───────┘
        │     │
        ▼     ▼
   Room DB  Retrofit
```

---

## Flujo de Datos

```text
Usuario
   │
   ▼
Pantalla Compose
   │
   ▼
ViewModel
   │
   ▼
UseCase
   │
   ▼
Repository
   │
 ┌─┴──────────────┐
 ▼                ▼
Room          Retrofit
 │                │
 └──────┬─────────┘
        ▼
      Datos
```

---

## Estructura del Proyecto

```text
com.everpath
│
├── data
├── domain
├── presentation
├── navigation
└── di
```

### `data`

Responsable de la gestión y acceso a datos.

```text
data
├── local
├── remote
├── mapper
├── repository
└── sync
```

Incluye:

- Room Database
- DAO
- Entidades
- DTOs
- Implementaciones de repositorios
- Servicios Retrofit
- Mecanismos de sincronización

---

### `domain`

Contiene el núcleo de negocio de la aplicación.

```text
domain
├── model
├── repository
├── usecase
└── sync
```

Responsabilidades:

- Modelos de dominio
- Casos de uso
- Contratos de repositorio
- Reglas de negocio

---

### `presentation`

Contiene toda la interfaz de usuario desarrollada con Jetpack Compose.

```text
presentation
├── auth
├── dashboard
├── goals
├── activities
├── achievements
├── profile
├── components
└── viewmodel
```

Responsabilidades:

- Pantallas
- Componentes visuales
- Gestión de estados
- ViewModels
- Navegación de usuario

---

### `navigation`

Centraliza las rutas y la navegación entre pantallas.

---

### `di`

Gestiona la inyección de dependencias y configuración de servicios compartidos.

---

## Persistencia Local

Everpath utiliza Room Database como solución de almacenamiento local.

Esto permite:

- Acceso rápido a la información.
- Persistencia de datos en el dispositivo.
- Reducción de llamadas innecesarias al servidor.
- Mejor experiencia de usuario.

---

## Comunicación con Backend

La comunicación con el servidor se realiza mediante Retrofit y servicios REST.

Las responsabilidades del backend incluyen:

- Gestión de usuarios.
- Persistencia centralizada.
- Sincronización de datos.
- Gestión de objetivos.
- Gestión de actividades.
- Gestión de logros.
- Historial de progreso.

---

## Instalación

### Clonar repositorio

```bash
git clone https://github.com/usuario/everpath.git
```

### Abrir proyecto

```bash
Android Studio
File → Open
```

### Ejecutar aplicación

1. Sincronizar Gradle.
2. Configurar backend si es necesario.
3. Ejecutar en emulador o dispositivo físico.

---

## Objetivo del Proyecto

Everpath nace con el propósito de ofrecer una forma más visual y estructurada de gestionar objetivos personales. El proyecto combina técnicas de organización, seguimiento de progreso y arquitectura moderna de desarrollo móvil para construir una aplicación mantenible, escalable y preparada para futuras expansiones.
