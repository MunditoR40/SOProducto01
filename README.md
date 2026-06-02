# 🖥️ Simulación de Problemas de Comunicación y Concurrencia en Sistemas Operativos

Este repositorio contiene el código fuente desarrollado en **Java** para simular los mecanismos internos de un sistema operativo frente a los problemas clásicos de comunicación y concurrencia entre procesos (hilos).

---

## 🏛️ Información Académica

* **Universidad:** Universidad Nacional del Santa (UNS).
* **Escuela Profesional:** Ingeniería de Sistemas e Informática.
* **Curso:** Sistemas Operativos.
* **Docente:** Dr. Mirko Manrique Ronceros.

---

## 👥 Integrantes del Proyecto

El desarrollo y documentación de este proyecto fue distribuido estratégicamente entre los siguientes integrantes:

* **Rojas León, Angel Edmundo** (`0202414044`) - *Desarrollador Principal (Programación en Java).*
* **Liñan Briones, Juan Carlos** (`0202414022`) - *Documentador Técnico e Ingeniería Inversa.*
* **Alejos Ponce, Erick Segundo** (`0202314002`) - *Investigador (Objetivos y Marco Teórico de Condición de Carrera y Deadlock).*
* **Salinas Pinedo, Carlos Yampier** (`0202414047`) - *Analista (Descripción del problema y Marco Teórico de Starvation).*

---

## 📖 Descripción del Tema y Proyecto

La comunicación entre procesos (IPC) es esencial en sistemas operativos multitarea para que los procesos cooperen, compartan datos o sincronicen sus acciones. La concurrencia mejora el rendimiento, pero requiere sincronización para garantizar resultados correctos. 

Cuando varios hilos o procesos acceden al mismo tiempo a recursos compartidos, pueden surgir diversos problemas de concurrencia que afectan el correcto funcionamiento del sistema. Este proyecto simula tres de los principales problemas y sus respectivas soluciones:

* **Condición de Carrera (Race Condition):** Una condición de carrera ocurre cuando dos o más procesos acceden concurrentemente a datos compartidos y el resultado final depende del orden no determinista de ejecución.
* **Deadlock (Interbloqueo):** El deadlock ocurre cuando dos o más procesos quedan bloqueados esperando recursos entre sí.
* **Starvation (Inanición):** La inanición ocurre cuando un proceso nunca recibe el recurso que necesita porque otros procesos tienen mayor prioridad.

### Mecanismos de Solución Implementados

Para resolver estas inconsistencias en la simulación, se aplican técnicas de sincronización nativas:

* **Mutex:** Un mutex permite que solo un hilo acceda a un recurso compartido.
* **Semáforos:** Un semáforo controla el acceso a uno o varios recursos mediante operaciones atómicas.
* **Monitores:** Un monitor agrupa datos compartidos y métodos sincronizados, garantizando que solo un proceso esté activo dentro de él en un momento dado.

---

## ⚙️ Ejecución

1. Clona el repositorio en tu entorno local.
2. Abre el proyecto en tu IDE de Java (Eclipse, NetBeans o IntelliJ).
3. Ejecuta la clase principal (`Main.java`) para observar en la consola el comportamiento de los hilos y cómo se aplican los bloqueos para evitar las condiciones de carrera, el interbloqueo y la inanición.
