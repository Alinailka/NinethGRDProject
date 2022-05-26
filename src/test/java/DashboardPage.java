import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import lombok.val;

import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    // к сожалению, разработчики не дали нам удобного селектора, поэтому так
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public ElementsCollection getCards(String id) {
        return cards;
    }

    public void setCards(ElementsCollection cards) {
        this.cards = cards;
    }

    public String getBalanceStart() {
        return balanceStart;
    }

    public String getBalanceFinish() {
        return balanceFinish;
    }

    public DashboardPage() {
    }

    public int getFirstCardBalance() {
        String text = cards.first().text();
        return extractBalance(text);
    }

    public int getCardBalance(String id) {
        String text = cards.findBy(Condition.id(id)).text();
        // TODO: перебрать все карты и найти по атрибуту data-test-id
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        int start = text.indexOf(balanceStart);
        int finish = text.indexOf(balanceFinish);
        String value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

}

