package trade.demo;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import trade.demo.TradeStore;;

public class TestTradeStore {
   
   @Test
   public void testUpdateTradeStoreSuccess() throws Exception{

      Trade trade = new Trade();
      trade.setTradeId("T2");
      trade.setVersion(2);
      trade.setCounterPartyId("CP-2");
      trade.setBookId("B1");
      trade.setMaturityDate(LocalDate.of(2021, 5, 22));
      trade.setExpired("N");
      String s = TradeStore.updateTrade(trade);
      assertEquals(s,"trade succesfully updated");
   }
   
   @Test
   public void testUpdateTradeStoreException1() throws Exception{

      Trade trade = new Trade();
      trade.setTradeId("T2");
      trade.setVersion(1);
      trade.setCounterPartyId("CP-2");
      trade.setBookId("B1");
      trade.setMaturityDate(LocalDate.of(2021, 5, 22));
      trade.setExpired("N");
      try {
        TradeStore.updateTrade(trade);
      }
      catch(Exception e) {
    	  assertEquals(e.getMessage(),"Rejected : version is lower");
      }
      
   }
   
   @Test
   public void testUpdateTradeStoreException2() throws Exception{

      Trade trade = new Trade();
      trade.setTradeId("T3");
      trade.setVersion(1);
      trade.setCounterPartyId("CP-2");
      trade.setBookId("B1");
      trade.setMaturityDate(LocalDate.of(2019, 5, 22));
      trade.setExpired("N");
      try {
        TradeStore.updateTrade(trade);
      }
      catch(Exception e) {
    	  assertEquals(e.getMessage(),"Rejected : maturity date lower than today's date");
      }
      
   }

  
}