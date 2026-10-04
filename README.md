# Mobile Automation — Google Contacts (Appium + TestNG)

Proyecto de automatización móvil en Android desarrollado con **Java, Appium (UiAutomator2) y TestNG** para validar la creación de contactos en la aplicación **Contactos de Google**.

## Tecnologías Utilizadas
* **Lenguaje:** Java 21
* **Framework Mobile:** Appium Java Client (`v10.1.1`) / Driver `UiAutomator2`
* **Framework de Pruebas:** TestNG (`v7.9.0`)
* **Dispositivo de Pruebas:** Samsung Galaxy (`SM-S938B` - Android 16)
* **Aplicación bajo prueba (AUT):** Contactos de Google
    * `appPackage`: `com.google.android.contacts`
    * `appActivity`: `com.android.contacts.activities.PeopleActivity`

##  Caso de Prueba Automatizado (`src/test/java/ContactTest.java`)
* **`testCrearNuevoContacto`**:
    1. Inicia la sesión con el servidor Appium (`http://127.0.0.1:4723`) y abre la aplicación Contactos de Google.
    2. Pulsa el botón flotante (`+`) para agregar un nuevo contacto.
    3. Completa los campos de **Nombre** (con sufijo único dinámico) y **Apellido**.
    4. Guarda el contacto y verifica mediante una aserción (`Assert.assertTrue`) que el contacto creado se visualice correctamente en la pantalla de detalle.

##  Cómo ejecutar la prueba
1. Iniciar el servidor de Appium en la terminal: `appium`
2. Conectar el dispositivo Android con *Depuración por USB* activa (`adb devices`).
3. Ejecutar la clase `src/test/java/ContactTest.java` desde IntelliJ IDEA.