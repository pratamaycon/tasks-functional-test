package br.ce.wcaquino.tasks.functional;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
			// clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			// escrever descricao
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/05/2021");
			
			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			
			assertEquals("Sucess!", mensagem);
		} finally {
			// fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
			// clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/05/2021");
			
			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();

			assertEquals("Fill the task description", mensagem);
		} finally {
			// fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
			// clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			// escrever descricao
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			
			assertEquals("Fill the due date", mensagem);
		} finally {
			// fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
			// clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			// escrever descricao
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/05/2010");
			
			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			
			assertEquals("Due date must not be in past", mensagem);
		} finally {
			// fechar o browser
			driver.quit();
		}
	}

	protected WebDriver acessarAplicacao() throws MalformedURLException {
		//WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL(" http://192.168.0.109:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.0.109:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

}
