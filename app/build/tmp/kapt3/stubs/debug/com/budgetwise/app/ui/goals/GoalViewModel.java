package com.budgetwise.app.ui.goals;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u000eR\u0019\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/budgetwise/app/ui/goals/GoalViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/budgetwise/app/data/repository/GoalRepository;", "userId", "", "(Lcom/budgetwise/app/data/repository/GoalRepository;I)V", "currentGoal", "Landroidx/lifecycle/LiveData;", "Lcom/budgetwise/app/data/entities/Goal;", "getCurrentGoal", "()Landroidx/lifecycle/LiveData;", "operationResult", "Landroidx/lifecycle/MutableLiveData;", "", "getOperationResult", "()Landroidx/lifecycle/MutableLiveData;", "saveGoal", "", "minStr", "maxStr", "app_debug"})
public final class GoalViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.budgetwise.app.data.repository.GoalRepository repository = null;
    private final int userId = 0;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> operationResult = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.budgetwise.app.data.entities.Goal> currentGoal = null;
    
    public GoalViewModel(@org.jetbrains.annotations.NotNull()
    com.budgetwise.app.data.repository.GoalRepository repository, int userId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getOperationResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.budgetwise.app.data.entities.Goal> getCurrentGoal() {
        return null;
    }
    
    public final void saveGoal(@org.jetbrains.annotations.NotNull()
    java.lang.String minStr, @org.jetbrains.annotations.NotNull()
    java.lang.String maxStr) {
    }
}