package com.et.config;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleLoggingHandler  implements ObservationHandler<Observation.Context> {
    @Override
    public void onStart(Observation.Context context) {
        ObservationHandler.super.onStart(context);
        log.info("Starting context {} ", context);
    }

    @Override
    public void onError(Observation.Context context) {
        ObservationHandler.super.onError(context);
    }

    @Override
    public void onEvent(Observation.Event event, Observation.Context context) {
        ObservationHandler.super.onEvent(event, context);
    }

    @Override
    public void onScopeOpened(Observation.Context context) {
        ObservationHandler.super.onScopeOpened(context);
    }

    @Override
    public void onScopeClosed(Observation.Context context) {
        ObservationHandler.super.onScopeClosed(context);
    }

    @Override
    public void onScopeReset(Observation.Context context) {
        ObservationHandler.super.onScopeReset(context);
    }

    @Override
    public void onStop(Observation.Context context) {

        ObservationHandler.super.onStop(context);
        log.info("Stopping context {} ", context);
    }

    @Override
    public boolean supportsContext(Observation.Context context) {
        return true;
    }
}
