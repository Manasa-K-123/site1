package com.mysite.core.schedulers;


import com.mysite.core.config.SchedulerConfiguration;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.*;

import org.osgi.service.metatype.annotations.Designate;

        import org.osgi.service.metatype.annotations.Designate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(service = Runnable.class, immediate = true)
@Designate(ocd = SchedulerConfiguration.class)
public class GeeksScheduler implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(GeeksScheduler.class);

    private int schedulerId;

    @Reference
    private Scheduler scheduler;

    @Activate
    protected void activate(SchedulerConfiguration config) {
        LOG.info("Activating scheduler with configuration: {}", config.schedulerName());
        schedulerId = config.schedulerName().hashCode();
        addScheduler(config);
    }

    @Deactivate
    protected void deactivate(SchedulerConfiguration config) {
        LOG.info("Deactivating scheduler with ID: {}", schedulerId);
        removeScheduler();
    }

    protected void removeScheduler() {
        scheduler.unschedule(String.valueOf(schedulerId));
    }

    protected void addScheduler(SchedulerConfiguration config) {
        ScheduleOptions scheduleOptions = scheduler.EXPR(config.cronExpression());
        scheduleOptions.name(String.valueOf(schedulerId));
        scheduler.schedule(this, scheduleOptions);
        //scheduleOptions.canRunConcurrently(true);
        LOG.info("scheduler method");
        ScheduleOptions scheduleOptions1 =scheduler.NOW();
        scheduler.schedule(this, scheduleOptions1);
    }

    @Override
    public void run() {
        LOG.info("Scheduler run method called.");
    }
}
