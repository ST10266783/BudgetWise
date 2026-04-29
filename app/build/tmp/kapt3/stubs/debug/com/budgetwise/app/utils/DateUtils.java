package com.budgetwise.app.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\u0004J\b\u0010\b\u001a\u00020\tH\u0002J\u0006\u0010\n\u001a\u00020\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004J\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0004J\b\u0010\u0010\u001a\u00020\tH\u0002J\u0006\u0010\u0011\u001a\u00020\u0004J\u0006\u0010\u0012\u001a\u00020\u0004J\u000e\u0010\u0013\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004J\u0006\u0010\u0014\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/budgetwise/app/utils/DateUtils;", "", "()V", "DATE_FORMAT", "", "DISPLAY_FORMAT", "MONTH_FORMAT", "currentMonth", "dateFormat", "Ljava/text/SimpleDateFormat;", "endOfCurrentMonth", "isValidDate", "", "dateStr", "isValidTime", "timeStr", "monthFormat", "startOfCurrentMonth", "startOfWeek", "toDisplayFormat", "today", "app_debug"})
public final class DateUtils {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String DATE_FORMAT = "yyyy-MM-dd";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String MONTH_FORMAT = "yyyy-MM";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String DISPLAY_FORMAT = "dd MMM yyyy";
    @org.jetbrains.annotations.NotNull()
    public static final com.budgetwise.app.utils.DateUtils INSTANCE = null;
    
    private DateUtils() {
        super();
    }
    
    private final java.text.SimpleDateFormat dateFormat() {
        return null;
    }
    
    private final java.text.SimpleDateFormat monthFormat() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String today() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String currentMonth() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String startOfCurrentMonth() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String endOfCurrentMonth() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String startOfWeek() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String toDisplayFormat(@org.jetbrains.annotations.NotNull()
    java.lang.String dateStr) {
        return null;
    }
    
    public final boolean isValidDate(@org.jetbrains.annotations.NotNull()
    java.lang.String dateStr) {
        return false;
    }
    
    public final boolean isValidTime(@org.jetbrains.annotations.NotNull()
    java.lang.String timeStr) {
        return false;
    }
}