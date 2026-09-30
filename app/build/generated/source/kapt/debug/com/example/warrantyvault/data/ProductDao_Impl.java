package com.example.warrantyvault.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ProductDao_Impl implements ProductDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Product> __insertionAdapterOfProduct;

  public ProductDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProduct = new EntityInsertionAdapter<Product>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `products` (`id`,`productName`,`brand`,`price`,`purchaseDate`,`warrantyMonths`,`expiryDate`,`invoicePath`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Product entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getProductName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getProductName());
        }
        if (entity.getBrand() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getBrand());
        }
        statement.bindDouble(4, entity.getPrice());
        statement.bindLong(5, entity.getPurchaseDate());
        statement.bindLong(6, entity.getWarrantyMonths());
        statement.bindLong(7, entity.getExpiryDate());
        if (entity.getInvoicePath() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getInvoicePath());
        }
      }
    };
  }

  @Override
  public Object insert(final Product product, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfProduct.insert(product);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAll(final Continuation<? super List<Product>> $completion) {
    final String _sql = "SELECT * FROM products ORDER BY purchaseDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Product>>() {
      @Override
      @NonNull
      public List<Product> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProductName = CursorUtil.getColumnIndexOrThrow(_cursor, "productName");
          final int _cursorIndexOfBrand = CursorUtil.getColumnIndexOrThrow(_cursor, "brand");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfPurchaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "purchaseDate");
          final int _cursorIndexOfWarrantyMonths = CursorUtil.getColumnIndexOrThrow(_cursor, "warrantyMonths");
          final int _cursorIndexOfExpiryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "expiryDate");
          final int _cursorIndexOfInvoicePath = CursorUtil.getColumnIndexOrThrow(_cursor, "invoicePath");
          final List<Product> _result = new ArrayList<Product>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Product _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpProductName;
            if (_cursor.isNull(_cursorIndexOfProductName)) {
              _tmpProductName = null;
            } else {
              _tmpProductName = _cursor.getString(_cursorIndexOfProductName);
            }
            final String _tmpBrand;
            if (_cursor.isNull(_cursorIndexOfBrand)) {
              _tmpBrand = null;
            } else {
              _tmpBrand = _cursor.getString(_cursorIndexOfBrand);
            }
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final long _tmpPurchaseDate;
            _tmpPurchaseDate = _cursor.getLong(_cursorIndexOfPurchaseDate);
            final int _tmpWarrantyMonths;
            _tmpWarrantyMonths = _cursor.getInt(_cursorIndexOfWarrantyMonths);
            final long _tmpExpiryDate;
            _tmpExpiryDate = _cursor.getLong(_cursorIndexOfExpiryDate);
            final String _tmpInvoicePath;
            if (_cursor.isNull(_cursorIndexOfInvoicePath)) {
              _tmpInvoicePath = null;
            } else {
              _tmpInvoicePath = _cursor.getString(_cursorIndexOfInvoicePath);
            }
            _item = new Product(_tmpId,_tmpProductName,_tmpBrand,_tmpPrice,_tmpPurchaseDate,_tmpWarrantyMonths,_tmpExpiryDate,_tmpInvoicePath);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getById(final int id, final Continuation<? super Product> $completion) {
    final String _sql = "SELECT * FROM products WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Product>() {
      @Override
      @Nullable
      public Product call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProductName = CursorUtil.getColumnIndexOrThrow(_cursor, "productName");
          final int _cursorIndexOfBrand = CursorUtil.getColumnIndexOrThrow(_cursor, "brand");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfPurchaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "purchaseDate");
          final int _cursorIndexOfWarrantyMonths = CursorUtil.getColumnIndexOrThrow(_cursor, "warrantyMonths");
          final int _cursorIndexOfExpiryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "expiryDate");
          final int _cursorIndexOfInvoicePath = CursorUtil.getColumnIndexOrThrow(_cursor, "invoicePath");
          final Product _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpProductName;
            if (_cursor.isNull(_cursorIndexOfProductName)) {
              _tmpProductName = null;
            } else {
              _tmpProductName = _cursor.getString(_cursorIndexOfProductName);
            }
            final String _tmpBrand;
            if (_cursor.isNull(_cursorIndexOfBrand)) {
              _tmpBrand = null;
            } else {
              _tmpBrand = _cursor.getString(_cursorIndexOfBrand);
            }
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final long _tmpPurchaseDate;
            _tmpPurchaseDate = _cursor.getLong(_cursorIndexOfPurchaseDate);
            final int _tmpWarrantyMonths;
            _tmpWarrantyMonths = _cursor.getInt(_cursorIndexOfWarrantyMonths);
            final long _tmpExpiryDate;
            _tmpExpiryDate = _cursor.getLong(_cursorIndexOfExpiryDate);
            final String _tmpInvoicePath;
            if (_cursor.isNull(_cursorIndexOfInvoicePath)) {
              _tmpInvoicePath = null;
            } else {
              _tmpInvoicePath = _cursor.getString(_cursorIndexOfInvoicePath);
            }
            _result = new Product(_tmpId,_tmpProductName,_tmpBrand,_tmpPrice,_tmpPurchaseDate,_tmpWarrantyMonths,_tmpExpiryDate,_tmpInvoicePath);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object count(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM products";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
