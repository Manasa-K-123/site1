package com.mysite.core.service;


import com.day.cq.wcm.api.Page;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component (service = DemoServiceB.class,immediate=true)
public class DemoServiceBImpl implements DemoServiceB{
    private static final Logger LOG= LoggerFactory.getLogger(DemoServiceBImpl.class);

    @Reference(target="(component.name=com.mysite.core.service.MultiServiceAImpl)")
    MultiService multiService;


    public String getNameWithReference(){
        return "Response coming from"+multiService.getName();
    }



    @Reference
    DemoService demoService;

    @Override
    public List<String> getPages() {
        List<String> listpages=new ArrayList<String>();

        try{
            Iterator<Page>pages =demoService.getPages();
            while(pages.hasNext()){
                listpages.add(pages.next().getTitle());

            }
            return listpages;
        }catch(Exception e){
            LOG.info("\n EXception {}",e.getMessage());
        }
        return null;
    }
}
