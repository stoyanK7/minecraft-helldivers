package com.github.stoyank7.helldivers;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StratagemScheduler {
    public static List<List> stratagems = new ArrayList<>();
    public static void initialize() {
        ServerTickEvents.START_SERVER_TICK.register((server) -> {
            for (int i = 0; i < stratagems.size(); i++) {
                int ticks = (int) stratagems.get(i).get(0);
                Runnable task = (Runnable) stratagems.get(i).get(1);
                ticks--;
                stratagems.set(i, Arrays.asList(ticks, task));
                if (ticks <= 0) {
                    task.run();
                    stratagems.remove(i);
                }
            }
        });
    }

    public static void scheduleStratagem(int ticks, Runnable task) {
        stratagems.add(Arrays.asList(ticks, task));
    }
}
