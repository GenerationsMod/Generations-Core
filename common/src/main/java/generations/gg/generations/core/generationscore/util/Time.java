package generations.gg.generations.core.generationscore.util;

import net.minecraft.network.FriendlyByteBuf;
import org.jetbrains.annotations.Range;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalTime;

public class Time implements Comparable<Time> {

    private byte hour, minute, second;

    private Time(@Range(from = 0, to = 23) byte hour,
                @Range(from = 0, to = 59) byte minute,
                @Range(from = 0, to = 59) byte second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public static Time of(@Range(from = 0, to = 23) byte hour,
                   @Range(from = 0, to = 59) byte minute,
                   @Range(from = 0, to = 59) byte second) {
        return new Time(hour, minute, second);
    }

    public static Time of(LocalTime time) {
        return new Time((byte) time.getHour(), (byte) time.getMinute(), (byte) time.getSecond());
    }

    public static Time of(Instant instant) {
        LocalTime time = LocalTime.ofInstant(instant, Clock.systemDefaultZone().getZone());
        return new Time((byte) time.getHour(), (byte) time.getMinute(), (byte) time.getSecond());
    }

    public static Time now() {
        return Time.of(LocalTime.now());
    }

    public static Time decode(FriendlyByteBuf buffer) {
        return of(buffer.readByte(), buffer.readByte(), buffer.readByte());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeByte(hour);
        buffer.writeByte(minute);
        buffer.writeByte(second);
    }

    public Time add(Time time) {
        int hour = this.hour + time.hour;
        int minute = this.minute + time.minute;
        int second = this.second + time.second;
        if (second >= 60) {
            second = second - 60;
            minute++;
        }
        if (minute >= 60) {
            minute = minute - 60;
            hour++;
        }
        hour %= 24;

        return new Time((byte) hour, (byte) minute, (byte) second);
    }

    public void addTo(Time time) {
        int hour = this.hour + time.hour;
        int minute = this.minute + time.minute;
        int second = this.second + time.second;
        if (second >= 60) {
            second = second - 60;
            minute++;
        }
        if (minute >= 60) {
            minute = minute - 60;
            hour++;
        }
        hour %= 24;

        this.hour   = (byte) hour;
        this.minute = (byte) minute;
        this.second = (byte) second;
    }

    public void setNow() {
        var now = LocalTime.now();
        this.hour = (byte) now.getHour();
        this.minute = (byte) now.getMinute();
        this.second = (byte) now.getSecond();
    }

    public byte getHour() {
        return hour;
    }

    public void setHour(byte hour) {
        this.hour = hour;
    }

    public byte getMinute() {
        return minute;
    }

    public void setMinute(byte minute) {
        this.minute = minute;
    }

    public byte getSecond() {
        return second;
    }

    public void setSecond(byte second) {
        this.second = second;
    }

    public int toInt() {
        return (hour << 16) + (minute << 8) + second;
    }

    public static Time fromInt(int time) {
        byte hours = (byte) ((time & 0xFF0000) >> 16);
        byte minutes = (byte) ((time & 0xFF00) >> 8);
        byte seconds = (byte) (time & 0xFF);
        return new Time(hours, minutes, seconds);
    }

    public boolean isAfter(Time other) {
        return compareTo(other) > 0;
    }

    public boolean isAfter(LocalTime other) {
        return compareTo(other) > 0;
    }

    public boolean isBefore(Time other) {
        return compareTo(other) < 0;
    }

    public boolean isBefore(LocalTime other) {
        return compareTo(other) < 0;
    }

    @Override
    public int compareTo(Time other) {
        int cmp = Byte.compare(hour, other.hour);
        if (cmp == 0) {
            cmp = Byte.compare(minute, other.minute);
            if (cmp == 0) {
                cmp = Byte.compare(second, other.second);
            }
        }
        return cmp;
    }

    public int compareTo(LocalTime other) {
        int cmp = Integer.compare(hour, other.getHour());
        if (cmp == 0) {
            cmp = Integer.compare(minute, other.getMinute());
            if (cmp == 0) {
                cmp = Integer.compare(second, other.getSecond());
            }
        }
        return cmp;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Time other)
                && hour == other.hour
                && minute == other.minute
                && second == other.second;
    }
}
