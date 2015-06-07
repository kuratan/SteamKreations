package de.kuratan.steamkreations.utils;

public interface IInitializer {
    boolean preInitialization();
    boolean initialization();
    boolean postInitialization();
}
