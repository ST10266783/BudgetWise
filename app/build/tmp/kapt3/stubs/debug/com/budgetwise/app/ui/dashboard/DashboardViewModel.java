package com.budgetwise.app.ui.dashboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u000bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0011\u00a8\u0006\""}, d2 = {"Lcom/budgetwise/app/ui/dashboard/DashboardViewModel;", "Landroidx/lifecycle/ViewModel;", "expenseRepo", "Lcom/budgetwise/app/data/repository/ExpenseRepository;", "goalRepo", "Lcom/budgetwise/app/data/repository/GoalRepository;", "catRepo", "Lcom/budgetwise/app/data/repository/CategoryRepository;", "(Lcom/budgetwise/app/data/repository/ExpenseRepository;Lcom/budgetwise/app/data/repository/GoalRepository;Lcom/budgetwise/app/data/repository/CategoryRepository;)V", "_userId", "Landroidx/lifecycle/MutableLiveData;", "", "categories", "Landroidx/lifecycle/LiveData;", "", "Lcom/budgetwise/app/data/entities/Category;", "getCategories", "()Landroidx/lifecycle/LiveData;", "categoryTotals", "Lcom/budgetwise/app/data/db/CategoryTotal;", "getCategoryTotals", "endDate", "", "goal", "Lcom/budgetwise/app/data/entities/Goal;", "getGoal", "month", "startDate", "totalSpent", "", "getTotalSpent", "init", "", "userId", "app_debug"})
public final class DashboardViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.budgetwise.app.data.repository.ExpenseRepository expenseRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.budgetwise.app.data.repository.GoalRepository goalRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.budgetwise.app.data.repository.CategoryRepository catRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> _userId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String startDate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String endDate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String month = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> totalSpent = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.budgetwise.app.data.db.CategoryTotal>> categoryTotals = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.budgetwise.app.data.entities.Category>> categories = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.budgetwise.app.data.entities.Goal> goal = null;
    
    public DashboardViewModel(@org.jetbrains.annotations.NotNull()
    com.budgetwise.app.data.repository.ExpenseRepository expenseRepo, @org.jetbrains.annotations.NotNull()
    com.budgetwise.app.data.repository.GoalRepository goalRepo, @org.jetbrains.annotations.NotNull()
    com.budgetwise.app.data.repository.CategoryRepository catRepo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getTotalSpent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.budgetwise.app.data.db.CategoryTotal>> getCategoryTotals() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.budgetwise.app.data.entities.Category>> getCategories() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.budgetwise.app.data.entities.Goal> getGoal() {
        return null;
    }
    
    public final void init(int userId) {
    }
}