package com.mysite.core.models;



import com.day.cq.wcm.api.Page;

import java.util.Iterator;
import java.util.List;

public interface ServiceDemo2 {
    public Iterator<Page> getPageList();
    public List<String> getPageTitleList();
}
