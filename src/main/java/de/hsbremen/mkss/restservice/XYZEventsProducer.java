package de.hsbremen.mkss.restservice;


import de.hsbremen.mkss.restservice.events.CrudEventProducer;
import de.hsbremen.mkss.restservice.events.Event;
import de.hsbremen.mkss.restservice.events.EventWithPayload;
import de.hsbremen.mkss.restservice.models.Order;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class XYZEventsProducer implements CrudEventProducer<Order> {

	private AmqpTemplate amqpTemplate;

    @Value("${my.rabbitmq.an.exchange}")
    String anExchangeName;

    @Value("${my.rabbitmq.a.routing.key}")
    String aRoutingKeyName;

	public XYZEventsProducer(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}


	private EventWithPayload<Order> buildEvent(Event.EventType type, Order payload) {
		EventWithPayload<Order> event = EventWithPayload.<Order> builder()
				.type(type)
				.payload(payload)
				.build();
		return event;
	}

	@Override
	public void emitCreateEvent(Order payload) {
		EventWithPayload<Order> event = buildEvent(Event.EventType.CREATED, payload);
	
		// TODO: send event to RabbitMQ exchange
		System.out.println("before sen");
		System.out.println("Sent event using exchange " + anExchangeName + " with routing key " + aRoutingKeyName);

		amqpTemplate.convertAndSend("amq.fanout","","'someevent");

		//System.out.println("Sent event = " + event + " using exchange " + anExchangeName + " with routing key " + aRoutingKeyName);
	}

	@Override
	public void emitUpdateEvent(Order payload) {
		// TODO: Implementation for update events (e.g. changed order)
		EventWithPayload<Order> event = buildEvent(Event.EventType.CHANGED, payload);
		amqpTemplate.convertAndSend(anExchangeName, aRoutingKeyName, event);
	}

	@Override
	public void emitDeleteEvent(Order payload) {
		// TODO: Implementation for delete events (e.g. deleted order)
		EventWithPayload<Order> event = buildEvent(Event.EventType.DELETED, payload);
		amqpTemplate.convertAndSend(anExchangeName, aRoutingKeyName, event);
	}
}
