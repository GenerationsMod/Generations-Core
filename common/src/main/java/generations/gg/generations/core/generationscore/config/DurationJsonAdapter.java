package generations.gg.generations.core.generationscore.config;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.Duration;

public class DurationJsonAdapter implements JsonSerializer<Duration>, JsonDeserializer<Duration> {

    @Override
    public Duration deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String durationString = json.getAsString();

        if (durationString == null || durationString.isEmpty()) {
            return null;
        }

        long days = 0;
        long hours = 0;
        long minutes = 0;
        long seconds = 0;

        int startIndex = 0;
        int endIndex = 0;

        // Parse the duration string
        while (endIndex < durationString.length()) {
            char c = durationString.charAt(endIndex);

            if (Character.isDigit(c)) {
                endIndex++;
                continue;
            }

            long value = Long.parseLong(durationString.substring(startIndex, endIndex));

            switch (c) {
                case 'd':
                    days = value;
                    break;
                case 'h':
                    hours = value;
                    break;
                case 'm':
                    minutes = value;
                    break;
                case 's':
                    seconds = value;
                    break;
                default:
                    throw new JsonParseException("Invalid duration format");
            }

            endIndex++;
            startIndex = endIndex;
        }

        return Duration.ofDays(days).plusHours(hours).plusMinutes(minutes).plusSeconds(seconds);
    }

    @Override
    public JsonElement serialize(Duration duration, Type typeOfSrc, JsonSerializationContext context) {
        if (duration == null) {
            return JsonNull.INSTANCE;
        }

        long days = duration.toDaysPart();
        long hours = duration.toHoursPart();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        StringBuilder durationString = new StringBuilder();

        if (days > 0) {
            durationString.append(days).append("d");
        }
        if (hours > 0) {
            durationString.append(hours).append("h");
        }
        if (minutes > 0) {
            durationString.append(minutes).append("m");
        }
        if (seconds > 0 || durationString.length() == 0) {
            durationString.append(seconds).append("s");
        }

        return new JsonPrimitive(durationString.toString());
    }
}