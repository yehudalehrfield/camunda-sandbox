package yehuda.camunda;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Desktop;
import java.net.URI;

@Component
@ExternalTaskSubscription("charge-card") // create a subscription for this topic name
public class ChargeCardWorker implements ExternalTaskHandler {

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService){

        // Get a process variable
        String item = externalTask.getVariable("item");
        Integer amount = externalTask.getVariable("amount");

        externalTaskService.complete(externalTask);

        Logger.getLogger("cardCharger")
                .log(Level.INFO, String.format("Charging credit card with an amount of $'%d' for the item '%s'...", amount, item));
    }

}
