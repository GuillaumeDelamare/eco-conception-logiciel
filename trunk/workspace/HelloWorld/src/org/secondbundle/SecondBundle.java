package org.secondbundle;

import org.osgi.framework.BundleContext;
import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Deactivate;

@Component
public class SecondBundle {
	@Activate
	public void activate(BundleContext bc) {
		//ToStringBuilder builder= new ToStringBuilder(this);
		System.out.println("builder "/*+ builder.toString()*/);
	}
	
	@Deactivate
	public void deactivate(BundleContext bc) {
		System.out.println("second bundle supprimé");
	}
	
}
