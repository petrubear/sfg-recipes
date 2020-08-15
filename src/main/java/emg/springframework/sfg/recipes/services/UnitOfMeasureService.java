package emg.springframework.sfg.recipes.services;

import emg.springframework.sfg.recipes.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}
