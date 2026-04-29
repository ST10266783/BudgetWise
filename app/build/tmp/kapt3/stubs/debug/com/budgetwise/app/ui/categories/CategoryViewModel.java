package com.budgetwise.app.ui.categories;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J%\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u000f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u00a2\u0006\u0002\u0010\u0018J\u000e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\nJ-\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u000f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u00a2\u0006\u0002\u0010\u001cR\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/budgetwise/app/ui/categories/CategoryViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/budgetwise/app/data/repository/CategoryRepository;", "userId", "", "(Lcom/budgetwise/app/data/repository/CategoryRepository;I)V", "categories", "Landroidx/lifecycle/LiveData;", "", "Lcom/budgetwise/app/data/entities/Category;", "getCategories", "()Landroidx/lifecycle/LiveData;", "operationResult", "Landroidx/lifecycle/MutableLiveData;", "", "getOperationResult", "()Landroidx/lifecycle/MutableLiveData;", "addCategory", "", "name", "colour", "monthlyLimit", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;)V", "deleteCategory", "category", "updateCategory", "(Lcom/budgetwise/app/data/entities/Category;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;)V", "app_debug"})
public final class CategoryViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.budgetwise.app.data.repository.CategoryRepository repository = null;
    private final int userId = 0;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.budgetwise.app.data.entities.Category>> categories = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> operationResult = null;
    
    public CategoryViewModel(@org.jetbrains.annotations.NotNull()
    com.budgetwise.app.data.repository.CategoryRepository repository, int userId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.budgetwise.app.data.entities.Category>> getCategories() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getOperationResult() {
        return null;
    }
    
    public final void addCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String colour, @org.jetbrains.annotations.Nullable()
    java.lang.Double monthlyLimit) {
    }
    
    public final void updateCategory(@org.jetbrains.annotations.NotNull()
    com.budgetwise.app.data.entities.Category category, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String colour, @org.jetbrains.annotations.Nullable()
    java.lang.Double monthlyLimit) {
    }
    
    public final void deleteCategory(@org.jetbrains.annotations.NotNull()
    com.budgetwise.app.data.entities.Category category) {
    }
}