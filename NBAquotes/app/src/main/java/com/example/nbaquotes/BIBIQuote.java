package com.example.nbaquotes;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BIBIQuote {
    public String mQuote;
    public List<String> mQuotes = QuotesPull();

    public BIBIQuote(){

        int random = new Random().nextInt(7);
        this.mQuote = this.mQuotes.get(random);
    }

    public List<String> QuotesPull() {
        List<String> Quotes = new ArrayList<>();
        Quotes.add("לא יהיה כלום כי אין כלום");
        Quotes.add("שלטון הימין בסכנה, המצביעים הערבים נעים בכמויות אדירות לקלפי, עמותות השמאל מביאות אותם באוטובוסים.");
        Quotes.add("אנשי השמאל שכחו מה זה להיות יהודים, הם חושבים שאת הביטחון שלנו ישימו בידי ערבים, הערבים ידאגו לנו הערבים ידאגו לנו– ניתן להם חלק מהארץ והם ידאגו לנו.");
        Quotes.add("אש ממשלה השקוע עד צוואר בחקירות אין לו מנדט ציבורי ומוסרי לקבוע דברים גורליים כי קיים חשש אמיתי שהוא יכריע על בסיס האינטרס האישי של ההישרדות שלו, ולא לפי האינטרס הלאומי. הדבר הנכון לעשות הוא שהממשלה הזו תלך הביתה");
        Quotes.add("רק אני יודע להשיג את הקולות המזרחיים, אני יודע את מי הם שונאים.");
        Quotes.add("פסי-כו-לו-גית, M.A, B.A - זהו!");
        Quotes.add("אבא סידר לכם מיליארדים, ואתה מתווכח איתי?");
        return Quotes;
    }
}