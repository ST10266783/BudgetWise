package com.budgetwise.app.ui.expenses;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J$\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010&\u001a\u00020\'H\u0016J\u001a\u0010(\u001a\u00020\'2\u0006\u0010)\u001a\u00020\u001f2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010*\u001a\u00020\'H\u0002J\u0010\u0010+\u001a\u00020\'2\u0006\u0010,\u001a\u00020\u0010H\u0002J\b\u0010-\u001a\u00020\'H\u0002J\u0010\u0010.\u001a\u00020\'2\u0006\u0010/\u001a\u00020\u0012H\u0002J\u0010\u00100\u001a\u00020\'2\u0006\u00101\u001a\u000202H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0010\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\n0\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0010\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\u00120\u00120\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001b\u00a8\u00063"}, d2 = {"Lcom/budgetwise/app/ui/expenses/AddExpenseFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/budgetwise/app/databinding/FragmentAddExpenseBinding;", "binding", "getBinding", "()Lcom/budgetwise/app/databinding/FragmentAddExpenseBinding;", "cameraLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "categories", "", "Lcom/budgetwise/app/data/entities/Category;", "editingExpense", "Lcom/budgetwise/app/data/entities/Expense;", "galleryLauncher", "", "receiptPath", "selectedCatIndex", "", "tempPhotoUri", "Landroid/net/Uri;", "viewModel", "Lcom/budgetwise/app/ui/expenses/ExpenseViewModel;", "getViewModel", "()Lcom/budgetwise/app/ui/expenses/ExpenseViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "onViewCreated", "view", "openCamera", "populateForEdit", "expense", "saveExpense", "showReceiptPreview", "path", "showTimePicker", "target", "Lcom/google/android/material/textfield/TextInputEditText;", "app_debug"})
public final class AddExpenseFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.budgetwise.app.databinding.FragmentAddExpenseBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.budgetwise.app.data.entities.Category> categories;
    private int selectedCatIndex = 0;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String receiptPath;
    @org.jetbrains.annotations.Nullable()
    private android.net.Uri tempPhotoUri;
    @org.jetbrains.annotations.Nullable()
    private com.budgetwise.app.data.entities.Expense editingExpense;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> cameraLauncher = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> galleryLauncher = null;
    
    public AddExpenseFragment() {
        super();
    }
    
    private final com.budgetwise.app.databinding.FragmentAddExpenseBinding getBinding() {
        return null;
    }
    
    private final com.budgetwise.app.ui.expenses.ExpenseViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void populateForEdit(com.budgetwise.app.data.entities.Expense expense) {
    }
    
    private final void saveExpense() {
    }
    
    private final void showTimePicker(com.google.android.material.textfield.TextInputEditText target) {
    }
    
    private final void openCamera() {
    }
    
    private final void showReceiptPreview(java.lang.String path) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}