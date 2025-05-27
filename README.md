# Transito-con-DB
# Sistema de Gestión de Vehículos y Propietarios

Este proyecto es una aplicación de escritorio desarrollada en **Java** con **Swing** para la gestión de vehículos, propietarios y sus tarjetas de propiedad. Utiliza una base de datos **MySQL** para almacenar los datos y sigue el patrón **DAO (Data Access Object)** para la interacción con la base de datos.

---

📑 Contenido

- Gestión de vehículos.
- Gestión de propietarios.
- Registro de tarjetas de propiedad.
- Consulta, modificación y eliminación de registros.
- Visualización de listados en tablas (JTable).

---

🛠️ Tecnologías utilizadas

- **Java SE**
- **Swing (JFrame, JTable, JOptionPane)**
- **MySQL**
- **JDBC**
- **NetBeans IDE**

---

📖 Estructura del proyecto
src/
├── control/
│ └── ControlMenuSTYGui.java
│ └── ControlTarjetaPropiedadGui.java
│ └── ...
├── modelo/
│ └── Vehiculo.java
│ └── Propietario.java
│ └── TarjetaPropiedad.java
│ └── VehiculoDAO.java
│ └── PropietarioDAO.java
│ └── TarjetaPropiedadDAO.java
│ └── Conexion.java
├── vista/
│ └── MenuPrincipal.java
│ └── GestionTarjetasPropiedad.java
│ └── ...
└── Main.java


---

## 📊 Base de datos

Se utiliza una base de datos **MySQL** llamada `bd_tarjetas_propiedad` con las siguientes tablas:

- `vehiculo`
- `propietario`
- `tarjeta_propiedad`

Consulta de creación de tablas y datos de prueba incluidos en `/sql_scripts/`.

---

## 📌 Instalación y ejecución

1. Crear la base de datos ejecutando los scripts SQL provistos.
2. Configurar los datos de conexión en `Conexion.java`.
3. Compilar y ejecutar el proyecto desde NetBeans o desde línea de comandos.

---

## 📦 Funcionalidades

✔️ Registrar vehículos y propietarios  
✔️ Asignar tarjetas de propiedad  
✔️ Consultar listados en tablas  
✔️ Modificar y eliminar registros  
✔️ Mensajes de confirmación y error mediante `JOptionPane`  

---

📌 Nota

Este proyecto se desarrolló con fines educativos, como práctica de conexión a bases de datos desde Java con JDBC y construcción de interfaces gráficas con Swing.

---

📧 Contacto

**Autor:** Hernán Darío  
**Proyecto:** Sistema de Gestión de Vehículos y Tarjetas de Propiedad  
**Año:** 2025  

