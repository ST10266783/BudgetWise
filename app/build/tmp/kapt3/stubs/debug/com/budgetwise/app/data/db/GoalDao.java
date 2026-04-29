package com.budgetwise.app.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\'J \u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u0004H\u00a7@\u00a2\u0006\u0002\u0010\u000e\u00a8\u0006\u0011"}, d2 = {"Lcom/budgetwise/app/data/db/GoalDao;", "", "getGoalForMonth", "Landroidx/lifecycle/LiveData;", "Lcom/budgetwise/app/data/entities/Goal;", "userId", "", "month", "", "getGoalForMonthSnapshot", "(ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertGoal", "", "goal", "(Lcom/budgetwise/app/data/entities/Goal;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateGoal", "", "app_debug"})
@androidx.room.Dao()
public abstract interface GoalDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertGoal(@org.jetbrains.annotations.NotNull()
    com.budgetwise.app.data.entities.Goal goal, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateGoal(@org.jetbrains.annotations.NotNull()
    com.budgetwise.app.data.entities.Goal goal, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM goals WHERE userId = :userId AND month = :month LIMIT 1")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<com.budgetwise.app.data.entities.Goal> getGoalForMonth(int userId, @org.jetbrains.annotations.NotNull()
    java.lang.String month);
    
    @androidx.room.Query(value = "SELECT * FROM goals WHERE userId = :userId AND month = :month LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getGoalForMonthSnapshot(int userId, @org.jetbrains.annotations.NotNull()
    java.lang.String month, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.budgetwise.app.data.entities.Goal> $completion);
}