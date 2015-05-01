import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import play.libs.F.Callback;
import play.test.TestBrowser;

public class ViewTest {

    @Test
    public void testTitle() {
    	WebDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:9000/");
        assertEquals(driver.getTitle(), "Inicio");
        assertThat(driver.getTitle()).isEqualTo("Inicio"); //s
        driver.get("http://localhost:9000/registro");
        assertEquals(driver.getTitle(), "Registro");
        assertFalse(driver.getTitle()=="PaginaRegistro"); //e
        driver.get("http://localhost:9000/ayuda");
        assertEquals(driver.getTitle(), "Ayuda");
        driver.get("http://localhost:9000/estadisticas");
        assertEquals(driver.getTitle(), "Estadísticas");
    }
    
    @Test
    public void testContainsStyle() {
    	new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333");
                assertThat(browser.pageSource()).contains("task(s)");
            }
        };
        new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:9000");
                assertThat(browser.pageSource()).contains("/public/stylesheets/inicio.scala.css");
            }
        };
    }
    
    
    @Test
    public void testFormView() {
    	WebDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:9000/");
//        assertEquals(driver.getTitle(), "Inicio");
//        WebElement password = driver.findElements(By.id("usuario")).get(0);
//        assertTrue(password.isEnabled());
//        WebElement text = driver.findElements(By.id("password")).get(0);
//        assertTrue(text.isEnabled());
    }
    
    
}
