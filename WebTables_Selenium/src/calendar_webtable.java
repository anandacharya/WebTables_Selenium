import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 * program to display all date elements in a calendar and click a particular date
 */

/**
 * @author anand acharya
 *
 */
public class calendar_webtable {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
		FirefoxProfile ffprofile = new FirefoxProfile();
		ffprofile.setPreference("dom.webnotifications.enabled", false);
		WebDriver driver = new FirefoxDriver(ffprofile);
		driver.manage().deleteAllCookies();

		driver.get("https://www.redbus.in/");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		
		driver.findElement(By.id("src")).sendKeys("Mumbai");
		Thread.sleep(3000);
		driver.findElement(By.id("dest")).sendKeys("Bangalore");
		Thread.sleep(3000);
		driver.findElement(By.id("onward_cal")).click();
		//driver.findElement(By.xpath(".//*[@class='fl search-box date-box gtm-onwardCalendar']")).click();
		Thread.sleep(3000);
		
		List<WebElement> dates = driver.findElements(By.xpath(".//*[@id='rb-calendar_onward_cal']/table/tbody/tr/td"));
		
		int count = dates.size();
		
		for(int i=0; i<count; i++)
				{
					WebElement ele = dates.get(i);
					String s = ele.getText();
					System.out.println("Dates are: "+s);
			
					if (s.equalsIgnoreCase(String.valueOf((Calendar.getInstance()).get(Calendar.DAY_OF_MONTH))))
					{
						dates.get(i).click();
						break;
					}
				}
		//implicit wait
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("search_btn")).click();
		System.out.println("Test completed successfully");
		
	}

}
