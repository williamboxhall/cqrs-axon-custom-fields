package org.example.write.domain;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.example.events.BooleanFieldConfigured;
import org.example.write.commands.ConfigureBooleanField;

public class BooleanField extends AbstractAnnotatedAggregateRoot<String> {
    @AggregateIdentifier
    private String guid;

    private BooleanField() {
    }

    @EventHandler
    private void on(BooleanFieldConfigured event) {
        this.guid = event.getGuid();
    }

    @CommandHandler
    public BooleanField(ConfigureBooleanField command) {
        apply(new BooleanFieldConfigured(command.getGuid(), command.getName(), command.getLabel(), command.getHintText()));
    }
}