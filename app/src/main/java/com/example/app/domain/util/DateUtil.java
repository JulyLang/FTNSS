package com.example.app.domain.util;

import androidx.core.util.Pair;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public class DateUtil {

    public static Pair<Long, Long> getDayStartAndEndTimestamps() {
        ZoneId zoneId = TimeZone.getDefault().toZoneId();
        LocalDate today = LocalDate.now(zoneId);

        ZonedDateTime zdtStart = today.atStartOfDay(zoneId);
        ZonedDateTime zdtStop = today.plusDays(1).atStartOfDay(zoneId);

        return new Pair<Long, Long>(zdtStart.toInstant().toEpochMilli(), zdtStop.toInstant().toEpochMilli());
    }
}