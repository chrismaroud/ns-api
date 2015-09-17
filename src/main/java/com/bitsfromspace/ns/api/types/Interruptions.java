package com.bitsfromspace.ns.api.types;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */
@XmlRootElement(name = "Storingen")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Interruptions {
    private List<Interruption> scheduledInterruptions;
    private List<Interruption> unscheduledInterruptions;

    public List<Interruption> getScheduledInterruptions() {
        return scheduledInterruptions;
    }

    @XmlElement(name = "Storing")
    @XmlElementWrapper(name = "Gepland")
    public void setScheduledInterruptions(List<Interruption> scheduledInterruptions) {
        this.scheduledInterruptions = scheduledInterruptions;
    }

    public List<Interruption> getUnscheduledInterruptions() {
        return unscheduledInterruptions;
    }

    @XmlElement(name = "Storing")
    @XmlElementWrapper(name = "Ongepland")
    public void setUnscheduledInterruptions(List<Interruption> unscheduledInterruptions) {
        this.unscheduledInterruptions = unscheduledInterruptions;
    }
}
