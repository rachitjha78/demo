package trade.demo;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class TradeStore {
	
  private static HashMap<String,Trade> trades = new HashMap<String,Trade>();
  
  public static String updateTrade(Trade trade) throws Exception {
	  
	       Trade t = trades.get(trade.getTradeId());
	       //check if trade version is lower
	       if(t != null) {
	       if(trade.getVersion() < t.getVersion()) {
	    	   throw new Exception("Rejected : version is lower");
	       }
	       if (t.getMaturityDate().isBefore(LocalDate.now())) {
	    	   throw new Exception("Rejected : maturity date lower than today's date");
	       }
	       }
	       
	       trades.put(trade.getTradeId(), trade);
	       return "trade succesfully updated";
  }
  
  
  public static void main(String args[]) {
	  
	  Thread t = new Thread(new Runnable() {
		 public void run() {
			   while(true) {
				 Iterator<String> keys  =  trades.keySet().iterator();
				 while(keys.hasNext()) {
					 String id = keys.next();
					 Trade trade = trades.get(id);
					 if(trade.getMaturityDate().isBefore(LocalDate.now())) {
						 trade.setExpired("Y");
					 }
				 }
				 try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
			   }
		   
	  });
	  t.start();
  }
  

}

