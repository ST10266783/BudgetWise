package com.budgetwise.app.data.db;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.budgetwise.app.data.entities.Goal;
import java.lang.Class;
import java.lang.Long;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class GoalDao_Impl implements GoalDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<Goal> __insertAdapterOfGoal;

  private final EntityDeleteOrUpdateAdapter<Goal> __updateAdapterOfGoal;

  public GoalDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfGoal = new EntityInsertAdapter<Goal>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `goals` (`id`,`userId`,`month`,`minimumGoal`,`maximumGoal`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Goal entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getUserId());
        if (entity.getMonth() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getMonth());
        }
        statement.bindDouble(4, entity.getMinimumGoal());
        statement.bindDouble(5, entity.getMaximumGoal());
      }
    };
    this.__updateAdapterOfGoal = new EntityDeleteOrUpdateAdapter<Goal>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `goals` SET `id` = ?,`userId` = ?,`month` = ?,`minimumGoal` = ?,`maximumGoal` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Goal entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getUserId());
        if (entity.getMonth() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getMonth());
        }
        statement.bindDouble(4, entity.getMinimumGoal());
        statement.bindDouble(5, entity.getMaximumGoal());
        statement.bindLong(6, entity.getId());
      }
    };
  }

  @Override
  public Object insertGoal(final Goal goal, final Continuation<? super Long> $completion) {
    if (goal == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      return __insertAdapterOfGoal.insertAndReturnId(_connection, goal);
    }, $completion);
  }

  @Override
  public Object updateGoal(final Goal goal, final Continuation<? super Unit> $completion) {
    if (goal == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfGoal.handle(_connection, goal);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public LiveData<Goal> getGoalForMonth(final int userId, final String month) {
    final String _sql = "SELECT * FROM goals WHERE userId = ? AND month = ? LIMIT 1";
    return __db.getInvalidationTracker().createLiveData(new String[] {"goals"}, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, userId);
        _argIndex = 2;
        if (month == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, month);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfMonth = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "month");
        final int _columnIndexOfMinimumGoal = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "minimumGoal");
        final int _columnIndexOfMaximumGoal = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "maximumGoal");
        final Goal _result;
        if (_stmt.step()) {
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final int _tmpUserId;
          _tmpUserId = (int) (_stmt.getLong(_columnIndexOfUserId));
          final String _tmpMonth;
          if (_stmt.isNull(_columnIndexOfMonth)) {
            _tmpMonth = null;
          } else {
            _tmpMonth = _stmt.getText(_columnIndexOfMonth);
          }
          final double _tmpMinimumGoal;
          _tmpMinimumGoal = _stmt.getDouble(_columnIndexOfMinimumGoal);
          final double _tmpMaximumGoal;
          _tmpMaximumGoal = _stmt.getDouble(_columnIndexOfMaximumGoal);
          _result = new Goal(_tmpId,_tmpUserId,_tmpMonth,_tmpMinimumGoal,_tmpMaximumGoal);
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
  public Object getGoalForMonthSnapshot(final int userId, final String month,
      final Continuation<? super Goal> $completion) {
    final String _sql = "SELECT * FROM goals WHERE userId = ? AND month = ? LIMIT 1";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, userId);
        _argIndex = 2;
        if (month == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, month);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfMonth = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "month");
        final int _columnIndexOfMinimumGoal = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "minimumGoal");
        final int _columnIndexOfMaximumGoal = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "maximumGoal");
        final Goal _result;
        if (_stmt.step()) {
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final int _tmpUserId;
          _tmpUserId = (int) (_stmt.getLong(_columnIndexOfUserId));
          final String _tmpMonth;
          if (_stmt.isNull(_columnIndexOfMonth)) {
            _tmpMonth = null;
          } else {
            _tmpMonth = _stmt.getText(_columnIndexOfMonth);
          }
          final double _tmpMinimumGoal;
          _tmpMinimumGoal = _stmt.getDouble(_columnIndexOfMinimumGoal);
          final double _tmpMaximumGoal;
          _tmpMaximumGoal = _stmt.getDouble(_columnIndexOfMaximumGoal);
          _result = new Goal(_tmpId,_tmpUserId,_tmpMonth,_tmpMinimumGoal,_tmpMaximumGoal);
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
