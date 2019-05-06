import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;

import com.google.common.io.Files;

public class AirTicketReservationChecker{
	public static void main(String[] args) throws InterruptedException{

		WebDriver driver = null;
		try {
			System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(true);
			options.addArguments("--headless");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-gpu");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--window-size=1280,1024");
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Actions actions = new Actions(driver);
			driver.get("https://www.jal.co.jp/");

			// ログインする
			driver.findElement(By.cssSelector(Mappings.MEMBER_LOGIN)).click();
			driver.findElement(By.cssSelector(Mappings.MEMBER_NO)).sendKeys("your id");
			driver.findElement(By.cssSelector(Mappings.ACCESS_CD)).sendKeys("your pass");
			driver.findElement(By.cssSelector(Mappings.LOGIN_BTN)).click();

			actions.moveToElement(driver.findElement(By.cssSelector(Mappings.LOGIN_CONTINUE_BTN))).perform();
			driver.findElement(By.cssSelector(Mappings.LOGIN_CONTINUE_BTN)).click();

			// 国内線特典航空券の予約へ遷移
			driver.findElement(By.cssSelector(Mappings.RESERVATION_BTN)).click();
			driver.findElement(By.cssSelector(Mappings.SPECIAL_TICKET_BTN)).click();
			// 国内線特典航空券 一番目の予約の変更を押す
			driver.findElement(By.cssSelector(Mappings.SPECIAL_TICKET_FIRST_RESERVATION)).click();
			actions.moveToElement(driver.findElement(By.cssSelector(Mappings.CHANGE_RESERVATION_BTN))).perform();
			driver.findElement(By.cssSelector(Mappings.CHANGE_RESERVATION_BTN)).click();
			driver.findElement(By.cssSelector(Mappings.CHANGE_BTN)).click();

			// 日付を変更する
			Select day = new Select(driver.findElement(By.cssSelector(Mappings.DAY)));
			day.selectByVisibleText("8"); //TODO: change day

			driver.findElement(By.cssSelector(Mappings.CONFIRM_BTN)).click();

			// 空席状況のチェック
			byte[] binaly = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			Files.write(binaly, new File("screenshot_"+ timestamp +".png"));

			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			List<WebElement> elements = driver.findElements(By.cssSelector(Mappings.RESERVATION_LIST));

			int available = 0;

			for(WebElement v :elements) {
				try {
					System.out.println(v.findElement(By.cssSelector("a")));
				}catch(NoSuchElementException e){
					continue;
				}
				available++;
			}
			System.out.println("予約可能な便数:" + available);

			if(available != 0) {
				Email email = EmailBuilder.startingBlank()
						.from("e-mail") // change e-mail
						.to("e-mail") // change e-mail
						.withSubject("JAL国内優待券予約とれる！！！！")
						.withPlainText("早く予約して")
						.buildEmail();

				Mailer mailer = MailerBuilder
						.withSMTPServer("sample.jp", 587, "user", "pass") // your smtp server
						.withTransportStrategy(TransportStrategy.SMTP)
						.withSessionTimeout(10 * 1000)
						.buildMailer();

				mailer.sendMail(email);

			}
			System.out.println("メール送信済み！");

		}catch(Exception e) {
			e.printStackTrace();

		} finally {
			if(Objects.nonNull(driver)) {
				driver.close();
			}
		}


	}
}