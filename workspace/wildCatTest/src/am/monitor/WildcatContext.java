
package am.monitor;

import java.util.concurrent.TimeUnit;

import org.ow2.wildcat.Context;
import org.ow2.wildcat.ContextException;
import org.ow2.wildcat.ContextFactory;
import org.ow2.wildcat.Query;
import org.ow2.wildcat.WAction;
import org.ow2.wildcat.hierarchy.attribute.Attribute;


public class WildcatContext{
	
	private static WildcatContext singleton;
	
	public static synchronized WildcatContext getInstance(){
		if (singleton == null)
			singleton = new WildcatContext();
		return singleton;
	}
	
	private Context ctx;
	
	private WildcatContext(){
		
		/*
		 * create wildcat context
		 */
		ctx = ContextFactory.getDefaultFactory().createContext();
		
		try {
			
			/*
			 * create simple hierarchy
			 */
			Attribute att = new WorkloadSensor("localhost",80,"server-status");
			ctx.attachAttribute("self://serviceEvent#eventData", att);
			
			
			ctx.createPeriodicAttributePoller("self://serviceEvent#eventData", 
					1, TimeUnit.SECONDS);
			
			
			Query query = ctx.createQuery("select max(cast(value('workload')?,double))" +
					" as maxWorkload from WAttributeEvent(source = 'self://serviceEvent#eventData').win:time(5 sec) as t having (max(cast(t.value('workload')?,double)) > 0.1)");
				
			
			/*
			 * Registering a simple action to the query
			 */
			ctx.registerActions(query, new WAction() {
				@Override
				public void onEvent() {
					System.out.println("Maximum of workload for the last 5 seconds = " +
					getListener().getNewEvents()[0].get("maxWorkload"));
				}
			});
					
		} catch (ContextException e) {
			e.printStackTrace();
		}
	}


	
	public static void main(String[] args) {
		WildcatContext wi = WildcatContext.getInstance();
		
	}
	
}
