package org.oep.core.api;

import java.util.List;
import java.util.Observer;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

public interface Installer {
	public Bundle install(String bundle) throws BundleException;
	public Bundle installAPIBundle(String bundle) throws BundleException;
	public Bundle installServiceBundle(String bundle) throws BundleException;
	public void startServiceBundle(Bundle bundle) throws BundleException;
	public Set<String> getInstalledAPIBundle();
	public List<Bundle> getInstalledServiceBundle(String APIBundle);
	public void addObserver(Observer o);
}
