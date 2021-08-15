package olx.spy.parser;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class OlxParser {
    private final String firefoxKey = "webdriver.gecko.driver";
    private final String firefoxValue = "C:\\WebDriver\\bin\\geckodriver.exe";
    private final String[] pagesRealEstate;

    public OlxParser(String[] pagesRealEstate) {
        this.pagesRealEstate = pagesRealEstate;
    }

    public StringBuffer parseOlxPages() {
        System.setProperty(firefoxKey, firefoxValue);
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        WebDriver driver = new FirefoxDriver(firefoxOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        StringBuffer realEstateNotes = new StringBuffer();
        try {
            for (int i = 0; i < pagesRealEstate.length; i++) {
                driver.get(pagesRealEstate[i]);
                List<WebElement> lastPageInPagesList =  driver.findElements(By.cssSelector("a[data-cy='page-link-last']"));

                /* check IF have got reqired WebElement for many pages real estate notes
                ELSE check if single page notes only
                */
                if (lastPageInPagesList.size() > 0) {
                    StringBuffer beginOfPagesRealEstate = new StringBuffer(pagesRealEstate[i]);
                    realEstateNotes.append("\n").append(pagesRealEstate[i]); //add link text for real estate chapter
                    int lastPageNumber = Integer.parseInt(lastPageInPagesList.get(0).getText());
                    for (int j = 1; j <= lastPageNumber; j++) {
                        StringBuffer currentPage = new StringBuffer(beginOfPagesRealEstate)
                                .append("?page=").append(j);
                        driver.get(currentPage.toString());
                        StringBuffer notesListPerChapter = makeNotesListPerChapter(driver, currentPage);
                        realEstateNotes.append("\n\t PAGE -").append(j).append("-\n").append(notesListPerChapter);
                    }
                } else if (driver.findElements(By.className("title-cell")) != null) {
                    StringBuffer beginOfPagesRealEstate = new StringBuffer(pagesRealEstate[i]);

                    realEstateNotes.append("\n").append(pagesRealEstate[i]); //add link text for real estate chapter
                    StringBuffer singlePage = new StringBuffer(beginOfPagesRealEstate).append("?page=1");
                    driver.get(singlePage.toString());
                    StringBuffer notesListPerChapter = makeNotesListPerChapter(driver, singlePage);
                    realEstateNotes.append("\n\t for this notes is only single PAGE -1-\n").append(notesListPerChapter);
                }
            }
        } catch (NullPointerException | InvalidArgumentException e) {
            e.printStackTrace();
        } finally {
            try {
                Thread.sleep(500);
                driver.quit();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realEstateNotes;
    }

    private StringBuffer makeNotesListPerChapter(WebDriver driver, StringBuffer currentPage) {
        StringBuffer realEstateNotesList = new StringBuffer();
        try {
            List<WebElement> oneNoteRenta =  driver.findElements(By.className("bottom-cell"));
            List<WebElement> oneNoteDescreption =  driver.findElements(By.className("title-cell"));
            List<WebElement> oneNotePrice =  driver.findElements(By.className("price"));

            for (int i = 0; i < oneNoteRenta.size(); i++) {
                String oneNoteRentaText = oneNoteRenta.get(i).getText();
                String oneNoteDescreptionText = oneNoteDescreption.get(i).getText();
                String oneNotePriceText = oneNotePrice.get(i).getText();
                realEstateNotesList.append(oneNoteRentaText).append(" - ")
                        .append(oneNoteDescreptionText)
                        .append(", цена: ").append(oneNotePriceText)
                        .append(", url: ").append(currentPage.toString())
                        .append(" - to visit follow to this link ").append("\n");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return realEstateNotesList;
    }
}
