import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.lang.Thread.sleep
import java.time.Duration


fun main(){
 val driverLocation = "app/src/main/resources/chromedriver.exe"

 System.setProperty("webdriver.chrome.driver", driverLocation);

 val driver = ChromeDriver()
 driver.get("https://twitter.com/search?q=web3")

 WebDriverWait(driver, Duration.ofSeconds(10)).until(
  ExpectedConditions.presenceOfElementLocated(
   By.cssSelector(
    "main section"
   )
  )
 )

 val page = driver.findElement(By.cssSelector("body"))

 val links: MutableList<String> = mutableListOf()
 while(links.size<5){
  val l = driver.findElements(By.xpath("//div[@data-testid=\"cellInnerDiv\"]//time/.."))
  for (e in l) {
   if (e.getAttribute("href")!=null) links.add(e.getAttribute("href"))
  }

  page.sendKeys(Keys.PAGE_DOWN)

  sleep(1500)
 }

 driver.quit();

 println(links);

 }