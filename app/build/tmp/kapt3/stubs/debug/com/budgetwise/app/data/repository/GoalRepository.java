package com.budgetwise.app.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/budgetwise/app/data/repository/GoalRepository;", "", "goalDao", "Lcom/budgetwise/app/data/db/GoalDao;", "(Lcom/budgetwise/app/data/db/GoalDao;)V", "getGoalForMonth", "Landroidx/lifecycle/LiveData;", "Lcom/budgetwise/app/data/entities/Goal;", "userId", "", "month", "", "saveGoal", "", "goal", "(Lcom/budgetwise/app/data/entities/Goal;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class GoalRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.budgetwise.app.data.db.GoalDao goalDao = null;
    
    public GoalRepository(@org.jetbrains.annotations.NotNull()
    com.budgetwise.app.data.db.GoalDao goalDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.budgetwise.app.data.entities.Goal> getGoalForMonth(int userId, @org.jetbrains.annotations.NotNull()
    java.lang.String month) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveGoal(@org.jetbrains.annotations.NotNull()
    com.budgetwise.app.data.entities.Goal goal, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}