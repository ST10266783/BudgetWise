package com.budgetwise.app.data.db;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.budgetwise.app.data.entities.Category;
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
public final class CategoryDao_Impl implements CategoryDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<Category> __insertAdapterOfCategory;

  private final EntityDeleteOrUpdateAdapter<Category> __deleteAdapterOfCategory;

  private final EntityDeleteOrUpdateAdapter<Category> __updateAdapterOfCategory;

  public CategoryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfCategory = new EntityInsertAdapter<Category>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `categories` (`id`,`userId`,`name`,`iconName`,`colour`,`monthlyLimit`,`isDefault`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final Category entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getUserId());
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getName());
        }
        if (entity.getIconName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getIconName());
        }
        if (entity.getColour() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getColour());
        }
        if (entity.getMonthlyLimit() == null) {
          statement.bindNull(6);
        } else {
          statement.bindDouble(6, entity.getMonthlyLimit());
        }
        final int _tmp = entity.isDefault() ? 1 : 0;
        statement.bindLong(7, _tmp);
      }
    };
    this.__deleteAdapterOfCategory = new EntityDeleteOrUpdateAdapter<Category>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `categories` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final Category entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfCategory = new EntityDeleteOrUpdateAdapter<Category>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `categories` SET `id` = ?,`userId` = ?,`name` = ?,`iconName` = ?,`colour` = ?,`monthlyLimit` = ?,`isDefault` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final Category entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getUserId());
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getName());
        }
        if (entity.getIconName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getIconName());
        }
        if (entity.getColour() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getColour());
        }
        if (entity.getMonthlyLimit() == null) {
          statement.bindNull(6);
        } else {
          statement.bindDouble(6, entity.getMonthlyLimit());
        }
        final int _tmp = entity.isDefault() ? 1 : 0;
        statement.bindLong(7, _tmp);
        statement.bindLong(8, entity.getId());
      }
    };
  }

  @Override
  public Object insertCategory(final Category category,
      final Continuation<? super Long> $completion) {
    if (category == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      return __insertAdapterOfCategory.insertAndReturnId(_connection, category);
    }, $completion);
  }

  @Override
  public Object deleteCategory(final Category category,
      final Continuation<? super Unit> $completion) {
    if (category == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfCategory.handle(_connection, category);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object updateCategory(final Category category,
      final Continuation<? super Unit> $completion) {
    if (category == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfCategory.handle(_connection, category);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public LiveData<List<Category>> getCategoriesForUser(final int userId) {
    final String _sql = "SELECT * FROM categories WHERE userId = ? ORDER BY name ASC";
    return __db.getInvalidationTracker().createLiveData(new String[] {"categories"}, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, userId);
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfIconName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "iconName");
        final int _columnIndexOfColour = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "colour");
        final int _columnIndexOfMonthlyLimit = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "monthlyLimit");
        final int _columnIndexOfIsDefault = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDefault");
        final List<Category> _result = new ArrayList<Category>();
        while (_stmt.step()) {
          final Category _item;
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final int _tmpUserId;
          _tmpUserId = (int) (_stmt.getLong(_columnIndexOfUserId));
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          final String _tmpIconName;
          if (_stmt.isNull(_columnIndexOfIconName)) {
            _tmpIconName = null;
          } else {
            _tmpIconName = _stmt.getText(_columnIndexOfIconName);
          }
          final String _tmpColour;
          if (_stmt.isNull(_columnIndexOfColour)) {
            _tmpColour = null;
          } else {
            _tmpColour = _stmt.getText(_columnIndexOfColour);
          }
          final Double _tmpMonthlyLimit;
          if (_stmt.isNull(_columnIndexOfMonthlyLimit)) {
            _tmpMonthlyLimit = null;
          } else {
            _tmpMonthlyLimit = _stmt.getDouble(_columnIndexOfMonthlyLimit);
          }
          final boolean _tmpIsDefault;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsDefault));
          _tmpIsDefault = _tmp != 0;
          _item = new Category(_tmpId,_tmpUserId,_tmpName,_tmpIconName,_tmpColour,_tmpMonthlyLimit,_tmpIsDefault);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Object getCategoriesSnapshot(final int userId,
      final Continuation<? super List<Category>> $completion) {
    final String _sql = "SELECT * FROM categories WHERE userId = ? ORDER BY name ASC";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, userId);
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfIconName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "iconName");
        final int _columnIndexOfColour = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "colour");
        final int _columnIndexOfMonthlyLimit = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "monthlyLimit");
        final int _columnIndexOfIsDefault = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDefault");
        final List<Category> _result = new ArrayList<Category>();
        while (_stmt.step()) {
          final Category _item;
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final int _tmpUserId;
          _tmpUserId = (int) (_stmt.getLong(_columnIndexOfUserId));
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          final String _tmpIconName;
          if (_stmt.isNull(_columnIndexOfIconName)) {
            _tmpIconName = null;
          } else {
            _tmpIconName = _stmt.getText(_columnIndexOfIconName);
          }
          final String _tmpColour;
          if (_stmt.isNull(_columnIndexOfColour)) {
            _tmpColour = null;
          } else {
            _tmpColour = _stmt.getText(_columnIndexOfColour);
          }
          final Double _tmpMonthlyLimit;
          if (_stmt.isNull(_columnIndexOfMonthlyLimit)) {
            _tmpMonthlyLimit = null;
          } else {
            _tmpMonthlyLimit = _stmt.getDouble(_columnIndexOfMonthlyLimit);
          }
          final boolean _tmpIsDefault;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsDefault));
          _tmpIsDefault = _tmp != 0;
          _item = new Category(_tmpId,_tmpUserId,_tmpName,_tmpIconName,_tmpColour,_tmpMonthlyLimit,_tmpIsDefault);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getCategoryById(final int id, final Continuation<? super Category> $completion) {
    final String _sql = "SELECT * FROM categories WHERE id = ? LIMIT 1";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfIconName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "iconName");
        final int _columnIndexOfColour = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "colour");
        final int _columnIndexOfMonthlyLimit = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "monthlyLimit");
        final int _columnIndexOfIsDefault = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDefault");
        final Category _result;
        if (_stmt.step()) {
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final int _tmpUserId;
          _tmpUserId = (int) (_stmt.getLong(_columnIndexOfUserId));
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          final String _tmpIconName;
          if (_stmt.isNull(_columnIndexOfIconName)) {
            _tmpIconName = null;
          } else {
            _tmpIconName = _stmt.getText(_columnIndexOfIconName);
          }
          final String _tmpColour;
          if (_stmt.isNull(_columnIndexOfColour)) {
            _tmpColour = null;
          } else {
            _tmpColour = _stmt.getText(_columnIndexOfColour);
          }
          final Double _tmpMonthlyLimit;
          if (_stmt.isNull(_columnIndexOfMonthlyLimit)) {
            _tmpMonthlyLimit = null;
          } else {
            _tmpMonthlyLimit = _stmt.getDouble(_columnIndexOfMonthlyLimit);
          }
          final boolean _tmpIsDefault;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsDefault));
          _tmpIsDefault = _tmp != 0;
          _result = new Category(_tmpId,_tmpUserId,_tmpName,_tmpIconName,_tmpColour,_tmpMonthlyLimit,_tmpIsDefault);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getDefaultCategory(final int userId,
      final Continuation<? super Category> $completion) {
    final String _sql = "SELECT * FROM categories WHERE userId = ? AND isDefault = 1 LIMIT 1";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, userId);
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfIconName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "iconName");
        final int _columnIndexOfColour = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "colour");
        final int _columnIndexOfMonthlyLimit = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "monthlyLimit");
        final int _columnIndexOfIsDefault = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDefault");
        final Category _result;
        if (_stmt.step()) {
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final int _tmpUserId;
          _tmpUserId = (int) (_stmt.getLong(_columnIndexOfUserId));
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          final String _tmpIconName;
          if (_stmt.isNull(_columnIndexOfIconName)) {
            _tmpIconName = null;
          } else {
            _tmpIconName = _stmt.getText(_columnIndexOfIconName);
          }
          final String _tmpColour;
          if (_stmt.isNull(_columnIndexOfColour)) {
            _tmpColour = null;
          } else {
            _tmpColour = _stmt.getText(_columnIndexOfColour);
          }
          final Double _tmpMonthlyLimit;
          if (_stmt.isNull(_columnIndexOfMonthlyLimit)) {
            _tmpMonthlyLimit = null;
          } else {
            _tmpMonthlyLimit = _stmt.getDouble(_columnIndexOfMonthlyLimit);
          }
          final boolean _tmpIsDefault;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsDefault));
          _tmpIsDefault = _tmp != 0;
          _result = new Category(_tmpId,_tmpUserId,_tmpName,_tmpIconName,_tmpColour,_tmpMonthlyLimit,_tmpIsDefault);
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
