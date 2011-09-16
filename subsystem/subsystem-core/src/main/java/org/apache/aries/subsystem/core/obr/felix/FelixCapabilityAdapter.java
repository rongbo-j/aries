/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.aries.subsystem.core.obr.felix;

import java.util.Collections;
import java.util.Map;

import org.osgi.framework.resource.Capability;
import org.osgi.framework.resource.Resource;
import org.osgi.framework.wiring.BundleRevision;

public class FelixCapabilityAdapter implements Capability {
	private final org.apache.felix.bundlerepository.Capability capability;
	private final Resource resource;
	
	public FelixCapabilityAdapter(org.apache.felix.bundlerepository.Capability capability, Resource resource) {
		if (capability == null)
			throw new NullPointerException("Missing required parameter: capability");
		if (resource == null)
			throw new NullPointerException("Missing required parameter: resource");
		this.capability = capability;
		this.resource = resource;
	}

	public Map<String, Object> getAttributes() {
		return capability.getPropertiesAsMap();
	}

	public Map<String, String> getDirectives() {
		return Collections.emptyMap();
	}

	public String getNamespace() {
		String namespace = capability.getName();
		if (namespace.equals(org.apache.felix.bundlerepository.Capability.BUNDLE))
			return BundleRevision.BUNDLE_NAMESPACE;
		if (namespace.equals(org.apache.felix.bundlerepository.Capability.FRAGMENT))
			return BundleRevision.HOST_NAMESPACE;
		if (namespace.equals(org.apache.felix.bundlerepository.Capability.PACKAGE))
			return BundleRevision.PACKAGE_NAMESPACE;
		return namespace;
	}

	public Resource getResource() {
		return resource;
	}
}