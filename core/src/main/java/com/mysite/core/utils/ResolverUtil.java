package com.mysite.core.utils;

<<<<<<< HEAD


=======
>>>>>>> f9f96e4 (Added Code)
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

import java.util.HashMap;
import java.util.Map;


/**
 *  resource resolver factory helper class
 */
public final class ResolverUtil {

    private ResolverUtil() {

    }

<<<<<<< HEAD
    public static final String GEEKS_SERVICE_USER = "geeksserviceuser";
=======
    public static final String AEM_SERVICE_USER = "writeService";
>>>>>>> f9f96e4 (Added Code)
    /**
     * @param  resourceResolverFactory factory
     * @return new resource resolver for Sony service user
     * @throws LoginException if problems
     */
    public static ResourceResolver newResolver( ResourceResolverFactory resourceResolverFactory ) throws LoginException {
        final Map<String, Object> paramMap = new HashMap<String, Object>();
<<<<<<< HEAD
        paramMap.put( ResourceResolverFactory.SUBSERVICE, GEEKS_SERVICE_USER );
=======
        paramMap.put( ResourceResolverFactory.SUBSERVICE,AEM_SERVICE_USER );
>>>>>>> f9f96e4 (Added Code)

        // fetches the admin service resolver using service user.
        ResourceResolver resolver = resourceResolverFactory.getServiceResourceResolver(paramMap);
        return resolver;
    }


<<<<<<< HEAD
}
=======
}
>>>>>>> f9f96e4 (Added Code)
