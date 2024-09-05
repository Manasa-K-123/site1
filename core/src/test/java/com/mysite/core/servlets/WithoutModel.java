package com.mysite.core.servlets;


import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component(service = Servlet.class)
@SlingServletResourceTypes(resourceTypes = "mysite/components/page",selectors = {"add","sub"},extensions = {"txt","json","xml","html"})
public class WithoutModel extends SlingSafeMethodsServlet {
    public static final Logger log= LoggerFactory.getLogger(WithoutModel.class);
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String pagePath = request.getParameter("pagePath");
        log.info("pagePath:" +pagePath);
        if(pagePath==null){
            pagePath="/content/mysite/us/en";
        }
        List pages = getSource(pagePath, request);
        response.getWriter().write("all pages"+pages);
        log.info("pages"+pages);
    }
    public List getSource(String path, SlingHttpServletRequest request) {
        ResourceResolver resouceReslover = request.getResourceResolver();
        log.info("resourceResolver"+ resouceReslover);
        PageManager pageManager = resouceReslover.adaptTo(PageManager.class);
        log.info("pageManager"+ pageManager);
        Page pages = pageManager.getContainingPage(path);
        log.info("pages" + pages);
        log.info("pages in withoutModel class " + pages);
        Iterator<Page> children = pages.listChildren();
        log.info("children"+ children);
        List li = new ArrayList();
//        while(children.hasNext()){
        Page childPage = children.next();
        Resource resource = childPage.getContentResource();
        log.info("resourceType1 " + resource);
        Iterator<Resource> components = resource.listChildren().next().listChildren();
        while (components.hasNext()) {
            Iterator<Resource> children1 = components.next().listChildren();
            log.info("children1"+children1);
            while (children1.hasNext()) {
                Resource value = children1.next();
                String st = value.getValueMap().get("name", String.class);
                li.add(st);
            }
        }
//    }
        return li;

    }
}