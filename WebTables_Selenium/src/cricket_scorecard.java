import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * The code is used to display records of a webtable in different ways
 */

/**
 * @author anand acharya
 *
 */
public class cricket_scorecard {
	
	WebDriver driver;
	
	@BeforeTest
	public void openCricbuzz() throws InterruptedException{
		//set the driver
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
		//disable firefox default notification
		FirefoxProfile ffprofile = new FirefoxProfile();
		ffprofile.setPreference("dom.webnotifcations.enabled", false);
		//set firefox driver
		driver = new FirefoxDriver(ffprofile);
		//delete all cookies
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		//Open the scorecard
		driver.get("http://www.cricbuzz.com/live-cricket-scorecard/18875/eng-vs-aus-5th-odi-australia-tour-of-england-2018");
		//Wait for 5 seconds
		Thread.sleep(5000);
	}
	
	//Test to display all records in the table
	@Test
	public void webtableAllRecords(){
		//Fetch all row elements for innings 1
		List<WebElement> rows = driver.findElements(By.xpath(".//*[@id='innings_1']/div/div"));
		//Fetch all elements (columns) in each row one by one
		for (WebElement row: rows){
			List<WebElement> columns = row.findElements(By.xpath("./div"));
			//Print text value of each element (column)
			for(WebElement column: columns){
				System.out.print(column.getText()+" ");
			}
			System.out.println(" ");
		}
		System.out.println("********************************************************");
	}
	
	//Test to display records for specific columns in a table
	@Test
	public void webtableSpecificRecords(){
		/*initialize position to the required columns to display. 
		Here I want only Name, balls and runs columnns to be displayed*/
		int positionName = 0;
		int positionB = 0;
		int positionR = 0;
		int count = 1;
		//Fetch all elements in the header
		List<WebElement> header = driver.findElements(By.xpath(".//*[@id='innings_1']//div[text()='Batsman']/../div"));
		//assign counter value 1,2,3 and so on to the required header columns
		for (WebElement head: header){
			System.out.print(head.getText() + " ");
			switch(head.getText().toLowerCase()){
				case "batsman":
					positionName = count;
					break;
				case "r":
					positionR = count;
					break;
				case "b":
					positionB = count;
					break;
			}
			count++;
		}
		System.out.println(" ");
		//Fetch all row elements in the table
		List<WebElement> rows1 = driver.findElements(By.xpath(".//*[@id='innings_1']//div[@class='cb-col cb-col-100 cb-scrd-itms']"));
		//scan through each row for the elements
		for (WebElement row1: rows1){
			//assign counter to columns
			int colcount = 1;
			//Fetch all column elements in the row one by one
			List<WebElement> columns1 = row1.findElements(By.xpath("./div"));
			//Scan through each column
			for(WebElement column1: columns1){
				//print text value of column if it meets below condition
				if(colcount == positionName || colcount == positionR || colcount == positionB){
					System.out.print(column1.getText()+" ");
				}
				colcount++;
			}
			System.out.println(" ");
		}
		
	}
	
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}

}
