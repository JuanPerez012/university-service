# university-service

Microservicio responsable de la gestión de universidades y de la parametrización académica (cortes, notas mínimas, programas, etc.). Permitirá realizar operaciones CRUD de universidades, exponer datos para los demás módulos y contribuir al análisis académico global.  

---

## Descripción del repositorio

Este repositorio contiene únicamente la implementación del **University Service**. Su responsabilidad principal es:  

* Gestionar la entidad **University** (CRUD completo con validaciones).  
* Administrar atributos académicos asociados (cortes, notas mínimas, etc.).  
* Proveer información de universidades para estudiantes, docentes y coordinadores.  
* Servir como fuente de datos para análisis globales y métricas académicas.  
* Integrarse con otros microservicios (Auth, Student, Teacher, Notification).  

Tecnologías principales:  

* Java 21  
* Spring Boot 3.5.5  
* Build: Maven  
* Tests: JUnit 5  
* Base de datos: PostgreSQL  
* Contenerización prevista (Docker)  

---

## Historias de usuario cubiertas

El módulo University cubre las siguientes HU:  

* **ÉPICA 3: Gestión de Entidades**  
  * HU-218 → Repositorio para el módulo University.  
  * HU-219 → Configuración del backend en Java/Spring Boot.  
  * HU-220 → CRUD de universidades.  
  * HU-221 → Despliegue en entorno local con Postman.  
  * HU-222 → Registro de universidades con atributos académicos (cortes, notas mínimas, etc.).  
  * HU-223 → Tabla con universidades (filtros y búsqueda).  
  * HU-224 → Edición de datos de universidades.  
  * HU-225 → Eliminación de universidades (con confirmación).  

* **ÉPICA 4: Análisis y Estadísticas**  
  HU-315, HU-317, HU-324 → Estadísticas globales y reportes consolidados por facultad o programa.  

---

## Estructura del repositorio

Ejemplo (alto nivel):  

/
├─ src/
│ ├─ main/
│ │ ├─ java/ → código fuente
│ │ └─ resources/ → application.properties
│ └─ test/ → pruebas unitarias con JUnit 5 y Testcontainers
├─ docs/ → diagramas, contratos API (OpenAPI), ADRs
├─ Dockerfile
├─ pom.xml
├─ .gitignore
└─ README.md


---

## Políticas de rama y flujo de trabajo

* **main** → código listo para producción.  
* **release/** → estabilización previa a producción.  
* **qa** → rama para despliegue en QA.  
* **develop** → rama de integración diaria.  

Flujo típico:  
`feature/*` → PR → `develop` → merge a `qa` → validación → `release/*` → `main`.  

---

## Perfiles / properties (con Maven)

* **application.properties** → Configuración base.  
* **application-dev.properties** → Desarrollo local.  
* **application-qa.properties** → QA.  
* **application-prod.properties** → Producción.  

---

## API (versionado)

Versionado base: `/api/v1/...`.

Endpoints principales previstos:  

* `GET  /api/v1/universities` → Listar universidades (con filtros y búsqueda).  
* `GET  /api/v1/universities/{id}` → Obtener universidad por ID.  
* `POST /api/v1/universities` → Crear universidad con atributos académicos.  
* `PUT  /api/v1/universities/{id}` → Actualizar datos de la universidad.  
* `DELETE /api/v1/universities/{id}` → Eliminar universidad (con confirmación o baja lógica).  

---

## Dockerización (concepto)

* Imagen base: JDK 21.  
* Multi-stage build.  
* Variables de entorno para perfiles.  
* Healthcheck en `/actuator/health`.  
* Tagging por versión (`vX.Y.Z`) y commit (`sha`).  

---

## Colaboración y convención de commits

* Convencional commits: `feat(university): add university CRUD`  
* PRs deben:  
  * Referenciar HU o issue en Jira.  
  * Incluir descripción clara.  
  * Ser revisadas antes de merge.  