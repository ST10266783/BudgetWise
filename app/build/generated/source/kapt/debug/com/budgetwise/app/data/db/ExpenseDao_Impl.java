package com.budgetwise.app.data.db;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.budgetwise.app.data.entities.Expense;
import java.lang.Class;
import java.lang.Double;
import java.lang.Long;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class ExpenseDao_Impl implements ExpenseDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<Expense> __insertAdapterOfExpense;

  private final EntityDeleteOrUpdateAdapter<Expense> __deleteAdapterOfExpense;

  private final EntityDeleteOrUpdateAdapter<Expense> __updateAdapterOfExpense;

  public ExpenseDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfExpense = new EntityInsertAdapter<Expense>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `expenses` (`id`,`userId`,`categoryId`,`amount`,`date`,`startTime`,`endTime`,`description`,`receiptPath`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Expense entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getUserId());
        statement.bindLong(3, entity.getCategoryId());
        statement.bindDouble(4, entity.getAmount());
        if (entity.getDate() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getDate());
        }
        if (entity.getStartTime() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getStartTime());
        }
        if (entity.getEndTime() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getEndTime());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getDescription());
        }
        if (entity.getReceiptPath() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getReceiptPath());
        }
        statement.bindLong(10, entity.getCreatedAt());
      }
    };
    this.__deleteAdapterOfExpense = new EntityDeleteOrUpdateAdapter<Expense>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `expenses` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Expense entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfExpense = new EntityDeleteOrUpdateAdapter<Expense>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `expenses` SET `id` = ?,`userId` = ?,`categoryId` = ?,`amount` = ?,`date` = ?,`startTime` = ?,`endTime` = ?,`description` = ?,`receiptPath` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Expense entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getUserId());
        statement.bindLong(3, entity.getCategoryId());
        statement.bindDouble(4, entity.getAmount());
        if (entity.getDate() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getDate());
        }
        if (entity.getStartTime() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getStartTime());
        }
        if (entity.getEndTime() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getEndTime());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getDescription());
        }
        if (entity.getReceiptPath() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getReceiptPath());
        }
        statement.bindLong(10, entity.getCreatedAt());
        statement.bindLong(11, entity.getId());
      }
    };
  }

  @Override
  public Object insertExpense(final Expense expense, final Continuation<? super Long> $completion) {
    if (expense == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      return __insertAdapterOfExpense.insertAndReturnId(_connection, expense);
    }, $completion);
  }

  @Override
  public Object deleteExpense(final Expense expense, final Continuation<? super Unit> $completion) {
    if (expense == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfExpense.handle(_connection, expense);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object updateExpense(final Expense expense, final Continuation<? super Unit> $completion) {
    if (expense == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfExpense.handle(_connection, expense);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public LiveData<List<Expense>> getExpensesForPeriod(final int userId, final String startDate,
      final String endDate) {
    final String _sql = "SELECT * FROM expenses WHERE userId = ? AND date BETWEEN ? AND ? ORDER BY date DESC, createdAt DESC";
    return __db.getInvalidationTracker().createLiveData(new String[] {"expenses"}, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, userId);
        _argIndex = 2;
        if (startDate == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, startDate);
        }
        _argIndex = 3;
        if (endDate == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, endDate);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfCategoryId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "categoryId");
        final int _columnIndexOfAmount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "amount");
        final int _columnIndexOfDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "date");
        final int _columnIndexOfStartTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "startTime");
        final int _columnIndexOfEndTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "endTime");
        final int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _columnIndexOfReceiptPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "receiptPath");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final List<Expense> _result = new ArrayList<Expense>();
        while (_stmt.step()) {
          final Expense _item;
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final int _tmpUserId;
          _tmpUserId = (int) (_stmt.getLong(_columnIndexOfUserId));
          final int _tmpCategoryId;
          _tmpCategoryId = (int) (_stmt.getLong(_columnIndexOfCategoryId));
          final double _tmpAmount;
          _tmpAmount = _stmt.getDouble(_columnIndexOfAmount);
          final String _tmpDate;
          if (_stmt.isNull(_columnIndexOfDate)) {
            _tmpDate = null;
          } else {
            _tmpDate = _stmt.getText(_columnIndexOfDate);
          }
          final String _tmpStartTime;
          if (_stmt.isNull(_columnIndexOfStartTime)) {
            _tmpStartTime = null;
          } else {
            _tmpStartTime = _stmt.getText(_columnIndexOfStartTime);
          }
          final String _tmpEndTime;
          if (_stmt.isNull(_columnIndexOfEndTime)) {
            _tmpEndTime = null;
          } else {
            _tmpEndTime = _stmt.getText(_columnIndexOfEndTime);
          }
          final String _tmpDescription;
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription);
          }
          final String _tmpReceiptPath;
          if (_stmt.isNull(_columnIndexOfReceiptPath)) {
            _tmpReceiptPath = null;
          } else {
            _tmpReceiptPath = _stmt.getText(_columnIndexOfReceiptPath);
          }
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          _item = new Expense(_tmpId,_tmpUserId,_tmpCategoryId,_tmpAmount,_tmpDate,_tmpStartTime,_tmpEndTime,_tmpDescription,_tmpReceiptPath,_tmpCreatedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public LiveData<List<CategoryTotal>> getTotalPerCategory(final int userId, final String startDate,
      final String endDate) {
    final String _sql = "SELECT categoryId, SUM(amount) as total FROM expenses WHERE userId = ? AND date BETWEEN ? AND ? GROUP BY categoryId";
    return __db.getInvalidationTracker().createLiveData(new String[] {"expenses"}, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, userId);
        _argIndex = 2;
        if (startDate == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, startDate);
        }
        _argIndex = 3;
        if (endDate == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, endDate);
        }
        final int _columnIndexOfCategoryId = 0;
        final int _columnIndexOfTotal = 1;
        final List<CategoryTotal> _result = new ArrayList<CategoryTotal>();
        while (_stmt.step()) {
          final CategoryTotal _item;
          final int _tmpCategoryId;
          _tmpCategoryId = (int) (_stmt.getLong(_columnIndexOfCategoryId));
          final double _tmpTotal;
          _tmpTotal = _stmt.getDouble(_columnIndexOfTotal);
          _item = new CategoryTotal(_tmpCategoryId,_tmpTotal);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public LiveData<Double> getTotalSpentForPeriod(final int userId, final String startDate,
      final String endDate) {
    final String _sql = "SELECT COALESCE(SUM(amount), 0.0) FROM expenses WHERE userId = ? AND date BETWEEN ? AND ?";
    return __db.getInvalidationTracker().createLiveData(new String[] {"expenses"}, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, userId);
        _argIndex = 2;
        if (startDate == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, startDate);
        }
        _argIndex = 3;
        if (endDate == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, endDate);
        }
        final Double _result;
        if (_stmt.step()) {
          final Double _tmp;
          if (_stmt.isNull(0)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getDouble(0);
          }
          _result = _tmp;
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Object getExpenseById(final int id, final Continuation<? super Expense> $completion) {
    final String _sql = "SELECT * FROM expenses WHERE id = ? LIMIT 1";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfCategoryId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "categoryId");
        final int _columnIndexOfAmount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "amount");
        final int _columnIndexOfDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "date");
        final int _columnIndexOfStartTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "startTime");
        final int _columnIndexOfEndTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "endTime");
        final int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _columnIndexOfReceiptPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "receiptPath");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final Expense _result;
        if (_stmt.step()) {
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final int _tmpUserId;
          _tmpUserId = (int) (_stmt.getLong(_columnIndexOfUserId));
          final int _tmpCategoryId;
          _tmpCategoryId = (int) (_stmt.getLong(_columnIndexOfCategoryId));
          final double _tmpAmount;
          _tmpAmount = _stmt.getDouble(_columnIndexOfAmount);
          final String _tmpDate;
          if (_stmt.isNull(_columnIndexOfDate)) {
            _tmpDate = null;
          } else {
            _tmpDate = _stmt.getText(_columnIndexOfDate);
          }
          final String _tmpStartTime;
          if (_stmt.isNull(_columnIndexOfStartTime)) {
            _tmpStartTime = null;
          } else {
            _tmpStartTime = _stmt.getText(_columnIndexOfStartTime);
          }
          final String _tmpEndTime;
          if (_stmt.isNull(_columnIndexOfEndTime)) {
            _tmpEndTime = null;
          } else {
            _tmpEndTime = _stmt.getText(_columnIndexOfEndTime);
          }
          final String _tmpDescription;
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription);
          }
          final String _tmpReceiptPath;
          if (_stmt.isNull(_columnIndexOfReceiptPath)) {
            _tmpReceiptPath = null;
          } else {
            _tmpReceiptPath = _stmt.getText(_columnIndexOfReceiptPath);
          }
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          _result = new Expense(_tmpId,_tmpUserId,_tmpCategoryId,_tmpAmount,_tmpDate,_tmpStartTime,_tmpEndTime,_tmpDescription,_tmpReceiptPath,_tmpCreatedAt);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
