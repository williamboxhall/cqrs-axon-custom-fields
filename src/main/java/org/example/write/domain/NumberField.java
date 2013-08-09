package org.example.write.domain;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.example.events.NumberFieldConfigured;
import org.example.write.commands.ConfigureNumberField;

public class NumberField extends AbstractAnnotatedAggregateRoot<String> {
    @AggregateIdentifier
    private String guid;

    private NumberField() {
    }

    @EventHandler
    private void on(NumberFieldConfigured event) {
        this.guid = event.getGuid();
    }

    @CommandHandler
    public NumberField(ConfigureNumberField command) {
        UnitOfMeasure unitOfMeasure = command.getUnitOfMeasure();
        apply(new NumberFieldConfigured(command.getGuid(), command.getName(), command.getLabel(), command.getHintText(),
                unitOfMeasure.getQuantity(), unitOfMeasure.getName(), unitOfMeasure.getSymbol()));
    }
}