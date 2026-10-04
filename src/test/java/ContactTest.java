import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public class ContactTest {

    private AndroidDriver driver;
    private WebDriverWait wait;

    // Locators de la aplicación Contactos de Google
    private final By addContactButton = By.xpath(
            "//*[@resource-id='com.google.android.contacts:id/floating_action_button' or @content-desc='Crear contacto' or @content-desc='Create contact']"
    );
    private final By firstNameInput = By.xpath("(//android.widget.EditText)[1]");
    private final By lastNameInput = By.xpath("(//android.widget.EditText)[2]");
    private final By saveButton = By.xpath(
            "//*[@text='Guardar' or @content-desc='Guardar' or @text='Save' or @resource-id='com.google.android.contacts:id/toolbar_button']"
    );

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setUdid("R3CX90P334T")
                .setAppPackage("com.google.android.contacts")
                .setAppActivity("com.android.contacts.activities.PeopleActivity")
                .setNoReset(true);

        driver = new AndroidDriver(URI.create("http://127.0.0.1:4723").toURL(), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test(description = "Crear un nuevo contacto y verificar su registro exitoso")
    public void testCrearNuevoContacto() throws InterruptedException {
        String idUnico = String.valueOf(System.currentTimeMillis()).substring(9);
        String nombre = "ContactoQA" + idUnico;
        String apellido = "Diplomado";

        // 1. Pulsar el botón '+' para crear un nuevo contacto
        WebElement btnAdd = wait.until(ExpectedConditions.elementToBeClickable(addContactButton));
        btnAdd.click();

        // 2. Ingresar Nombre y Apellido
        WebElement inputNombre = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        inputNombre.click();
        inputNombre.sendKeys(nombre);

        WebElement inputApellido = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
        inputApellido.click();
        inputApellido.sendKeys(apellido);

        // 3. Guardar el contacto
        WebElement btnGuardar = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        btnGuardar.click();

        // 4. Esperar la pantalla de detalle y verificar que el contacto se creó exitosamente
        Thread.sleep(3000);
        boolean contactoCreado = driver.getPageSource().contains(nombre);
        Assert.assertTrue(contactoCreado, "El contacto nuevo no se visualiza tras guardarlo.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}